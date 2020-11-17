package com.forum.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.forum.domain.Opinion;
import com.forum.domain.WallOpinion;

public interface WallOpinionRepository extends JpaRepository<WallOpinion, Long> {
	
	@Query(nativeQuery = true, value="SELECT * FROM wallopinion LIMIT 20")
	List<Opinion> findAllLimit();
	
	List<Opinion> findByPaisIdAndUserId(Long paisId, Long userId);
	
	List<Opinion> findByPaisId(Long paisId);
	
	
	
}
