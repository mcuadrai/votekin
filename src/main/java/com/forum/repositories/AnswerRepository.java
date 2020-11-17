package com.forum.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.forum.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
	
	List<Answer> findAllByOpinionId(Long opinionId);
	
	
	 @Query(nativeQuery = true, value="SELECT * FROM Answer a WHERE a.opinion_id = ?1 AND a.positiveAmount = (SELECT max(positiveAmount) FROM answer WHERE opinion_id = ?1) AND a.positiveAmount > 0")
	 List<Answer> findPositiveResultsByOpinionId(Long opinionId);
	 
	 @Query(nativeQuery = true, value="SELECT * FROM Answer a WHERE a.opinion_id = ?1 AND a.negativeAmount = (SELECT max(negativeAmount) FROM answer WHERE opinion_id = ?1) AND a.negativeAmount > 0")
	 List<Answer> findNegativeResultsByOpinionId(Long opinionId);
	 
	 @Query(nativeQuery = true, value="SELECT * FROM Answer a WHERE a.opinion_id = ?1 AND a.interestingAmount = (SELECT max(interestingAmount) FROM answer WHERE opinion_id = ?1) AND a.interestingAmount > 0")
	 List<Answer> findInterestingResultsByOpinionId(Long opinionId);
}
