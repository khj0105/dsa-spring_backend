window.onload = function () {
	
	document.querySelector("form").onsubmit = function (e) {
		const id = document.querySelector("#id").value.trim();
		const pw1 = document.querySelector("#pw1").value.trim();
		const pw2 = document.querySelector("#pw2").value.trim();
		const name = document.querySelector("#name").value.trim();
		const phone = document.querySelector("#phone").value.trim();

		// 아이디 유효성 검사: 3~14자, 영문/숫자/특수문자 포함 허용
        if (id.length < 3 || id.length > 14) {
            alert("아이디는 3자 이상, 14자 이하로 입력해주세요.");
            e.preventDefault();
            return;
        }
		// 비밀번호 일치 검사
		if (pw1 == "") {
		    alert("비밀번호를 입력해주세요.");
		    e.preventDefault();
		    return;
		}
		if (pw1 !== pw2) {
            alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            e.preventDefault();
            return;
		}

		// 이름 필수 입력
		if (name === "") {
		    alert("이름을 입력해주세요.");
		    e.preventDefault();
		    return;
		}

		// 핸드폰 필수 입력
        if (phone === "") {
            alert("핸드폰 번호를 입력해주세요.");
            e.preventDefault();
            return;
        }
	}
}