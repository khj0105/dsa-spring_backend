/* result.html 용 JS */

//window.onload = function () {
//    document.querySelectorAll('.delBtn').forEach(function (btn) {
//        const targetBtn = btn.target.closet('tr');
//        if (targetBtn) {
//            targetBtn.addEventListener('click', function () {
//                if (confirm('정말 삭제하시겠습니까?')) {
//                    location.href = `/perfume/delete?no=${btn.dataset.no};
//                }
//            });
//        }
//    });
//}


//window.onload = function () {
//    document.querySelectorAll('.delBtn').forEach(function(btn) {
//        const row = btn.closest('tr');
//        row.addEventListener('click', function() {
//            if(confirm('정말 삭제하시겠습니까?')) {
//                location.href = `/perfume/delete?no=${btn.dataset.no}`
//            }
//        });
//    })
//};

window.onload = function () {
    document.querySelectorAll('.delBtn').forEach(function (btn) {
        const row = btn.closest('tr');
        row.addEventListener('click', function () {
            if (confirm('정말 삭제하시겠습니까?')) {
                location.href = `/perfume/delete?no=${btn.dataset.no}`
            }
        });
    })
};