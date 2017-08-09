function toast(msg) {
    $(".alert-danger")
        .text(msg)
        .fadeIn(1000)
        .fadeOut(2000);
}