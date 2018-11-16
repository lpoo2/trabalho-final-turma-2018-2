package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * Entity implementation class for Entity: Reserva
 *
 */
@Entity
@Data
public class Reserva extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -7506875242774252963L;
	private Date data;
	private Double valor;
	
	@ManyToOne
	private Pessoa pessoa;
	
	public Reserva() {
		super();
	}

	public Reserva(Date data, Double valor) {
		this.data = data;
		this.valor = valor;
	}
	
}
