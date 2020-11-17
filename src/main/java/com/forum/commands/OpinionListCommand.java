package com.forum.commands;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;


public class OpinionListCommand implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	private String[] themes;
	private MultipartFile file;
	
	//private List<Opinion> opinions;

	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String[] getThemes() {
		return themes;
	}

	public void setThemes(String[] themes) {
		this.themes = themes;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	

//	public List<Opinion> getOpinions() {
//		return opinions;
//	}
//
//	public void setOpinions(List<Opinion> opinions) {
//		this.opinions = opinions;
//	}
	
	
	
	
	
}
