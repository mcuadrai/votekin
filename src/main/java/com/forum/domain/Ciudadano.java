package com.forum.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="ciudadano")
public class Ciudadano  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String fotoAvatar;
	
	@Column
	private String fotoAvatarMedium;

	@Column
	private String fotoAvatarSmall;
	
	@Column
	private String fotoAvatarDebate;
	
	@Column
	private String codigoIdentificacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private TipoIdentificacion tipoIdentificacion;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date   fechaNacimiento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Direccion direccionResidencia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Pais paisDondeVoto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Comuna comunaDondeVoto;

	public String getCodigoIdentificacion() {
		return codigoIdentificacion;
	}

	public void setCodigoIdentificacion(String codigoIdentificacion) {
		this.codigoIdentificacion = codigoIdentificacion;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Direccion getDireccionResidencia() {
		return direccionResidencia;
	}

	public void setDireccionResidencia(Direccion direccionResidencia) {
		this.direccionResidencia = direccionResidencia;
	}

	public Pais getPaisDondeVoto() {
		return paisDondeVoto;
	}

	public void setPaisDondeVoto(Pais paisDondeVoto) {
		this.paisDondeVoto = paisDondeVoto;
	}

	public Comuna getComunaDondeVoto() {
		return comunaDondeVoto;
	}

	public void setComunaDondeVoto(Comuna comunaDondeVoto) {
		this.comunaDondeVoto = comunaDondeVoto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFotoAvatar() {
		return fotoAvatar;
	}

	public void setFotoAvatar(String fotoAvatar) {
		this.fotoAvatar = fotoAvatar;
	}

	public String getFotoAvatarMedium() {
		return fotoAvatarMedium;
	}

	public void setFotoAvatarMedium(String fotoAvatarMedium) {
		this.fotoAvatarMedium = fotoAvatarMedium;
	}

	public String getFotoAvatarSmall() {
		return fotoAvatarSmall;
	}

	public void setFotoAvatarSmall(String fotoAvatarSmall) {
		this.fotoAvatarSmall = fotoAvatarSmall;
	}

	public String getFotoAvatarDebate() {
		return fotoAvatarDebate;
	}

	public void setFotoAvatarDebate(String fotoAvatarDebate) {
		this.fotoAvatarDebate = fotoAvatarDebate;
	}
	
            	
	
	
}
