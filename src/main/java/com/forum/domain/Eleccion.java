package com.forum.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="eleccion")
public class Eleccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nombre;
	
	@Column
	private String tipoEleccion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Pais pais;
	
	@Column
	private String estado;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecha;
	
		
	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="eleccion_id")
	private List<Postulacion> postulaciones;
	
	public Eleccion() {
		postulaciones = new ArrayList<Postulacion>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoEleccion() {
		return tipoEleccion;
	}

	public void setTipoEleccion(String tipoEleccion) {
		this.tipoEleccion = tipoEleccion;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
    public void addPostulacion(Postulacion postulacion) {
    	postulaciones.add(postulacion);
    }
	

	public List<Postulacion> getPostulaciones() {
		return postulaciones;
	}

	public void setPostulaciones(List<Postulacion> postulaciones) {
		this.postulaciones = postulaciones;
	}
    
    
	
	
}
