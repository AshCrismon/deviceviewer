/**
 * Created by Yadi.Sun on 2017/8/5.
 */

function getToken() {
    $.get(
        {
            url: "/ibook/user/getToken",
            dataType: "json",
            success: function (result) {
                if (result.statusCode === 200) {
                    $(".caret").before(result.token);
                } else if (result.statusCode === 401) {
                    window.location.href = "/ibook/views/login.html";
                } else {
                    toast(result.msg);
                }
            }
        });
}

function logout() {
    $.get(
        {
            url: "/ibook/user/logout",
            dataType: "json",
            success: function (result) {
                window.location.href = "/ibook/views/login.html";
            }

        }
    );
}

(function main() {
    getToken();
})();

