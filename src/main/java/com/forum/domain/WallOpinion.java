package com.forum.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="wallopinion")

public class WallOpinion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	@Column(nullable = false)
	private LocalDate publishedDate;


	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Opinion opinion;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User user;
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Pais pais;

	public WallOpinion() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Opinion getOpinion() {
		return opinion;
	}

	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

                

}
