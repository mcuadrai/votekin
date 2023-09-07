package com.forum.services;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.forum.commands.AnswerCommand;
import com.forum.domain.Answer;
import com.forum.domain.Opinion;
import com.forum.repositories.AnswerRepository;
import com.forum.repositories.OpinionRepository;

import jakarta.annotation.PostConstruct;

@Service("answerService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class AnswerServiceImpl implements AnswerService {
	
	private static Log log = LogFactory.getLog(AnswerServiceImpl.class);
	
	@Autowired
	private OpinionRepository opinionRepository;
	
	private AnswerRepository answerRepository;
	
	public AnswerServiceImpl(AnswerRepository answerRepository) {
		this.answerRepository = answerRepository;
	}
	
	
	@PostConstruct
	public void init() {
		
		log.info("Inside Post construct de answerService");
	}
	

	@Override
	@Deprecated
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void create(AnswerCommand answerCommand) {
		
		Answer answer = answerCommand.toAnswer();
		answer.setCreationDate(LocalDate.now());
		answer.setPositiveAmount((long) 0);
		answer.setNegativeAmount((long) 0);
		answer.setInterestingAmount((long) 0);
		
		answerRepository.save(answer);
		
	}
	
	@Override
	@Deprecated
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Answer createAnswer(AnswerCommand answerCommand) {
		Answer answer = answerCommand.toAnswer();
		answer.setCreationDate(LocalDate.now());
		answer.setPositiveAmount((long) 0);
		answer.setNegativeAmount((long) 0);
		answer.setInterestingAmount((long) 0);
		
		return answerRepository.save(answer);
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Opinion> findCommentsByOpinionId(Long opinionId,Pageable pageable) {
		return opinionRepository.findByOriginOpinionIdOrderByCreationDateDesc(opinionId,pageable);
	   
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Answer> findPositiveResults(Long opinionId) {
		return answerRepository.findPositiveResultsByOpinionId(opinionId);
	}
	@Override
	@Transactional(readOnly=true)
	public List<Answer> findNegativeResults(Long opinionId) {
		return answerRepository.findNegativeResultsByOpinionId(opinionId);
	}
	@Override
	@Transactional(readOnly=true)
	public List<Answer> findInterestingResults(Long opinionId) {
		return answerRepository.findInterestingResultsByOpinionId(opinionId);
	}

	@Override
	@Transactional(readOnly=true)
	public Answer buscarRespuesta(Long answerId) {
	   Answer answerNull =	new Answer();
	   answerNull.setId(answerId);
	   answerNull.setPositiveAmount((long) 0);
	   answerNull.setNegativeAmount((long)0);
	   answerNull.setInterestingAmount((long) 0);
	   log.info("buscarRespuesta="+answerId);
	   
	   return answerRepository.findById(answerId).orElse(answerNull);
	}


	
	

}