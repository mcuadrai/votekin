package com.forum.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.forum.responsemodels.ResponseModel.GetIt;
import com.forum.responsemodels.ResponseModel.SkipIt;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
