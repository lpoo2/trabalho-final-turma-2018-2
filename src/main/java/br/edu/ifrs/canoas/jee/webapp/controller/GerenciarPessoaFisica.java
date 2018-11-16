package br.edu.ifrs.canoas.jee.webapp.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.service.GerenciarPessoaFisicaService;
import lombok.Data;

@Named
@RequestScoped
@Data
public class GerenciarPessoaFisica {
	
	@Inject
	private GerenciarPessoaFisicaService gerenciarPessoaFisicaService;
	@Inject
	private PessoaFisica pessoaFisica;
	
	private List<PessoaFisica> pessoasFisicas;
		
	public String salva() {
		gerenciarPessoaFisicaService.salvaPessoaFisica(pessoaFisica);
		this.init();
		return limpa();
	}
	
	@PostConstruct
    public void init() {
		pessoasFisicas = gerenciarPessoaFisicaService.busca(null);	
    }
	
	public void exclui() {
		gerenciarPessoaFisicaService.exclui(pessoaFisica);
		this.init();
	}
	
	public void edita(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public String limpa() {
		pessoaFisica = new PessoaFisica();
		return "/Client/PessoaFisica.jsf?facesRedirect=true";
	}

}
