$(function (){
    $("#login-btn").click(function (){
        let email = $("#email").val();
        let password = $("#password").val();
        let requestData = {
            email: email,
            password: password,
        };

        $.ajax ({
            url: "http://localhost:8081/apis/auth",
            type: "POST",
            data: JSON.stringify(requestData),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function(data, textStatus, jqXHR){
                console.log(">>> 로그인 응답: " + data);
                localStorage.setItem('refresh-token', data.refreshToken);
                Cookies.set('customerId', data.customerId);
                Cookies.set('access-token', data.accessToken);
                location.href = "/";
            }
        });
    });
})