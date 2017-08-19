function toast(msg) {
    $(".alert-danger")
        .text(msg)
        .fadeIn(500)
        .fadeOut(5000);
}