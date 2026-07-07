/* 유효성 검사 */
/* writeForm.html , updateForm.html */

window.onload = function() {
    let h2 = document.querySelector('h2');
    h2.addEventListener('click', function() {
        location.href = '/';
    });

    // code 작성
    const msg = document.querySelector('#message');
    const cnt = document.querySelector('#cnt');
    msg.addEventListener("input", () => {
        // 글자 수 표시
        cnt.textContent = `(${msg.value.length} / 200)`;
    });

    const form = document.querySelector('form');
    form.addEventListener("submit", function(event) {
        let name = document.querySelector('#name');
        let password = document.querySelector('#password');
        let message = document.querySelector('#message');

        if (name.value.length < 3 || name.value.length > 8 || name.value.trim() == "") {
            sweetAlert(name, '이름은 3-8자로 입력해주세요.');
            event.preventDefault();
            return;
        }
        if (password.value.length < 5) {
            sweetAlert(password, '비밀번호는 5자 이상 입력해주세요.');
            event.preventDefault();
            return;
        }
        if (message.value.length < 5) {
            sweetAlert(message, '내용은 5자 이상 입력해주세요.');
            event.preventDefault();
            return;
        }
    });
}

function sweetAlert(el, msg) {
    // SweetAlert2 사용
    Swal.fire({
        toast: true,
        position: 'top',
        icon: 'info',
        title: msg,                 // title에 메시지 넣기 파라미터값임
        showConfirmButton: false,
        timer: 1500,
        timerProgressBar: true,
    });
    el.focus();
    el.select();
}