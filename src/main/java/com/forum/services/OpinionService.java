package com.forum.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.forum.commands.OpinionCommand;
import com.forum.domain.Choice;
import com.forum.domain.Opinion;
import com.forum.domain.TagsList;
import com.forum.domain.Theme;

public interface OpinionService {

	public List<Opinion> findAllRecentOpinions();
	@Deprecated
	public List<Opinion> findAllRecentOpinions(String text);
	
	public List<Opinion> findAllRecentOpinions(String text,Long[] choiceId,String[] theams,String[] tags,Pageable pageable);
	
	public Opinion fetchById(Long opinionId);
	
	public List<Theme> findAllThemes();
	
	@Deprecated
	public List<Opinion> findMyPosts(Long userId);
	
	public void create(OpinionCommand opinionCommand, int levelId, Long OriginOpinionId);
	
	public List<Opinion> findOpinionsByChoiceId(Long... choiceId);
	
	public List<Opinion> findOpinionsByOpinionIdChoiceId(Long opinionId, Long choiceId);
	
	public Optional<Choice> findChoiceById(Long choiceId);
	
	public List<TagsList> getTagsListByValue(String value);
	
	public void saveTags(String tags);
	
	public List<Opinion> getOpinionByUserId(Long userId, Pageable pageable);
	
	public List<Opinion> getOpinionByCurrentUser(Pageable pageable);
}
