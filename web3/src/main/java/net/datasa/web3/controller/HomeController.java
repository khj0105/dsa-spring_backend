package net.datasa.web3.controller;

import lombok.extern.slf4j.Slf4j;
import net.datasa.web3.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {
	
	// application.properties의 "service.impl" 키에 해당하는 값을
	// 빌드시 변수에 자동으로 주입
	@Value("${service.impl}")
	private String serviceName;
	
	// 스프링이 관리하는 어플리케이션의 모든 설정 정보(properties, 시스템 환경변수)
	// 를 통째로 가지고 있는 객체
	@Autowired
	private Environment env;
	
	@Autowired
	private TestService ts;
	
	
	
	@GetMapping({"", "/"})
	public String home() {
		
		log.debug("application.properties에서 작성한 값: {}", serviceName);
		
		String serviceName2 = env.getProperty("service.impl");
		log.debug("Environment에서 가져온 값: {}", serviceName2);
		
		ts.testLog();
		
		return "home";
	}
}
