
// 주문하기 버튼 클릭시
function orderFunc() {
	
	// 객체 가져오기
	const chickenType = document.querySelector("#chickenType");
	const quantity = parseInt(document.querySelector("#quantity").value);
	const error = document.querySelector("#error");
	const form = document.querySelector("#orderForm");

	// 메시지 초기화
	error.textContent = "";

    // 유효성 검사
    if (!chickenType.value) {
        error.textContent = "치킨 종류를 선택하세요.";
        return;
    }
    if (isNaN(quantity) || quantity < 1) {
        error.textContent = "수량은 1 이상이어야 합니다.";
        return;
    }

    // 치킨 가격, 종류 추출
    const chickenPrice = parseInt(chickenType.value);
    const chickenName = chickenType.options[chickenType.selectedIndex].text;

    // 추가 메뉴(텍스트, 가격 추출
    const extras = document.querySelectorAll("input[name='extraOption']:checked");
    let extraList = []          // 텍스트
    let extraTotal = 0;         // 가격
    for (let i = 0; i < extras.length; i++) {
        // nextElementSibling: 현재
        extraList.push(extras[i].nextElementSibling.textContent.trim());
        extraTotal += parseInt(extras[i].value);
    }

    // 배달 종류, 가격 추출
    const delivery = document.querySelector("input[name='deliveryType']:checked");
    const deliveryPrice = parseInt(delivery.value);
    const deliveryLabel = delivery.nextElementSibling.textContent.trim();

    // 총 결제 비용 합산
    const total = (chickenPrice * quantity) + extraTotal + deliveryPrice;

    // 폼 데이터 채우기
    // form.속성명 형태로 접근 가능한 것은 form 요소 내부에 name 속성이 있는 요소만 가능
    form.chickenType.value      = chickenName;
    form.chickenPrice.value     = chickenPrice;
    form.quantity.value         = quantity;
    form.extraOptions.value     = extraList.join(", ") || "없음";
    form.extraTotalPrice.value  = extraTotal;
    form.deliveryType.value     = deliveryLabel;
    form.deliveryPrice.value    = deliveryPrice;
    form.totalPrice.value       = total;

    // 모달 내용 채우기
    document.querySelector("#receiptChickenType").textContent = chickenName;
    document.querySelector("#receiptQuantity").textContent = quantity + "개";
    document.querySelector("#receiptExtras").innerHTML = extraList.length > 0 ? extraList.join("<br>") : "없음";
    document.querySelector("#receiptDelivery").textContent = deliveryLabel;
    document.querySelector("#receiptTotal").textContent = total.toLocaleString() + "원";



	// Modal 보이게
	document.querySelector("#receiptModal").style.display = "flex";
}

// 주문확정 버튼 클릭시
function closeModal() {
	// Modal 안보이게
	document.querySelector("#receiptModal").style.display = "none";
}
