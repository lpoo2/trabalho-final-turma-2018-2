package br.edu.ifrs.canoas.jee.webapp.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.SituacaoQuarto;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarQuartoService;

public class GerenciarQuarto {
	
	private GerenciarQuartoService gerenciarQuartoService;
	
	private Quarto quarto;
	
	private SituacaoQuarto situacao;
	
	private List<Quarto> quartos;
		
	public String salva() {
		gerenciarQuartoService.salvaQuarto(quarto);
		this.init();
		return limpa();
	}
	
	@PostConstruct
    public void init() {
		quartos = gerenciarQuartoService.busca(null);	
    }
	
	public void exclui() {
		gerenciarQuartoService.exclui(quarto);
		this.init();
	}
	
	public void edita(Quarto quarto) {
		this.quarto = quarto;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public List<Quarto> getQuartos() {
		return quartos;
	}

	public void setQuartos(List<Quarto> quartos) {
		this.quartos = quartos;
	}

	public String limpa() {
		quarto = new Quarto();
		situacao = SituacaoQuarto.DISPONIVEL;
		quarto.setSituacao(situacao);
		return "/adm/quarto.jsf?facesRedirect=true";
	}
	 

}
