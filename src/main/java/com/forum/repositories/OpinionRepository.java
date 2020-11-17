package com.forum.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.forum.domain.Opinion;
import com.forum.domain.OpinionEvaluacion;

public interface OpinionRepository extends JpaRepository<Opinion, Long> , CustomOpinionRespository{
	
	@Query(nativeQuery = true, value="SELECT * FROM opinion LIMIT 20")
	 List<Opinion> findAllLimit();
	

	@Query("SELECT oe, te, oc, c FROM  OpinionEvaluacion oe JOIN FETCH oe.tipoEvaluacion te JOIN FETCH oe.opinionChoices oc JOIN FETCH oc.choice c  WHERE oe.id = ?1 ORDER BY  oe.tipoEvaluacion, c.orderChoice ")
	OpinionEvaluacion findByOpinionEvaluacionIdWithOpinionEvaluacionWithTipoEvaluacionWithOpinionChoicesWithChoice(Long id);
	
	
//	@Query(nativeQuery = true, value="SELECT o.*, oe.*, te.*, oc.*, c.* " + 
//			" FROM forum.opinion o " + 
//			" INNER JOIN forum.opinion_evaluacion oe on (oe.opinion_id = o.id) " + 
//			" INNER JOIN forum.tipo_evaluacion te on (te.id = oe.tipo_evaluacion_id) " + 
//			" INNER JOIN forum.opinion_choice oc on (oc.opinion_evaluacion_id = oe.id) " + 
//			" INNER JOIN forum.choice c on (oc.choice_id = c.id) " + 
//			" ORDER BY  oe.tipo_evaluacion_id, c.order_choice")
//	 List<Opinion> findAllOrderChoices();
	
	@Query(nativeQuery = true, value="SELECT oc.id as id1, oc.amount, oc.choice_id, oc.opinion_evaluacion_id, " + 
			"    c.id as id2, c.codigo_nemotecnico,c.name,c.order_choice,c.tipo_evaluacion_id, " + 
			"    oe.id as id3, oe.tipo_evaluacion_id tipo3, oe.opinion_id   " + 
			" FROM       forum.opinion_evaluacion oe " + 
			" INNER JOIN forum.opinion_choice oc on (oc.opinion_evaluacion_id = oe.id) " + 
			" INNER JOIN forum.choice c on (oc.choice_id = c.id) " +
			" WHERE oe.opinion_id = ?1 "+
			" ORDER BY  oe.tipo_evaluacion_id, c.order_choice")
	 List<Object> findAllOpinionChoicesById(Long opinionId);


	@Deprecated
	List<Opinion> findByUserId(Long id);
	
	@Deprecated
	List<Opinion> findFirst10ByOrderByCreationDateDesc();
	@Deprecated
	List<Opinion> findFirst10ByTextIgnoreCaseContaining(String text);
	
	List<Opinion> findByTextIgnoreCaseContaining(String text,Pageable pageable);
	List<Opinion> findByOrderByCreationDateDesc(Pageable pageable);

	
	@Query("select o from Opinion o join fetch o.user u order by o.creationDate desc")
	public List<Opinion> fetchByWithUser();

	public List<Opinion> findByOriginOpinionIdOrderByCreationDateDesc(Long opinionId,Pageable pageable);
	
	@Query("select o from Opinion o where o.user.id = :userId  order by o.creationDate desc")
	public List<Opinion> findByUserIdOrderByCreationDateDesc(@Param("userId") Long userId,Pageable pageable);
	
	
	/*@Query(value=
			" Select o.* "+
			" From Opinion o  "+
			" Where o.id in( "+
			" Select o.id  "+
			" from       Opinion o "+ 
			" inner join Opinion_Evaluacion oe on (oe.opinion_id = o.id) "+ 
			" inner join Opinion_Choice oc     on (oc.opinion_evaluacion_id = oe.id) "+ 
			" inner join Choice c             on (c.id = oc.choice_id)  "+
			" where c.id = ?1 and oc.amount > 0 order by oc.amount desc "+
			" ) " +
			 " limit 120 "	    
	, nativeQuery = true)
	public List<Opinion> fetchOpinionsResultByChoiceId(Long choiceId);*/
	
	@Query(value=
			" Select o.* "+
			" From opinion o  "+
			" Where o.id in( "+
			" Select o.id  "+
			" from       opinion o "+ 
			" inner join opinionevaluacion oe on (oe.opinion_id = o.id) "+ 
			" inner join opinionchoice oc     on (oc.opinionEvaluacion_id = oe.id) "+ 
			" inner join choice c             on (c.id = oc.choice_id)  "+
			" where c.id IN ( ?1 ) and oc.amount > 0 order by oc.amount desc "+
			" ) " +
			 " limit ?2,?3 "	    
	, nativeQuery = true)
	public List<Opinion> fetchOpinionsResultByChoiceId(List<Long> choiceId,int pageNumber, int pageSize);
	
	@Query(value=
			" Select o.* "+
			" From opinion o  "+
			" Where o.id in( "+
			" Select o.id  "+
			" from       opinion o "+ 
			" inner join opinionevaluacion oe on (oe.opinion_id = o.id) "+ 
			" inner join opinionchoice oc     on (oc.opinionEvaluacion_id = oe.id) "+ 
			" inner join choice c             on (c.id = oc.choice_id)  "+
			" where c.id IN ( :choiceId ) and o.text like '%:text%' and oc.amount > 0 order by oc.amount desc "+
			" ) " +
			 " limit :pageNumber, :pageSize "	    
	, nativeQuery = true)
	public List<Opinion> fetchOpinionsResultByChoiceIdAndText(List<Long> choiceId,String text,int pageNumber, int pageSize);
	

	@Query(value=
			" Select o.* "+
			" From Opinion o "+
			" Where o.id in( "+
			" Select o.id  "+
			" from       Opinion o "+ 
			" inner join opinionevaluacion oe on (oe.opinion_id = o.id) "+ 
			" inner join opinionchoice oc     on (oc.opinionEvaluacion_id = oe.id) "+ 
			" inner join Choice c              on (c.id = oc.choice_id)  "+
			" where o.originOpinionId = ?1 c.id = ?2 and oc.amount > 0 order by oc.amount desc "+
			" )  limit 120 "	    
	, nativeQuery = true)
	public List<Opinion> fetchOpinionsResultByOpinionIdChoiceId(Long opinionId, Long choiceId);
	
	
	@Query("select o from Opinion o join fetch o.user u join fetch o.evaluacionesDeOpinion oe join fetch oe.opinionChoices oc join fetch oc.choice c where c.id = ?1 order by oc.amount desc ")
	public List<Opinion> findByWithOpinionWithUserWithOpinionEvaluacionWithOpinionChoiceWithChoice(Long id);
	
	
	
	
	
	
	
}
