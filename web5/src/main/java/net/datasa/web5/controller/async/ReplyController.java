package net.datasa.web5.controller.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5.domain.dto.ReplyDTO;
import net.datasa.web5.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
	
	private final BoardService bs;
	
	/*
		댓글 목록 조회
		@param boardNum 조회할 게시글 번호
		@return 응답객체	(댓글 목록)
	 */
	@ResponseBody
	@GetMapping("/list/{boardNum}")
	public ResponseEntity<List<ReplyDTO>> getList(
			@PathVariable("boardNum") int boardNum
	) {
		List<ReplyDTO> list = bs.getReplyList(boardNum);
		return ResponseEntity.ok(list);
	}
	
	// --------------------------------------------------------
	/*
		댓글 저장
		@param replyDTO	저장할 댓글 정보
		@param user	로그인한 사용자 정보
		@return 응답 객체
	 */
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@PostMapping("/write")
	public ResponseEntity<String> replyWrite(
			@RequestBody ReplyDTO replyDTO
			, @AuthenticationPrincipal UserDetails user
	) {
		replyDTO.setMemberId(user.getUsername());
		
		bs.replyWrite(replyDTO);
		
		return ResponseEntity.ok("success");
	}
	
	// 삭제
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteReply (
		@AuthenticationPrincipal UserDetails user
	) {
		reply
		
		bs.
		
		return ResponseEntity.ok("delete");
	}
	
	
	// 수정
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@PatchMapping
}
