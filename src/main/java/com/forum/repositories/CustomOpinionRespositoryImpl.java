package com.forum.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.forum.domain.Opinion;
import com.forum.model.OpinionFilterModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CustomOpinionRespositoryImpl implements CustomOpinionRespository{

	 @PersistenceContext
	 private EntityManager em;

	
	@Override
	public List<Opinion> opinionFiltersCustomRepo(OpinionFilterModel opinionFilterModel) {

		String query = "from Opinion opn where opn.id in ("
				+ "Select o.id  "
				+ "from Opinion o "
				+ "inner join OpinionEvaluacion oe on (oe.opinion.id = o.id) "
				+ "inner join OpinionChoice oc     on (oc.opinionEvaluacion.id = oe.id) "
				+" inner join Choice c             on (oc.choice.id = c.id)  "
				+ "inner join o.themes ot";
		
		if(opinionFilterModel.isSet()) {
			query+=" where ";
			boolean isAndRequiered = false;
			
			if(opinionFilterModel.getText()!=null) {
				
				
				query+="o.text like :text ";
				
				isAndRequiered = true;
			}
			
			if(opinionFilterModel.getChoiceId()!=null && opinionFilterModel.getChoiceId().length>0) {
				if(isAndRequiered) {
					query+="and ";
				}
				query+="c.id in :choiceId and oc.amount > 0 ";
				isAndRequiered = true;
			}
			
			if(opinionFilterModel.getTheams()!=null && opinionFilterModel.getTheams().length>0) {
				
				if(isAndRequiered) {
					query+="and ";
				}
				
				query+="ot.name in :themeName ";
				
				isAndRequiered = true;
			}
			
			if(opinionFilterModel.getTags()!=null && opinionFilterModel.getTags().length>0) {
				
				if(isAndRequiered) {
					query+="and ";
				}
				
				query+="o.tags like :tags ";
				
				isAndRequiered = true;
			}
		}
		query+= ")" ;
		//query+=" limit :pageNumber, :pageSize ";
		
		query +=" order by id desc";
		Query q = em.createQuery(query);
		
		
		if(opinionFilterModel.isSet()) {
			
			if(opinionFilterModel.getChoiceId()!=null && opinionFilterModel.getChoiceId().length>0) {
				q.setParameter("choiceId", Arrays.asList(opinionFilterModel.getChoiceId()));
			}
			if(opinionFilterModel.getText()!=null) {
				q.setParameter("text", "%"+opinionFilterModel.getText()+"%");
			}
			if(opinionFilterModel.getTheams()!=null && opinionFilterModel.getTheams().length>0) {
				//q.setParameter("themeName", opinionFilterModel.getTheams());
				q.setParameter("themeName", Arrays.asList(opinionFilterModel.getTheams()));
				
			}
			if(opinionFilterModel.getTags()!=null && opinionFilterModel.getTags().length>0) {
				
				String param = "";
				
				for(String t : opinionFilterModel.getTags()) {
					param+="%"+t+"%";
				}
				
				q.setParameter("tags", param);
				
			}
		}
		
		
		q.setFirstResult(opinionFilterModel.getPageable().getPageNumber()*opinionFilterModel.getPageable().getPageSize());
		q.setMaxResults(opinionFilterModel.getPageable().getPageSize());
		
		List<Opinion> opinions = q.getResultList();
		
		return opinions;
	}

}
