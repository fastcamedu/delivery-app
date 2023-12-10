$(function () {

    // 뒤로 가기
    $(".header-back-icon").on("click", function () {
        history.back();
    });

    // 결제 버튼 클릭
    $("#payment-btn").click(function(){
        let accessToken = Cookies.get("access-token");
        let customerId = Cookies.get("customerId")
        let checkoutId = $("input[name='checkoutId']").val()
        let requestData = {
            "customerId": customerId,
            "checkoutId": checkoutId,
            "paymentMethod": "CREDIT_CART"
        }

        $.ajax ({
            url: "http://localhost:8081/apis/payments",
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
            success: function(data){
                location.href = "/order-histories";
            }
        });
    });
});