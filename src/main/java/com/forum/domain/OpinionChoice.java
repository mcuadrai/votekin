package com.forum.domain;

import java.io.Serializable;
import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.forum.responsemodels.ResponseModel.GetIt;
import com.forum.responsemodels.ResponseModel.SkipIt;

@Entity
@Table(name="opinionchoice")

public class OpinionChoice implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private Long amount;

	@SkipIt
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference	
	private OpinionEvaluacion opinionEvaluacion;


	@ManyToOne(fetch = FetchType.LAZY)
	private Choice choice;

	@GetIt
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="opinion_choice_id")
	@JsonIgnore
	private List<ChoiceVote> choiceVotes;


	public OpinionChoice() {
		choiceVotes = new ArrayList<ChoiceVote>();
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}




	public Choice getChoice() {
		return choice;
	}

	public void setChoice(Choice choice) {
		this.choice = choice;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}


	public List<ChoiceVote> getChoiceVotes() {
		return choiceVotes;
	}


	public void setChoiceVotes(List<ChoiceVote> choiceVotes) {
		this.choiceVotes = choiceVotes;
	}

	public void addVote(ChoiceVote vote) {
		this.choiceVotes.add(vote);
	}

	public OpinionEvaluacion getOpinionEvaluacion() {
		return opinionEvaluacion;
	}

	public void setOpinionEvaluacion(OpinionEvaluacion opinionEvaluacion) {
		this.opinionEvaluacion = opinionEvaluacion;
	} 	

     
}
