package com.forum.domain;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="answer")
public class Answer  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=true, length=200)
	private String text;

	@Column(nullable=false)
	private int levelId;
	
	@Column(nullable=true)
	private Long originAnswerId;

	@Column(nullable=false)
	private Long positiveAmount;

	@Column(nullable=false)
	private Long negativeAmount;
	
	@Column(nullable=false)
	private Long interestingAmount;
	
	/*
	@Column(nullable=false)
	private String relation;

	@Column(nullable=false)
	private long visualizationId;
	*/
	
	@OneToOne(fetch=FetchType.LAZY)
	  private Opinion opinion;
	
	
	@Column(nullable=true, length=200)
	private String themes;
	
	@Column(nullable=false)
	private LocalDate  creationDate;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;
	
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	

	public String getThemes() {
		return themes;
	}

	public void setThemes(String themes) {
		this.themes = themes;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public Long getOriginAnswerId() {
		return originAnswerId;
	}

	public void setOriginAnswerId(Long originAnswerId) {
		this.originAnswerId = originAnswerId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Long getPositiveAmount() {
		return positiveAmount;
	}

	public void setPositiveAmount(Long positiveAmount) {
		this.positiveAmount = positiveAmount;
	}

	public Long getNegativeAmount() {
		return negativeAmount;
	}

	public void setNegativeAmount(Long negativeAmount) {
		this.negativeAmount = negativeAmount;
	}

	public Long getInterestingAmount() {
		return interestingAmount;
	}

	public void setInterestingAmount(Long interestingAmount) {
		this.interestingAmount = interestingAmount;
	}

	
	public Opinion getOpinion() {
		return opinion;
	}

	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}
        	

	
	

}
