package com.forum.commands;

import com.forum.domain.Answer;
import com.forum.domain.Opinion;
import com.forum.domain.User;
import com.forum.utils.MyUtils;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AnswerCommand {
	
	
	@NotBlank
	private String text;
	
	@Size(min=1, max=100)
	private String themes;
	
	private Long idUser;
	
	private Long originAnswerId;
	
	private Long opinionId;
	
	private int levelId;
		

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
    
	public String getThemes() {
		return themes;
	}

	public void setThemes(String themes) {
		this.themes = themes;
	}
	
	public Long getOriginAnswerId() {
		return originAnswerId;
	}

	public void setOriginAnswerId(Long originAnswerId) {
		this.originAnswerId = originAnswerId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	
	

	public Long getOpinionId() {
		return opinionId;
	}

	public void setOpinionId(Long opinionId) {
		this.opinionId = opinionId;
	}

	public Answer toAnswer() {
		
		Answer answer = new Answer();
		
		answer.setText(text);
		//answer.setThemes(themes);
		answer.setOriginAnswerId(originAnswerId);
		Opinion opinion = new Opinion();
		opinion.setId(opinionId);
		answer.setOpinion(opinion);
	    answer.setLevelId(levelId); 
		
		User user = MyUtils.currentUser().get();
		user.setId(user.getId());
		answer.setUser(user);
				
		return answer;
	}
	
     
	
	
}
