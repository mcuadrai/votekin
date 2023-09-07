package com.forum.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="choice")
public class Choice  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String codigoNemotecnico;
	
	@Column
	private Integer orderChoice;
	
	@Column
	private String name;

	@Column
	private String rutaIcono;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public String getRutaIcono() {
		return rutaIcono;
	}

	public void setRutaIcono(String rutaIcono) {
		this.rutaIcono = rutaIcono;
	}

	public String getCodigoNemotecnico() {
		return codigoNemotecnico;
	}

	public void setCodigoNemotecnico(String codigoNemotecnico) {
		this.codigoNemotecnico = codigoNemotecnico;
	}

	public Integer getOrderChoice() {
		return orderChoice;
	}

	public void setOrderChoice(Integer order) {
		this.orderChoice = order;
	}
    	
	
}
