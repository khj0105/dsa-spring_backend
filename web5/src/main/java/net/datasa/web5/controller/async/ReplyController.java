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
import org.springframework.ui.Model;
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
	
	// --------------------------------------------------------------------
	/*
		댓글 삭제
		@param replyNum			삭제할 댓글 번호
		@param user				로그인한 사용자 정보
		@return 응답 객체
	 */
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@DeleteMapping("/delete" +"/{replyNum}")
	public ResponseEntity<String> replyDelete (
		@AuthenticationPrincipal UserDetails user
		,@PathVariable("replyNum") int replyNum
	) {
		
		bs.replyDelete(replyNum, user.getUsername());
		
		return ResponseEntity.ok("success");
	}
	
	// ----------------------------------------------------------
	/*
	 	댓글 수정
	 	@param replyDTO		수정할 댓글 정보
	 	@param user			로그인한 사용자 정보
	 	@return 응답 객체
	 */
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@PutMapping("/update")
	public ResponseEntity<String> replyUpdate (
			@AuthenticationPrincipal UserDetails user
			,@RequestBody ReplyDTO replyDTO
	){
		
		bs.replyUpdate(replyDTO, user.getUsername());
		
		return ResponseEntity.ok("success");
	}
	
	// ------------------------------------------------
	/*
		해당 회원의 댓글 목록 페이지 이동
		@param memberId		조회할 아이디
	 	@return replyList.html
	 */
	@GetMapping("/userReplyList")
	public String userReplyList (
			@RequestParam("memberId") String memberId,
			Model model
	) {
		// 특정 회원의 댓글 목록
		List<ReplyDTO> replyList = bs.userReplyList(memberId);
		// 특정 회원의 댓글 수
		int replyCount = bs.replyCount(memberId);
		
		model.addAttribute("id", memberId);
		model.addAttribute("count", replyCount);
		model.addAttribute("replyList", replyList);
		
		return "boardView/replyList";
	}
}
