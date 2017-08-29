/**
 * Created by hadoop on 2017/8/5.
 */

function loadAllDevices() {
    $.get(
        {
            url: "../device/list",
            dataType: "json",
            success: function (result) {
                var msg = result.msg;
                $(".table-device tbody").children().remove();
                if (result.statusCode === 200) {
                    refreshDeviceList(msg);
                } else if (result.statusCode === 401) {
                    window.location.href = "../views/login.html";
                } else {

                }
            }

        }
    );
}

function loadDeviceByPage(pageNo) {
    $.get(
        {
            url: "../device/loadPage/" + pageNo,
            dataType: "json",
            success: function (result) {
                $(".table-device tbody").children().remove();
                var data = result.data;
                if (result.statusCode === 200) {
                    refreshDeviceList(data.deviceList);
                    paginate(data.pageNo, data.totalPages, 10, '.page');
                    // paginate(2, 100, 20, '.page_1');
                } else if (result.statusCode === 401) {
                    window.location.href = "../views/login.html";
                } else {
                    toast(result.msg);
                }
            }

        }
    );
}

function refreshDeviceList(deviceList) {
    deviceList.forEach(function (e) {

        var idTD = $("<td></td>").text(e.deviceId);
        var deviceTypeTD = $("<td></td>").text(e.deviceType);
        var deviceNameTD = $("<td></td>").text(e.deviceName);
        var deviceGroupTD = $("<td></td>").text(e.deviceGroup);

        var deviceHostIPsTD = $("<td class='td-host-ips'></td>")
            .append($("<span class='ips'></span>").text(e.deviceHostIPs))
            .append("<br/>")
            .append($("<span class='account'></span>").text(e.hostAccount));

        var controllerIPsTD = $("<td class='td-controller-ips'></td>")
            .append($("<span class='ips'></span>").text(e.controllerIPs))
            .append("<br/>")
            .append($("<span class='account'></span>").text(e.controllerAccount));

        var isOccupiedTD = $("<td></td>");
        var occupyStateDiv = $("<div></div>");
        if (e.isOccupied) {
            isOccupiedTD.html(occupyStateDiv.addClass("circle-red"));
        } else {
            isOccupiedTD.html(occupyStateDiv.addClass("circle-green"));
        }

        var occupierUsernameTD = $("<td></td>").text(e.occupierUsername);
        var occupierNameTD = $("<td></td>").text(e.occupierName);
        var beginTimeTD = $("<td></td>").text(e.beginTime);
        var endTimeTD = $("<td></td>").text(e.endTime);
        var noteTD = $("<td></td>").text(e.note);

        var applyBtn = $("<a class='btn btn-info btn-apply'>申请</a>")
            .css('width', '62px')
            .attr("data-backdrop", "false")
            .attr("data-toggle", "modal")
            .attr("data-target", "#modal-device")
            .on("click", function () {
                applyDeviceModal($(this).parent().parent());
            });

        var logBtn = $("<a class='btn btn-info btn-log'>查看日志</a>")
            .css('width', '82px')
            .attr("data-backdrop", "false")
            .attr("data-toggle", "modal")
            .attr("data-target", "#modal-device-log")
            .on("click", function () {
                var tr = $(this).parent().parent();
                var deviceId = $(tr).children().eq(0).text();
                loadLogByDeviceId(deviceId, 10, e.controllerIPs);
            });

        if (e.isOccupied === 1) {
            if (e.occupierIsMe === 1) {
                applyBtn.removeClass("btn-info").addClass("btn-success").text("释放");
                logBtn.removeClass("btn-info").addClass("btn-success");
            } else {
                applyBtn.addClass("disabled");
            }
        }
        var optionTD = $("<td></td>").append(applyBtn).append(logBtn);

        var trDom = $("<tr></tr>")
            .append(idTD)
            .append(deviceTypeTD)
            .append(deviceNameTD)
            .append(deviceGroupTD)
            .append(deviceHostIPsTD)
            .append(controllerIPsTD)
            .append(isOccupiedTD)
            .append(occupierNameTD)
            .append(occupierUsernameTD)
            .append(beginTimeTD)
            .append(endTimeTD)
            .append(noteTD)
            .append(optionTD);

        if (e.note === "CI") {
            trDom.addClass("tr-importance-warn");
        }

        $(".table-device tbody").append(trDom);
    })
}

