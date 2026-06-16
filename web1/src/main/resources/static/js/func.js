/* 외부 파일로 자바스크립트 정의 */
window.onload = function() { // 콜백함수
    let p = document.querySelector('#func');
    p.addEventListener('click', function() {
        alert('click event 발생!!');
    });
}