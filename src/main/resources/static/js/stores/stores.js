$(function() {
    $('#searchFilter').on('shown.bs.modal', function (event) {
        // do something...
        console.log('팝업 보여짐');
    });

    $('#searchFilter').on('hidden.bs.modal', function (event) {
        // do something...
        console.log('팝업 감쳐짐');
    });

    $('button[name="apply-filter-btn"]').on('click', function () {
        let reviewGradeFilterValue = $('input[name="review_grade_filter"]:checked').val();
        let categoryId = $('input[name="categoryId"]').val();

        console.log(">>> 필터 적용: categoryId: " + categoryId + ", reviewGradeFilterValue: " + reviewGradeFilterValue);
        console.log(Number.isInteger(reviewGradeFilterValue));
        if (isNaN(reviewGradeFilterValue)) {
            location.href = "http://localhost:8080/display/stores?categoryId=" + categoryId;
        } else {
            location.href = "http://localhost:8080/display/stores?categoryId=" + categoryId + "&reviewGradeFilterValue=" + reviewGradeFilterValue;
        }
    });

    $(".header-back-icon").on("click", function () {
        location.href = "/";
    });
});