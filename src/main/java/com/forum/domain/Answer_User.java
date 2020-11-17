package com.forum.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="answer_user")
public class Answer_User  {
	
	@Id
	@GeneratedValue
	@Column(nullable=false)
	private Long id;
	
	
	//@Column(nullable=false)
	//private Integer typeChoice;  //"1:logical; 2:emotional" 
	
	
	@Column(nullable=false)
	private String choice;  //"p" or "n" or "i"
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="answerId")
	private Answer answer;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
      	
	
	
	

}
