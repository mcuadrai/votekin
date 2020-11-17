package com.forum.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.forum.domain.OpinionChoice;

public interface OpinionChoiceRepository extends JpaRepository<OpinionChoice, Long> {

	List<OpinionChoice> findByOpinionEvaluacionId(Long id);
		
	@Query(nativeQuery=true,value="SELECT c.*  FROM choicevote v INNER JOIN opinionchoice c on (v.opinion_choice_id = c.id) WHERE c.opinionEvaluacion_id = ?1 and v.user_id = ?2")
	List<OpinionChoice> findByOpinionEvaluacionIdAndUserId(Long opinionEvaluacionId, Long userId);

	@Query(nativeQuery=true,value="SELECT c.* FROM opinionchoice c inner join choicevote v  on (c.id = v.opinion_choice_id)   WHERE c.opinionEvaluacion_id = ?1 AND c.id != ?2 AND v.user_id = ?3")
	List<OpinionChoice> findByEvaluacionIdAndIdNotAndUserId(Long id, Long opinionChoiceId, Long userId);
	
	


	

	
	
		
}
