package net.datasa.web4.util;

public enum CardCompany {
	
	KB("KB국민카드", "1588-9999", 0.01),
	SHINHAN("신한카드", "1544-7000", 0.012),
	WOORI("우리카드", "1588-9955", 0.009);
	
	private final String fullName;		 // 회사 정식 이름
	private final String csNumber; 		 // 고객센터 번호
	private final  double feeRate; // 거래 수수료율
	
	// enum 생성자는 private이기 때문에 외부에서 new 못함
	CardCompany(String fullName, String csNumber, double feeRate) {
		this.fullName = fullName;
		this.csNumber = csNumber;
		this.feeRate = feeRate;
	}
	
	// enum도 클래스 타입이라서 메서드를 가질 수 있음
	public String getFullName() { return fullName; }
	public String getCsNumber() { return csNumber; }
	public double getFeeRate() { return feeRate; }
	
	public double calcFee(double amount) {
		return amount + feeRate;
	}
	
}
