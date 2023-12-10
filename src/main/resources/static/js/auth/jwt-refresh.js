function requestRefreshAccessToken(refreshToken) {
    $.ajax({
        url: "http://localhost:8081/apis/auth/refresh",
        type: "POST",
        data: JSON.stringify({"refreshToken": refreshToken}),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function(data, textStatus, jqXHR){
            localStorage.setItem('refresh-token', data.refreshToken);
            Cookies.set('customerId', data.customerId);
            Cookies.set('access-token', data.accessToken)
        }
    });
}