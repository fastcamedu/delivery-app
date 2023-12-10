$(function () {
    const successCallback = (position) => {
        console.log(">>> successCallback");
        $.get("http://localhost:8081/apis/geocode?lat=" + position.coords.latitude + "&lng=" + position.coords.longitude, function (data) {
            console.log(">>> GeoCode: " + data);
            if (data !== "") {
                $(".current-address-text").text(data);
            } else {
                $(".current-address-text").text("알 수 없음");
            }
        });
    };

    const errorCallback = (error) => {
        console.log(error);
    };

    // 현재 주소 정보 얻기
    navigator.geolocation.getCurrentPosition(successCallback, errorCallback);

    // 검색 키워드 창 클릭
    $("input[name='search-keyword']").on('click', function (event) {
        location.href = "/search-form";
    });

    // 장바구니 클릭
    $('.cart-count-btn').on('click', function () {
       location.href = "/cart";
    });
});