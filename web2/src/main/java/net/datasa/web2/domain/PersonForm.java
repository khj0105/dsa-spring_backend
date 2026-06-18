package net.datasa.web2.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
    [ 검증 어노테이션 요약본 (Validation) ]
    
    1. 에러의 종류 (필드는 멤버변수)
       - 필드 에러(Field Error) : 특정 "필드(입력값)"에 대한 유효성 검증 실패 시 발생.
                                에러 정보는 BindingResult 객체에 자동으로 저장됨.
                                
    2. 주요 검증 어노테이션 종류
       @Size(min=, max=) : 문자열의 길이 또는 컬렉션의 크기 제한
       @NotNull          : null값만 허용하지 않음 (빈 문자열 "" 이나 공백 " "은 통과됨)
       @NotEmpty         : null과 빈 문자열("") 불가 (공백 " "은 통과됨)
       @NotBlank         : null, "", " " 모두 불가 (문자열 검증 시 가장 안전하고 자주 사용)
       @Past             : 과거 날짜만 가능 (현재 날짜 제외)
       @PastOrPresent    : 오늘을 포함한 과거 날짜만 가능
       @Future           : 미래 날짜만 가능 (현재 날짜 제외)
       @Max(value=)      : 숫자의 최댓값 제한
       @Min(value=)      : 숫자의 최솟값 제한
       @Pattern(regexp=) : 정규표현식을 이용한 문자열 패턴 검사
       @Valid / @Validated : 컨트롤러 매개변수나 하위 객체(Object)의 Validation을 실제 구동시킴
       
    3. 자주 사용하는 정규식 예시 (Java 코드 작성 기준 - 역슬래시 2개 필수, 탈출문자 문제)
       목적                  정규식(regexp)                 				설명
       -------------------------------------------------------------------------------------------------
       한글만                 "^[가-힣]+$"                  				한글만 허용 (공백 불가)
       영어만                 "^[a-zA-Z]+$"                 				영문자만 허용
       숫자만                 "^[0-9]+$"                    				숫자만 허용
       영문자+숫자            "^[a-zA-Z0-9]+$"              				영어와 숫자 조합 허용
       전화번호 (하이픈 없이)   "^010\\d{3,4}\\d{4}$"         				예: 01012345678 (3,4자리 호환)
       전화번호 (하이픈 포함)   "^010-\\d{3,4}-\\d{4}$"       				예: 010-1234-5678 (3,4자리 호환)
       이메일                 "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$" 	일반적인 이메일 형식
       주민등록번호            "^\\d{6}-\\d{7}$"             				앞 6자리 - 뒤 7자리 형식
 */
@Getter
@Setter
@ToString
public class PersonForm {
	//	@Pattern(regexp = "^[a-zA-Z]+$", message = "영문자만 입력 가능합니다")	// 대소문자 포함한 영문자만 허용하는 정규 표현식
	@Pattern(regexp = "^[가-힣]+$", message = "한글만 입력 가능합니다.")
	@NotBlank(message = "입력해 주세요.")
	@Size(min = 3, max = 10, message = "이름은 3자 이상, 10자 이하로 입력해 주세요.")
	String name;
	
	//	@Pattern(regexp = "^010\\d{4}\\d{4}$", message = "전화번호 형식은 01012345678 형식입니다")
	@Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호 형식은 010-1234-5678 형식입니다")
	String phone;
	
	// @Pattern: 비밀번호 (영문, 숫자, 특수문자 포함 8~20자)
	@NotBlank(message = "입력해 주세요.")
	@Pattern(
			regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$",
			message = "비밀번호는 8~20자이며, 영문/숫자/특수문자를 포함해야 합니다"
	)
	private String password;
	
	public static Person toPerson(PersonForm personForm) {
		Person p = new Person();
		
		p.setName(personForm.getName());
		p.setPassword(personForm.getPassword());
		p.setPhone(personForm.getPhone());
		
		return p;
	}
}