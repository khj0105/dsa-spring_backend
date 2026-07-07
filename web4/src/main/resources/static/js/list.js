/* list.html */

// html에서 defer를 썼으므로 window.onload는 생략 가능
// HTML에 심어둔 data-msg 값 가져오기 (Thymeleaf를 외부 js 파일에서 사용 불가)
const msg = document.body.dataset.msg;

if (msg) {
    Swal.fire({
        toast: true,
        position: 'top',
        icon: 'info',
        title: msg,     // title에 메시지 넣기
        showConfirmButton: false,
        timer: 1500,
        timerProgressBar: true,
    });
}

let h2 = document.querySelector('h2');
h2.addEventListener('click', function() {
    location.href = '/';
});

// 모든 item 요소를 가져오기
let items = document.querySelectorAll('.item');

// 각 item에 랜덤 색상을 적용
items.forEach(item => {
    item.style.backgroundColor = getRandomColor();
});

function getRandomColor() {
    const r = Math.floor(Math.random() * 256);      // red
    const g = Math.floor(Math.random() * 256);      // green
    const b = Math.floor(Math.random() * 256);      // blue
    const alpha = (Math.random() * 0.2 + 0.1).toFixed(2);

    return `rgba(${r}, ${g}, ${b}, ${alpha})`;
}