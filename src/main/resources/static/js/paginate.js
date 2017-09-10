/**
 * Created by Yadi.Sun on 2017/8/6.
 */
function paginate($scope, pageNo, totalPages, limit, pageDomClass) {
    var pageStart = pageNo - limit / 2 > 0 ? pageNo - limit / 2 : 1;
    var pageEnd = pageStart + limit - 1 > totalPages ? totalPages : pageStart + limit - 1;
    $(pageDomClass + ' ul.pagination li.page-element').remove();
    for (var i = pageStart; i <= pageEnd; i++) {
        var a = $('<a></a>')
            .text(i)
            .on("click", function () {
                loadDeviceByPage($(this).text());
            });
        var li = $('<li class="page-element"></li>').html(a);
        $(pageDomClass + ' ul.pagination li:last').before(li);
    }
    $(pageDomClass + ' ul.pagination li:eq(' + (pageNo - pageStart + 1) + ')').addClass('active');
    $(pageDomClass + ' ul.pagination li a.previous').unbind('click').on('click', function () {
        var previous = parseInt($(pageDomClass + ' ul.pagination li.active').text()) - 1;
        if (previous >= 1) {
            loadDeviceByPage(previous);
        }
    });
    $(pageDomClass + ' ul.pagination li a.next').unbind('click').on('click', function () {
        var next = parseInt($(pageDomClass + ' ul.pagination li.active').text()) + 1;
        if (next <= totalPages) {
            loadDeviceByPage(next);
        }
    });
}
