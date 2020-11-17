package com.forum.model;

import org.springframework.data.domain.Pageable;

public class OpinionFilterModel {

	private String text; 
	private Long[] choiceId;
	private String[] theams;
	private String[] tags;
	private Pageable pageable;
	
	private boolean isSet;
	
	public String getText() {
		return text;
	}
	public Long[] getChoiceId() {
		return choiceId;
	}
	public String[] getTheams() {
		return theams;
	}
	public String[] getTags() {
		return tags;
	}
	
	public void setTags(String[] tags) {
		this.tags = tags;
		if(tags!=null) {
			isSet = true;
		}
	}
	public Pageable getPageable() {
		return pageable;
	}
	public void setText(String text) {
		this.text = text;
		if(text!=null) {
			isSet = true;
		}
		
	}
	public void setChoiceId(Long[] choiceId) {
		this.choiceId = choiceId;
		if(choiceId!=null) {
			isSet = true;
		}
	}
	public void setTheams(String[] theams) {
		
		if(theams!=null) {
			for (String t : theams) {
				if (t.equals("All")) {
					return;
				}
			}
			this.theams = theams;
		
			isSet = true;
		}
	}
	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}
	public boolean isSet() {
		return isSet;
	}
	
	
}
