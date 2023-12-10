$(function () {
    $(".header-back-icon").on("click", function () {
        location.href = "/";
    });

    $(".search-keyword").on('click', function () {
        let hitKeyword = $(this).text();
        $(".search-keyword-input").val(hitKeyword);
        $(".search-form").submit();
    });

    $(".search-form").on('submit', function (event) {
        let searchKeyword = $('.search-keyword-input').val();
        if (searchKeyword !== "") {
            return;
        }

        alert("검색 키워드를 입력하셔야 합니다.");
        $(".search-keyword-input").focus();
        event.preventDefault();
    });
});
