package com.forum.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.forum.responsemodels.ResponseModel.GetIt;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="opinion")
public class Opinion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    
	@Column(nullable = true, length = 150)
	@Size(max=150)
	private String text;

	@GetIt
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE })
	@JoinTable(name = "opinion_theme", joinColumns = @JoinColumn(name = "opinion_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "theme_id", referencedColumnName = "id"))
	@JsonManagedReference 
	private List<Theme> themes;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "opinion_topic", joinColumns = @JoinColumn(name = "opinion_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "topic_id", referencedColumnName = "id"))
	@JsonManagedReference 
	private List<Topic> topics;
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Pais pais;

	@Column(nullable = false)
	private LocalDateTime creationDate;
	

	@Column
	private int levelId;
	
	@Column
	private Long originOpinionId;
	
	private String image;
	
	private String video;
	
	private String tags;
	
	private String location;
	

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;


	@GetIt
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "opinion_id")
	@OrderBy("id")
	private List<OpinionEvaluacion> evaluacionesDeOpinion;
	
	@Transient
	private List<Opinion> comments;

	public Opinion() {
		evaluacionesDeOpinion = new ArrayList<>();
		themes = new ArrayList<Theme>();
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public List<OpinionEvaluacion> getEvaluacionesDeOpinion() {
		return evaluacionesDeOpinion;
	}

	public void setEvaluacionesDeOpinion(List<OpinionEvaluacion> evaluacionesDeOpinion) {
		this.evaluacionesDeOpinion = evaluacionesDeOpinion;
	}

	public void addEvaluation(OpinionEvaluacion evaluacion) {

		evaluacionesDeOpinion.add(evaluacion);
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	public List<Theme> getThemes() {
		return themes;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
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

	public List<Opinion> getComments() {
		return comments;
	}

	public void setComments(List<Opinion> comments) {
		this.comments = comments;
	}

	
	
	
//    public String getTimePoint() {
//    	return MyUtils.convertTimeToTimePoint(this.creationDate);
//    }     
}
