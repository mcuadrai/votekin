/*package com.forum.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OpinionThemeId implements Serializable{
	 
	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;

	@Column(name = "opinion_id")
	private Long opinionId;
	 
	@Column(name = "theme_id")
	private Long themeId;

	
	
	public Long getOpinionId() {
		return opinionId;
	}

	public Long getThemeId() {
		return themeId;
	}

	public void setOpinionId(Long opinionId) {
		this.opinionId = opinionId;
	}

	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        OpinionThemeId that = (OpinionThemeId) o;
        return Objects.equals(opinionId, that.opinionId) &&
               Objects.equals(themeId, that.themeId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(opinionId, themeId);
    }
	
}
*/