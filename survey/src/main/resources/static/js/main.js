/* main.html 용 JS */
window.onload = function () {
    const addForm = document.querySelector('#addForm');

    addForm.addEventListener('submit', function(e) {
        const name = document.querySelector('#name').value.trim();
        const gender = document.querySelector('#gender').value.trim();
        const age = document.querySelector('#age').value.trim();
        const favoriteScent = document.querySelector('#favoriteScent').value.trim();
        const favoriteBrand = document.querySelector('#favoriteBrand').value.trim();

        if(name === "") {
            alert("이름을 입력하세요.");
            e.preventDefault();
            return;
        }

        if(gender === "") {
            alert("성별을 선택하세요.");
            e.preventDefault();
            return;
        }

        if(age === "") {
            alert("나이를 입력하세요.");
            e.preventDefault();
            return;
        }

        if(favoriteScent === "") {
            alert("향수를 선택하세요.");
            e.preventDefault();
            return;
        }

        if(favoriteBrand === "") {
            alert("브랜드를 입력하세요.")
            e.preventDefault();
            return;
        }


    })
}

