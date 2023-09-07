package com.forum.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="choicevote")
public class ChoiceVote {

	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 
	 @ManyToOne(fetch=FetchType.LAZY)
	 private User user;
	 
	 //@ManyToOne(fetch=FetchType.LAZY)
	 //private OpinionChoice opinionChoice;
	
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	public OpinionChoice getOpinionChoice() {
//		return opinionChoice;
//	}
//
//	public void setOpinionChoice(OpinionChoice opinionChoice) {
//		this.opinionChoice = opinionChoice;
//	}

	
    		 
	 
	 
}
