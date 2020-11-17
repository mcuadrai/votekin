package com.forum.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ciudadano")
public class Ciudadano implements Serializable {
	
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
