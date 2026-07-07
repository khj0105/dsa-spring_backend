package net.datasa.web4.controller;

import lombok.extern.slf4j.Slf4j;
import net.datasa.web4.util.Card;
import net.datasa.web4.util.CardCompany;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {
	@GetMapping({"", "/"})
	public String home() {
		return "home";
	}
	
	@GetMapping("/enumTest")
	public String enumTest() {
		
		Card card = new Card("홍길동", CardCompany.KB);
		int amount = 50000;
		
		CardCompany company = card.getCompany();
		double fee = company.calcFee(amount);
		double total = amount + fee;
		
		log.debug("=== 결제 정보 ===");
		log.debug("결제자: {}", card.getOwner());
		log.debug("카드사: {}", company.getFullName());
		log.debug("고객센터: {}", company.getCsNumber());
		log.debug("결제금액: {}원", amount);
		log.debug("수수료 ({}%): {}원", company.getFeeRate() * 100, fee);
		log.debug("최종 청구금액: {}원", total);
		
		return "redirect:/";
	}
}
