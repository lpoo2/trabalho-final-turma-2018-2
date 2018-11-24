package br.edu.ifrs.canoas.jee.webapp.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.jee.webapp.model.entity.Reserva;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarReservaService;
import lombok.Data;

@Named
@RequestScoped
@Data
public class GerenciarReservaMB {

	@Inject
    private GerenciarReservaService gerenciarReservaService;	
	@Inject
	private Reserva reserva;
	private List<Reserva> reservas;
	private List<String> tipoClientes;
	private String tipoCliente;
	private List<String> cpfs;
//	private List<Pessoa> cpfs;
	private String cpf;
	private List<String> cnpjs;
//	private List<Pessoa> cnpjs;
	private String cnpj;
	private String labelDado;
	private List<String> quartos;
//	private List<Quarto> quartos;
	private String quarto;
//	private Quarto quarto;
	private Date dataAtual;
	
	@PostConstruct
    public void init() {
		reservas = gerenciarReservaService.busca(null);	
		tipoClientes = Arrays.asList("Pessoa Fisica", "Pessoa Juridica");
		cpfs = Arrays.asList("CPF1", "CPF2");
//		cpfs = gerenciarReservaService.pegaCpfPf();
		cnpjs = Arrays.asList("CNPJ1", "CNPJ2");
//		cnpjs = gerenciarReservaService.pegaCnpjPj();
		quartos = Arrays.asList("Quarto 1", "Quarto 2");
//		quartos = gerenciarReservaService.pegaQuartos();
		dataAtual = new Date();
    }
	
	public String salva() {
		gerenciarReservaService.salvaReserva(reserva);
		this.init();
		return limpa();
	}
	
	public void edita(Reserva r) {
		this.reserva = r;
	}
	
	public void exclui() {
		gerenciarReservaService.exclui(reserva);
		this.init();
	}
	
	public String limpa() {
		reserva = new Reserva();
		return "/public/reserva.jsf?facesRedirect=true";
	}
	
	
}
