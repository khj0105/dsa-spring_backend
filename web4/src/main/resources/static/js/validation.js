/* 유효성 검사 */
/* writeForm.html , updateForm.html */

window.onload = function() {
    let h2 = document.querySelector('h2');
    h2.addEventListener('click', function() {
        location.href = '/';
    });

    // code 작성
}

function sweetAlert(el, msg) {
    // SweetAlert2 사용
    Swal.fire({
        toast: true,
        position: 'top',
        icon: 'info',
        title: msg,                 // title에 메시지 넣기
        showConfirmButton: false,
        timer: 1500,
        timerProgressBar: true,
    });
    el.focus();
    el.select();
}