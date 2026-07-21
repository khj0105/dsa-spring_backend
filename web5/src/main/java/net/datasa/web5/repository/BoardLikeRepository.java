package net.datasa.web5.repository;

import net.datasa.web5.domain.entity.BoardLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
	추천 이력 Repository
 */
@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLikeEntity, Integer>{
}
