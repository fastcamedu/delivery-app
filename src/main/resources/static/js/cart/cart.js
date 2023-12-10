$(function () {

    // 뒤로 가기
    $(".header-back-icon").on('click', function () {
        history.back();
    });

    // 결제하기
    $("#checkout-btn").click(function(){
        let accessToken = Cookies.get("access-token");
        let customerId = Cookies.get("customerId");
        let requestData = {
            "customerId": customerId,
            "discountId": 0,
        };
        $.ajax ({
           url: "http://localhost:8081/apis/checkouts",
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
               xhr.setRequestHeader('Authorization', 'Bearer ' + accessToken);
           },
           success: function(data){
               location.href = "/checkouts";
           }
        });
    });

    // 장바구니 아이템 삭제
    $('input[name="delete-cart-item-btn"]').on('click', function () {
        let accessToken = Cookies.get("access-token");
        let customerId = Cookies.get("customerId");
        let removeCartItemId = $(this).data("cart-item-id");
        let removeCartItemUrl = "http://localhost:8081/apis/carts/cart-items";
        let requestData = {
            "customerId": customerId,
            "cartItemId": removeCartItemId
        };

        $.ajax ({
           url: removeCartItemUrl,
           type: "PUT",
            data: JSON.stringify(requestData),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
           beforeSend: function (xhr) {
               xhr.setRequestHeader('Authorization', 'Bearer ' + accessToken);
           },
           success: function (data) {
               console.log(">>> 장바구니 삭제 응답 : " + data);
               location.reload();
           },
        });
    });
});