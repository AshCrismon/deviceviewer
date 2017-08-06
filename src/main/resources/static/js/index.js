/**
 * Created by hadoop on 2017/8/5.
 */

function loadAllDevices() {
    $.get(
        {
            url: "/deviceviewer/device/list",
            dataType: "json",
            success: function (result) {
                var status = result.status;
                var msg = result.msg;
                $(".table-device tbody").children().remove();
                if (status === "true") {
                    refreshDeviceList(msg);
                } else {
                    console.log("load device data failed!");
                }
            }

        }
    );
}

function loadDeviceByPage(pageNo) {
    $.get(
        {
            url: "/deviceviewer/device/loadPage/" + pageNo,
            dataType: "json",
            success: function (result) {
                var status = result.status;
                var msg = result.msg;
                $(".table-device tbody").children().remove();
                if (status === "true") {

                    refreshDeviceList(msg.deviceList);
                    paginate(msg.pageNo, msg.totalPages, 10, '.page');
                    // paginate(2, 100, 20, '.page_1');
                } else {
                    console.log("load device data failed!");
                }
            }

        }
    );
}

function refreshDeviceList(deviceList) {
    deviceList.forEach(function (e) {

        var idTd = $("<td></td>").text(e.id);
        var typeTd = $("<td></td>").text(e.type);
        var nameTd = $("<td></td>").text(e.name);
        var ipsTd = $("<td></td>").text(e.ips);
        var isOccupiedTd = $("<td></td>").text(e.isOccupied === 0 ? "空闲" : "占用");
        var occupierTd = $("<td></td>").text(e.occupier);
        var beginTimeTd = $("<td></td>").text(e.beginTime);
        var endTimeTd = $("<td></td>").text(e.endTime);
        var applyBtn = $("<a class='btn btn-info'>申请</a>")
            .css('width', '82px')
            .attr("data-backdrop", "false")
            .attr("data-toggle", "modal")
            .attr("data-target", "#modal-device")
            .on("click", function () {
                applyDeviceModal($(this).parent().parent());
            });

        if (e.isOccupied === 1) {
            if (e.occupierIsMe === 1) {
                applyBtn.text("解除占用");
            } else {
                applyBtn.addClass("disabled");
            }
        }
        var optionTd = $("<td></td>").append(applyBtn);

        var trDom = $("<tr></tr>")
            .append(idTd)
            .append(typeTd)
            .append(nameTd)
            .append(ipsTd)
            .append(isOccupiedTd)
            .append(occupierTd)
            .append(beginTimeTd)
            .append(endTimeTd)
            .append(optionTd);

        $(".table-device tbody").append(trDom);
    })
}

function applyDeviceModal(tr) {
    var deviceId = $(tr).children().eq(0).text();
    var deviceType = $(tr).children().eq(1).text();
    var deviceName = $(tr).children().eq(2).text();
    var deviceIPs = $(tr).children().eq(3).text();
    var applyOrNot = $(tr).children().eq(8).text() === "申请" ? 1 : 0;

    $("#modal-device .device-id").text(deviceId);
    $("#modal-device .device-type").text(deviceType);
    $("#modal-device .device-name").text(deviceName);
    $("#modal-device .device-ips").text(deviceIPs);
    $("#modal-device .device-applyOrNot").text(deviceIPs);

    if (applyOrNot === 0) {
        $("#modal-device .form-time").css("display", "none");
    } else {
        $("#modal-device .form-time").css("display", "block");
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

    $(".btn-ok").on("click", function(){
        applyOrCancelDevice($(this).text());
    });
}

function applyOrCancelDevice(op){
    if(op === "申请"){
        applyDevice();
    }else{
        cancelDevice();
    }
}

function applyDevice() {
    var deviceId = $(".device-id").text();
    var beginTime = $(".input-begin-time").val();
    var endTime = $(".input-end-time").val();
    var data = {
        "deviceId": deviceId,
        "beginTime": beginTime,
        "endTime": endTime
    };
    $(".input-begin-time").val("");
    $(".input-end-time").val("");
    $.post(
        {
            url: "/deviceviewer/device/apply",
            data: data,
            dataType: "json",
            success: function (result) {
                var status = result.status;
                var msg = result.msg;
                if (status === "true") {
                    loadDeviceByPage(1);
                }
            }

        });
}

function cancelDevice() {
    var deviceId = $(".device-id").text();
    var data = {
        "deviceId": deviceId
    };
    $.post(
        {
            url: "/deviceviewer/device/cancel",
            data: data,
            dataType: "json",
            success: function (result) {
                var status = result.status;
                var msg = result.msg;
                if (status === "true") {
                    loadDeviceByPage(1);
                }
            }
        });
}

function getSession(){
    $.get(
        {
            url: "/deviceviewer/user/getSession",
            dataType: "json",
            success: function (result) {
                var status = result.status;
                var msg = result.msg;
                if (status === "true") {
                    $(".caret").before(msg);
                }
            }
        });
}

function logout() {
    $.get(
        {
            url: "/deviceviewer/user/logout",
            dataType: "json",
            success: function (result) {
                if (result.status === "true") {
                    console.log("logout success!");
                    window.location.href = "/deviceviewer/views/login.html";
                } else {
                    console.log("logout failed!");
                }
            }

        }
    );
}

loadDeviceByPage(1);
initBindEvent();
getSession();
