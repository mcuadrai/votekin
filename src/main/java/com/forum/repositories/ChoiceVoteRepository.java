package com.forum.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.forum.domain.ChoiceVote;

public interface ChoiceVoteRepository extends JpaRepository<ChoiceVote, Long> {

	
	@Query(nativeQuery=true,value="SELECT v.*  FROM choicevote v INNER JOIN opinionchoice c on (v.opinion_choice_id = c.id) WHERE c.opinionEvaluacion_id = ?1 and v.user_id = ?2")
	List<ChoiceVote> findByOpinionEvaluacionIdAndUserId(Long opinionEvaluacionId, Long userId);

	@Query(nativeQuery=true,value="DELETE FROM choicevote WHERE opinion_choice_id = ?1 and user_id = ?2")
	void deleteByOpinionChoiceIdAndUserId(Long opinionChoiceId, Long userId);

	@Query(nativeQuery=true,value="SELECT v.*  FROM choicevote v WHERE v.opinion_choice_id = ?1 and v.user_id = ?2")
	ChoiceVote findByOpinionChoiceIdAndUserId(Long opinionChoiceId, Long userId);
	
	
		
}
