package com.forum.repositories;

import java.util.List;

import com.forum.domain.Opinion;
import com.forum.model.OpinionFilterModel;

public interface CustomOpinionRespository {
	public List<Opinion> opinionFiltersCustomRepo(OpinionFilterModel opinionFilterModel);
}
