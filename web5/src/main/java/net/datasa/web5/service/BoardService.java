package net.datasa.web5.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5.repository.BoardLikeRepository;
import net.datasa.web5.repository.BoardRepository;
import net.datasa.web5.repository.MemberRepository;
import net.datasa.web5.repository.ReplyRepository;
import net.datasa.web5.util.FileManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
	게시판 관련 서비스
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BoardService {
	
	private final MemberRepository 		mr;
	private final BoardRepository 		br;
	private final ReplyRepository 		rr;
	private final BoardLikeRepository 	blr;
	private final FileManager 			fileManager;
}
