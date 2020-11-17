package com.forum.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
