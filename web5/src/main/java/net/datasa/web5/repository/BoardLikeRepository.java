package net.datasa.web5.repository;

import net.datasa.web5.domain.entity.BoardEntity;
import net.datasa.web5.domain.entity.BoardLikeEntity;
import net.datasa.web5.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
	추천 이력 Repository
 */
@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLikeEntity, Integer>{
	
	// 특정 회원과 특정 게시글로 추천 이력이 있는지 조회하는 메서드
	Optional<BoardLikeEntity> findByBoardAndMember(BoardEntity board, MemberEntity member);
}
