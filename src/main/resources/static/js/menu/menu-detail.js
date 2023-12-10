$(function () {

    $(".header-back-icon").on("click", function () {
        history.back();
    });

    // 장바구니 담기
    $(".menu-detail-cart-btn").click(function(){
        let accessToken = Cookies.get("access-token");
        let storeId = $("#storeId").val();
        let customerId = $("#customerId").val();
        let menuId = $("#menuId").val();
        let quantity = $( ".menu-quantity-select option:selected" ).val();
        let requestData = {
            storeId: storeId,
            menuId: menuId,
            quantity: quantity,
            customerId: customerId,
        };

        $.ajax ({
            url: "http://localhost:8081/apis/carts/items",
            type: "POST",
            data: JSON.stringify(requestData),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            statusCode: {
                401: function (response) {
                    if (response.responseJSON.code === 1401) {
                        let refreshToken  = localStorage.getItem('refresh-token');
                        requestRefreshAccessToken(refreshToken);
                    }
                },
                403: function(data) {
                    location.href = "/customer/login";
                }
            },
            beforeSend: function (xhr) {
                console.log(">>> Before Send: " + accessToken);
                xhr.setRequestHeader('Authorization', 'Bearer ' + accessToken);
            },
            success: function(){
                let isConfirm = confirm("장바구니에 등록되었습니다. 장바구니로 이동하시겠습니까?");
                if (isConfirm) {
                    location.href = "/cart";
                }
            }
        });
    });

    // 수량 선택
    $(".menu-quantity-select").on("change", function () {
        let chooseMenuCount = $(this).val();
        $("span.menu-count").text(chooseMenuCount);
    }).trigger("change");
});