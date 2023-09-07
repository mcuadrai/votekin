package com.forum.commands;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.forum.domain.Opinion;
import com.forum.domain.Theme;
import com.forum.domain.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class OpinionCommand {
	
	
	@NotEmpty
	@Size(min=1, max=150)
	private String text;
	
	@NotNull
	private String[] themes;
	
	
	private List<Theme> themes_list;
	
	private int levelId;
	
	private Long opinionId;
	
	private Long originOpinionId;
	
	private Long idUser;
	
	private String image;
	
	private String video;
	
	private MultipartFile file;
	
	private String tags;
	
	private String location;
	
	public Long getOpinionId() {
		return opinionId;
	}

	public void setOpinionId(Long opinionId) {
		this.opinionId = opinionId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public Long getOriginOpinionId() {
		return originOpinionId;
	}

	public void setOriginOpinionId(Long originOpinionId) {
		this.originOpinionId = originOpinionId;
	}

	public OpinionCommand() {
		themes_list = new ArrayList<Theme>();
	}

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
    
    
	public String[] getThemes() {
		return themes;
	}

	public void setThemes(String[] themes) {
		this.themes = themes;
	}
	
	
	

	public List<Theme> getThemes_list() {
		return themes_list;
	}

	public void setThemes_list(List<Theme> themes_list) {
		this.themes_list = themes_list;
	}

	
	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	
	
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Opinion toOpinion() {
		
		Opinion opinion = new Opinion();
		opinion.setText(text);
		User user = new User();
		user.setId(idUser);
		opinion.setUser(user);
		opinion.setImage(image);		
		opinion.setVideo(video);
		opinion.setTags(tags);
		opinion.setLocation(location);
		return opinion;
	}
	

	
	
}
