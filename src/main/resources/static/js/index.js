/**
 * Created by hadoop on 2017/8/5.
 */

function loadAllDevices() {
    $.get(
        {
            url: "/deviceviewer/device/list",
            dataType: "json",
            success: function (result) {
                var msg = result.msg;
                $(".table-device tbody").children().remove();
                if (result.statusCode === 200) {
                    refreshDeviceList(msg);
                } else if(result.statusCode === 401){
                    window.location.href = "/deviceviewer/views/login.html";
                } else{

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
                $(".table-device tbody").children().remove();
                var data = result.data;
                if (result.statusCode === 200) {
                    refreshDeviceList(data.deviceList);
                    paginate(data.pageNo, data.totalPages, 10, '.page');
                    // paginate(2, 100, 20, '.page_1');
                } else if(result.statusCode === 401){
                    window.location.href = "/deviceviewer/views/login.html";
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
        var deviceHostIPsTD = $("<td class='td-host-ips'></td>").html(e.deviceHostIPs + "<br/>" + e.hostAccount);
        var controllerIPsTD = $("<td class='td-controller-ips'></td>").html(e.controllerIPs + "<br/>" + e.controllerAccount);
        // var isOccupiedTD = $("<td></td>").text(e.isOccupied === 0 ? "空闲" : "占用");
        var isOccupiedTD = $("<td></td>");
        var occupyStateDiv = $("<div></div>");
        if(e.isOccupied){
            isOccupiedTD.html(occupyStateDiv.addClass("circle-red"));
        }else{
            isOccupiedTD.html(occupyStateDiv.addClass("circle-green"));
        }
        
        var occupierUsernameTD = $("<td></td>").text(e.occupierUsername);
        var occupierNameTD = $("<td></td>").text(e.occupierName);
        var beginTimeTD = $("<td></td>").text(e.beginTime);
        var endTimeTD = $("<td></td>").text(e.endTime);
        var noteTD = $("<td></td>").text(e.note);

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
        var optionTD = $("<td></td>").append(applyBtn);

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

        if(e.note === "CI"){
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
    var deviceHostIPs = $(tr).children().eq(4).text();
    var controllerIPs = $(tr).children().eq(5).text();
    var applyOrNot = $(tr).children().eq(12).text();

    $("#modal-device .device-id").text(deviceId);
    $("#modal-device .device-type").text(deviceType);
    $("#modal-device .device-name").text(deviceName);
    $("#modal-device .device-group").text(deviceGroup);
    $("#modal-device .device-host-ips").text(deviceHostIPs);
    $("#modal-device .device-controller-ips").text(controllerIPs);
    $("#modal-device .device-applyOrNot").text(applyOrNot);

    if (applyOrNot === "申请") {
        $("#modal-device .form-time").css("display", "block");
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

    $(".btn-ok").on("click", function(){
        applyOrCancelDevice($("#modal-device .device-applyOrNot").text());
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
                if (result.statusCode === 200) {
                    loadDeviceByPage(1);
                }else if(result.statusCode === 401){
                    window.location.href = "/deviceviewer/views/login.html";
                } else{
                    toast(result.msg);
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
                var msg = result.msg;
                if (result.statusCode === 200) {
                    loadDeviceByPage(1);
                }else if(result.statusCode === 401){
                    window.location.href = "/deviceviewer/views/login.html";
                } else{

                }
            }
        });
}

function getToken(){
    $.get(
        {
            url: "/deviceviewer/user/getToken",
            dataType: "json",
            success: function (result) {
                if (result.statusCode === 200) {
                    $(".caret").before(result.token);
                }else if(result.statusCode === 401){
                    window.location.href = "/deviceviewer/views/login.html";
                } else{
                    
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
                if (result.statusCode === 200) {
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
getToken();
