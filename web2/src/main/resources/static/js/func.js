// 페이지가 완전히 로드되었을 때 실행
window.addEventListener("DOMContentLoaded", function() {
    // 1. 브라우저 콘솔(F12)에 로그 출력 (연동 확인용)
    console.log("★ func.js 파일이 성공적으로 연동되었습니다! ★");

    // 2. 이미지 클릭 시 알림창 띄우는 이벤트 추가
    const images = document.querySelectorAll("img");

    images.forEach(function(img) {
        // 이미지를 누르면 손가락 모양 커서로 변경
        img.style.cursor = "pointer";

        // 클릭 이벤트 리스너 등록
        img.addEventListener("click", function() {
            const animalName = img.getAttribute("alt");
            alert("귀여운 " + animalName + " 사진을 클릭하셨습니다!");
        });
    });
});