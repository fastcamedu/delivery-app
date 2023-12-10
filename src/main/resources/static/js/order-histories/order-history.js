$(function () {

    $(".header-back-icon").on("click", function () {
        location.href = "/";
    });

    // 주문 상세 팝업
    $(".order-history-detail-btn").click(function () {
        let accessToken = Cookies.get("access-token");
        let customerId = Cookies.get("customerId")
        let orderId = $(this).data('order-id');
        let orderDetailUrl = "http://localhost:8081/apis/order-histories/" + orderId;
        $.ajax({
            url: orderDetailUrl,
            beforeSend: function (xhr) {
                console.log(">>> Before Send: " + accessToken);
                xhr.setRequestHeader('Authorization', 'Bearer ' + accessToken);
            },
        })
        .done(function( data ) {
            console.log(data);
            $("#myOrderModal .order-desc").html("");
            let orderDetailContent = "<h5>" + data.storeName + " <span class='badge text-bg-primary'>" + data.orderStatus +"</span></h5>";
            data.menuNames.forEach((menuName) => {
                orderDetailContent += "<span>" + menuName + "</span><br>"
            });
            orderDetailContent += "<br>";
            orderDetailContent += "<h5> 총 가격: " + data.totalOrderAmount + "</h5>";
            $("#myOrderModal .order-desc").html(orderDetailContent);
        });
    });

    $('#myOrderModal').on('hidden.bs.modal', function (e) {
        // do something...
        console.log(">>> 주문 상세 팝업 닫기");
    });

    $('#myOrderModal').on('show.bs.modal', function (e) {
        // do something...
        console.log(">>> 주문 상세 팝업 열기");

    });
});