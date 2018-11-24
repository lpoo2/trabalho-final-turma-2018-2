package br.edu.ifrs.canoas.jee.webapp.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.jee.webapp.model.dao.DiariaAvulsaDAO;
import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaAvulsa;

@Stateless
public class GerenciarDiariaService {
	@Inject
	private DiariaAvulsaDAO diariaAvulsaDAO;
	
	public void salvaDiaria(DiariaAvulsa diariaAvulsa) {
		if (diariaAvulsa.getId() == null) {
			diariaAvulsaDAO.insere(diariaAvulsa);
		}
	}
}