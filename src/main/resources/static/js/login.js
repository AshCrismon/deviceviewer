/**
 * Created by Yadi.Sun on 2017/7/30.
 */

jQuery(document).ready(function () {

    $.backstretch("../img/login_background.jpg");

    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function () {
        $(this).removeClass('input-error');
    });

});

function login() {
    var username = $('.login-form .form-username').val().trim();
    var password = $('.login-form .form-password').val().trim();
    validate(username, password);
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

function validate(username, password){
    if(username === "" || password === ""){
        toast("用户名和密码不能为空！");
    }
    return;
}


