package net.datasa.web5.repository;

import net.datasa.web5.domain.entity.ReplyEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
	댓글 Repository
 */
@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Integer>{
	
	// 게시글 정보에 필요한 댓글 목록
	@EntityGraph(attributePaths = {"member"})
	List<ReplyEntity> findByBoard_BoardNumOrderByCreateDateDesc(int boardNum);
}
