/*package com.forum.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity(name = "OpinionTheme")
@Table(name = "opinion_theme")
public class OpinionTheme {

	@EmbeddedId
	OpinionThemeId id;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @MapsId("opinionId")
	private Opinion opinion;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @MapsId("themeId")
	private Theme theme;

	public Opinion getOpinion() {
		return opinion;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	 
	 
	
}
*/