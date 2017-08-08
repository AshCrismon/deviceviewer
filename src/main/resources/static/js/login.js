/**
 * Created by Yadi.Sun on 2017/7/30.
 */

jQuery(document).ready(function () {

    /*
     Fullscreen background
     */
    $.backstretch("../img/login_background.jpg");

    /*
     Form validation
     */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function () {
        $(this).removeClass('input-error');
    });

});

function login() {
    var username = $('.login-form .form-username').val();
    var password = $('.login-form .form-password').val();
    var data = {"username": username, "password": password};
    $.post(
        {
            url: "/deviceviewer/user/login",
            data: data,
            dataType: "json",
            success: function (result, status, xhr) {
                if (result.status === "true") {
                    console.log("login success!");
                    window.location.href = "/deviceviewer/views/index.html";
                } else {
                    console.log("login failed!");
                    toast(result.msg);
                }
            }

        }
    );
}

function toast(msg) {
    $(".alert-danger")
        .text(msg)
        .fadeIn(1000)
        .fadeOut(2000);
}
