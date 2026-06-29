package net.datasa.web3.service.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@ConditionalOnProperty(name = "service.impl", havingValue = "impl1", matchIfMissing = true)
public class TestServiceImpl1 implements TestService{
	
	@Override
	public void testLog() {
		log.debug("1번 서비스 로직 실행");
	}
}
