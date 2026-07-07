
/* enroll.html , updateForm.html */

window.onload = function() {

    document.querySelector("#home").addEventListener("click", function() {
        location.href = '/';
    });

    document.querySelector("form").addEventListener("submit", function(e) {
        const name  = document.querySelector("#name").value.trim();
        const major = document.querySelector("#major").value.trim();
        const java  = document.querySelector("#java").value;
        const db    = document.querySelector("#db").value;
        const web   = document.querySelector("#web").value;

        // 유효성 검사
        // 이름 3~10자
        if (name.length < 3 || name.length > 10) {
            alert("이름은 3자 이상 10자 이하로 입력해주세요.");
            e.preventDefault();
            return;
        }

        // 전공 1글자 이상
        if (major.length < 1) {
            alert("전공을 입력해주세요.");
            e.preventDefault();
            return;
        }

        // 상, 중, 하
        const levels = ["상", "중", "하"];

        // java 변수에 담긴 값이 "상", "중", "하" 중 하나인지 확인
        if (!levels.includes(java)) {
            alert("자바 등급을 선택해주세요.");
            e.preventDefault();
            return;
        }
        if (!levels.includes(db)) {
                    alert("DB 등급을 선택해주세요.");
                    e.preventDefault();
                    return;
        }
        if (!levels.includes(web)) {
                    alert("WEB 등급을 선택해주세요.");
                    e.preventDefault();
                    return;
        }

    });
}


