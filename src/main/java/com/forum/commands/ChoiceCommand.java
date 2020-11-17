package com.forum.commands;

import com.forum.domain.Answer;
import com.forum.domain.Answer_User;
import com.forum.domain.User;
import com.forum.utils.MyUtils;

public class ChoiceCommand {
	
	
	private Long answerId;
	private String choice;
	private Long opinionId;
	

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public Long getOpinionId() {
		return opinionId;
	}
	public void setOpinionId(Long opinionId) {
		this.opinionId = opinionId;
	}


	public Answer_User toAnswer_User() {
		
		Answer_User answer_user = new Answer_User();
		
		Answer answer = new Answer();
		answer.setId(answerId);
		
		answer_user.setAnswer(answer);
		answer_user.setChoice(choice);
		
		User user = MyUtils.currentUser().get();
		user.setId(user.getId());
		answer_user.setUser(user);
				
		return answer_user;
	}
	
     
	
	
}
