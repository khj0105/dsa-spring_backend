package net.datasa.web2.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonForm_Messages {
	
	@NotBlank
	/*
		스프링(정확히는 Bean Validation) 내부 규칙에 따라 @Size 어노테이션의 속성들은 알파벳 순서대로 다음과 같이 번호(인덱스)가 매겨짐
			{0}: 검증 중인 필드명 (여기서는 name)
			{1}: @Size의 max 속성값 (여기서는 10)
			{2}: @Size의 min 속성값 (여기서는 3)
	 */
	@Size(min = 3, max = 10)
	@Pattern(regexp = "^[가-힣]+$")
	private String name;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$")
	private String password;
	
	@Pattern(regexp = "^010-\\d{4}-\\d{4}$")
	private String phone;
	
	public static Person toPerson(PersonForm_Messages personForm) {
		Person p = Person.builder()
				.name(personForm.getName())
				.password(personForm.getPassword())
				.phone(personForm.getPhone())
				.build();
		
		return p;
	}
}