jQuery(document).ready(function () {

    /*
     Fullscreen background
     */
    $.backstretch("../img/login_background.jpg");

    /*
     Form validation
     */
    $('.register-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function () {
        $(this).removeClass('input-error');
    });

});

function register() {

    var name = $("#form-name").val().trim();
    var username = $("#form-username").val().trim();
    var password = $("#form-password").val().trim();
    var repassword = $("#form-repassword").val().trim();

    if (name == "" || username == "" || password == "" || repassword == "") {
        toast('信息输入不能为空！');
        return;
    }

    if (password !== repassword) {
        toast('两次密码输入不一致!');
        return;
    }

    var data = {
        "name": name,
        "username": username,
        "password": password
    };

    $.post(
        {
            url: "/deviceviewer/user/register",
            data: data,
            dataType: "json",
            success: function (result) {
                if (result.status === "true") {
                    console.log("register success!");
                    window.location.href = "/deviceviewer/views/index.html";
                } else {
                    console.log("register failed!");
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
