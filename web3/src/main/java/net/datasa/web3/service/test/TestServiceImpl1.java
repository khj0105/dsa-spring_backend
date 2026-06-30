package net.datasa.web3.service.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
// 특정 설정값에 따라 빈(Bean)을 등록할지 말지 결정할 때 사용
@Slf4j
@ConditionalOnProperty(
		name = "service.impl",		// application.properties에서 설정한 값
		havingValue = "impl1", 		// 속성값 = impl1 임을 식별
		matchIfMissing = true		// 속성이 존재하지 않을 때, 기본값으로 빈으로 등록
)
public class TestServiceImpl1 implements TestService{
	
	@Override
	public void testLog() {
		log.debug("1번 서비스 로직 실행");
	}
}
