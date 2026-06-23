/* JS */

/* 유효성 검사 작성 */
/* 이름은 3자 이상 10자 이하, 이메일은 '@'를 포함, 메시지는 5자 이상 */

function formCheck() {
    let name = document.querySelector("#name").value.trim();
    let email = document.querySelector('#email').value.trim();
    let message = document.querySelector('#message').value.trim();

    // 이름 3 - 10자
    if (name.length < 3 || name.length > 10) {
        alert("이름은 3자 이상 10자 이하로 작성해주세요.");
        return false;
    }

    // 이메일 : @포함
    if (!email.includes("@")) {
        alert("올바른 이메일 형식을 입력해주세요.");
        return false;
    }

    // 메시지 : 5자 이상
    if (message.length < 5) {
        alert("문의 내용은 5자 이상 입력해주세요.");
        return false;
    }

    alert("문의가 등록되었씁니다.");
    return true;
}