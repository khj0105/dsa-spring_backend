window.onload = function () {
	
	document.querySelector("form").onsubmit = function (e) {
		const id = document.querySelector("#id").value.trim();
		const pw1 = document.querySelector("#pw1").value.trim();
		const pw2 = document.querySelector("#pw2").value.trim();
		const name = document.querySelector("#name").value.trim();
		const phone = document.querySelector("#phone").value.trim();

		// 아이디 유효성 검사: 3~14자, 영문/숫자/특수문자 포함 허용

		// 비밀번호 일치 검사

		// 이름 필수 입력

		// 핸드폰 필수 입력

	}
}