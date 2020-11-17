package com.forum.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.forum.commands.AnswerCommand;
import com.forum.domain.Answer;
import com.forum.domain.Opinion;

public interface AnswerService {

	void create(AnswerCommand answerCommand);
	
	public List<Answer> findPositiveResults(Long opinionId);
	public List<Answer> findNegativeResults(Long opinionId);
	public List<Answer> findInterestingResults(Long opinionId);
	public Answer buscarRespuesta(Long answerId);
	public Answer createAnswer(AnswerCommand answerCommand);

	public List<Opinion> findCommentsByOpinionId(Long opinionId,Pageable pageable);

	
}
