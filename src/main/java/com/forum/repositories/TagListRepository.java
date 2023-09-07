package com.forum.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forum.domain.TagsList;

public interface TagListRepository extends JpaRepository<TagsList, Long> {
	
	List<TagsList> findByValueIgnoreCaseContaining(String value);
	List<TagsList> findByValue(String value);
	
}
