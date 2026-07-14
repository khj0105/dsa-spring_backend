 function showExtraOptions(options) {
    let htmlMessage = '';

    if (!options || options.trim() === '') {
        htmlMessage = '▫️ 추가된 메뉴가 없습니다.';
    } else {
        // 천 단위 쉼표(,000)는 제외하고 메뉴 구분용 쉼표로만 분리
        htmlMessage = options.split(/,(?!\d{3}\b)/)
                             .map(item => `▫️ ${item.trim()}`)
                             .join('<br>');
    }

    Swal.fire({
        title: '📋 선택된 추가 메뉴',
        html: `<div>${htmlMessage}</div>`,
        icon: 'info',
        confirmButtonText: '확인',
        confirmButtonColor: '#d9383a',
        customClass: {
            popup: 'animated fadeInDown faster',
            // SweetAlert2 본문 영역에 커스텀 CSS 클래스 부여
            htmlContainer: 'swal-custom-extra-options'
        }
    });
}

function confirmDelete(orderId) {
    Swal.fire({
        title: '⚠️ 주문 내역 삭제',
        text: `주문 번호 ${orderId}번 기록을 삭제하시겠습니까?`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d9383a',
        cancelButtonColor: '#868e96',
        confirmButtonText: '삭제',
        cancelButtonText: '취소',
        reverseButtons: true        // 취소 버튼을 왼쪽, 삭제 버튼을 오른쪽에 배치
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = `/chicken/delete/${orderId}`;
        }
    });
}