function applyDeviceModal(tr) {
    var deviceId = $(tr).children().eq(0).text();
    var deviceType = $(tr).children().eq(1).text();
    var deviceName = $(tr).children().eq(2).text();
    var deviceGroup = $(tr).children().eq(3).text();
    var deviceHostIPs = $(tr).children().eq(4).children(":first").text();
    var controllerIPs = $(tr).children().eq(5).children(":first").text();
    var applyOrNot = $(tr).children().eq(12).children(":first").text();

    $("#modal-device .device-id").text(deviceId);
    $("#modal-device .device-type").text(deviceType);
    $("#modal-device .device-name").text(deviceName);
    $("#modal-device .device-group").text(deviceGroup);
    $("#modal-device .device-host-ips").text(deviceHostIPs);
    $("#modal-device .device-controller-ips").text(controllerIPs);
    $("#modal-device .device-applyOrNot").text(applyOrNot);

    if (applyOrNot === "申请") {
        $("#modal-device .form-time").css("display", "block");
        timeSlideBar(".time-slidebar");
    } else {
        $("#modal-device .form-time").css("display", "none");
    }

}


function initBindEvent() {

    $(".logout").on("click", logout);

    $('.form-datetime').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        minuteStep: 5
    });

    $(".btn-close").on("click", function () {
        $(".input-begin-time").val("");
        $(".input-end-time").val("");
    });

    $(".btn-ok").on("click", function () {
        applyOrCancelDevice($("#modal-device .device-applyOrNot").text());
    });
}

function applyOrCancelDevice(op) {
    if (op === "申请") {
        applyDevice();
    } else {
        releaseDevice();
    }
}

function applyDevice() {
    var deviceId = $(".device-id").text();
    // var beginTime = $(".input-begin-time").val();
    // var endTime = $(".input-end-time").val();

    var now = moment().format("YYYY-MM-DD ");

    var timeVal = $(".time-slidebar").val();

    var beginTime = moment().format("YYYY-MM-DD HH:mm");
    var endTime = now + padzero(parseInt(timeVal / 60), 2) + ":" + padzero(timeVal % 60, 2);
    var data = {
        "deviceId": deviceId,
        "beginTime": beginTime,
        "endTime": endTime
    };
    // $(".input-begin-time").val("");
    // $(".input-end-time").val("");
    $.post(
        {
            url: "../device/apply",
            data: data,
            dataType: "json",
            success: function (result) {
                if (result.statusCode === 200) {
                    loadDeviceByPage(1);
                } else if (result.statusCode === 401) {
                    window.location.href = "../views/login.html";
                } else {
                    toast(result.msg);
                }
            }

        });
}

function releaseDevice() {
    var deviceId = $(".device-id").text();
    var data = {
        "deviceId": deviceId
    };
    $.post(
        {
            url: "../device/release",
            data: data,
            dataType: "json",
            success: function (result) {
                var msg = result.msg;
                if (result.statusCode === 200) {
                    loadDeviceByPage(1);
                } else if (result.statusCode === 401) {
                    window.location.href = "../views/login.html";
                } else {
                    toast(result.msg);
                }
            }
        });
}

function getToken() {
    $.get(
        {
            url: "../user/getToken",
            dataType: "json",
            success: function (result) {
                if (result.statusCode === 200) {
                    $(".caret").before(result.token);
                } else if (result.statusCode === 401) {
                    window.location.href = "../views/login.html";
                } else {
                    toast(result.msg);
                }
            }
        });
}

function logout() {
    $.get(
        {
            url: "../user/logout",
            dataType: "json",
            success: function (result) {
                window.location.href = "../views/login.html";
            }

        }
    );
}

function loadLogByDeviceId(deviceId, logNum, controllerIPs) {
    var data = {
        "deviceId": deviceId,
        "logNum": logNum
    };
    $.get(
        {
            url: "../device/loadLog",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.statusCode === 200) {
                    refreshDeviceLog(result.data, controllerIPs);
                } else if (result.statusCode === 401) {
                    window.location.href = "../views/login.html";
                } else {
                    toast(result.msg);
                }
            }

        }
    );
}

function refreshDeviceLog(logList, controllerIPs) {
    $(".table-device-log tbody").children().remove();
    logList.forEach(function (e) {
        var createTimeTD = $("<td></td>").text(e.createTime);
        var usernameTD = $("<td></td>").text(e.username);
        var actionTypeTD = $("<td></td>").text(e.actionType === 1 ? "申请" : "释放");
        var beginTimeTD = $("<td></td>").text(e.beginTime);
        var endTimeTD = $("<td></td>").text(e.endTime);

        var trDom = $("<tr></tr>")
            .append(createTimeTD)
            .append(usernameTD)
            .append(actionTypeTD)
            .append(beginTimeTD)
            .append(endTimeTD);

        $(".table-device-log tbody").append(trDom);
    });
    $("#modal-device-log .device-identifier").text(controllerIPs);
}

(function main() {
    loadDeviceByPage(1);
    initBindEvent();
    getToken();
})();

