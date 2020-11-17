package com.forum.domain;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.forum.responsemodels.ResponseModel.GetIt;
import com.forum.responsemodels.ResponseModel.SkipIt;

@Entity
@Table(name="opinionevaluacion")

public class OpinionEvaluacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private TipoEvaluacion tipoEvaluacion;

	@GetIt
	@OneToMany(mappedBy="opinionEvaluacion", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonManagedReference
	@OrderBy("id")
	private Set<OpinionChoice> opinionChoices ;
	
	@SkipIt
	@ManyToOne
	private Opinion opinion;
	
	public OpinionEvaluacion() {
		opinionChoices = new LinkedHashSet<OpinionChoice>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoEvaluacion getTipoEvaluacion() {
		return tipoEvaluacion;
	}

	public void setTipoEvaluacion(TipoEvaluacion tipoEvaluacion) {
		this.tipoEvaluacion = tipoEvaluacion;
	}


	public void addChoice(OpinionChoice opinionChoice) {
		opinionChoices.add(opinionChoice);
		
    }
	
	public Set<OpinionChoice> getOpinionChoices() {
		return opinionChoices;
	}

	public void setOpinionChoices(Set<OpinionChoice> opinionChoices) {
		this.opinionChoices = opinionChoices;
	}

	public Opinion getOpinion() {
		return opinion;
	}

	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}

	
}
