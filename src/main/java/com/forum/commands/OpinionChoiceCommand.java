package com.forum.commands;

import java.util.List;

public class OpinionChoiceCommand {
	
	private Long id;
	private Long amount;
	
	private Boolean sonExcluyentes;
	
	private List<OpinionChoiceCommand> choices;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Boolean getSonExcluyentes() {
		return sonExcluyentes;
	}
	public void setSonExcluyentes(Boolean sonExcluyentes) {
		this.sonExcluyentes = sonExcluyentes;
	}
	public List<OpinionChoiceCommand> getChoices() {
		return choices;
	}
	public void setChoices(List<OpinionChoiceCommand> choices) {
		this.choices = choices;
	}
	
       	
   
	

}
