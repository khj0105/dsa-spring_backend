package net.datasa.web4.exception;

// 이미 추천한 게시글일 경우
public class RecommendException extends RuntimeException{
	public RecommendException(String message) {
		super(message);
	}
}
