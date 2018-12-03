package br.edu.ifrs.canoas.jee.webapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.jee.webapp.model.dao.DiariaAvulsaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.DiariaReservadaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.PessoaFisicaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.PessoaJuridicaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.dao.QuartoDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaAvulsa;
import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaReservada;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaJuridica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

@Stateless
public class GerenciarDiariaService {
	@Inject
	private DiariaAvulsaDAO diariaAvulsaDAO;

	@Inject
	private DiariaReservadaDAO diariaReservadaDAO;

	@Inject
	private PessoaFisicaDAO pessoaFisicaDAO;

	@Inject
	private PessoaJuridicaDAO pessoaJuridicaDAO;

	@Inject
	private QuartoDAO quartoDAO;

	public Boolean salvaDiaria(DiariaAvulsa diariaAvulsa) {
		if(!validaDataEntrada(diariaAvulsa.getData())) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "diaria.data.invalida");
			return false;
		}
		if (diariaAvulsa.getId() == null) {
			diariaAvulsaDAO.insere(diariaAvulsa);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "diaria.cadastra.sucesso");
			return true;
		}else {
			diariaAvulsaDAO.atualiza(diariaAvulsa);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "diaria.atualizada.sucesso");
			return true;
		}
	}

	public void salvaDiariaReservada(DiariaReservada diariaReservada) {
		if (diariaReservada.getId() == null) {
			diariaReservadaDAO.insere(diariaReservada);
		}else {
			diariaReservadaDAO.atualiza(diariaReservada);
		}
	}

	@SuppressWarnings("unchecked")
	public List<DiariaAvulsa> busca(String criterio) {
		if (criterio != null && criterio.length() > 0) {
			return diariaAvulsaDAO.buscaPorCriterio(criterio);
		}else {
			return diariaAvulsaDAO.lista();
		}
	}

	@SuppressWarnings("unchecked")
	public List<DiariaReservada> buscaDiariaReservada(String criterio) {
		if (criterio != null && criterio.length() > 0)
			return diariaReservadaDAO.buscaPorCriterio(criterio);
		else
			return diariaReservadaDAO.lista();
	}

	public void exclui(DiariaAvulsa diariaAvulsa) {
		diariaAvulsaDAO.exclui(diariaAvulsa.getId());
		Mensagens.define(FacesMessage.SEVERITY_INFO, "diaria.exclui.sucesso");
	}

	public void excluiDiariaReservada(DiariaReservada diariareservada) {
		diariaAvulsaDAO.exclui(diariareservada.getId());
	}

	public List<String> getTipoCliente() {
		List<String> list = new ArrayList<String>();
		list.add("Pessoa Física");
		list.add("Pessoa Jurídica");

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<PessoaFisica> getPF(){
		return pessoaFisicaDAO.lista();
	}

	@SuppressWarnings("unchecked")
	public List<PessoaJuridica> getPJ(){
		return pessoaJuridicaDAO.lista();
	}

	@SuppressWarnings("unchecked")
	public List<Quarto> getQuartos(){
		return quartoDAO.lista();
	}
	
	public Boolean validaDataEntrada(Date dataEntrada) {
		Date dataAtual = new Date();
		
		return dataEntrada.compareTo(dataAtual) >= 0 ? true : false;
	}
}
