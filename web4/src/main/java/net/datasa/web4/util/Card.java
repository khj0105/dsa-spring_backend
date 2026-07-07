package net.datasa.web4.util;

public class Card {
	private String owner;			// 카드 소유자
	private CardCompany company;	// 발급사 (enum)
	
	public Card(String owner, CardCompany company) {
		this.owner = owner;
		this.company = company;
	}
	
	public String getOwner() {return owner;}
	
	public CardCompany getCompany() {
		return company;
	}
}
