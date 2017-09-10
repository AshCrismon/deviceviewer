angular.module('pageBody', [])
    .controller('devicePageCtrl',
        function ($scope, $http) {
            $scope.loadDeviceByPage =
                function (pageNo) {
                    if (undefined === pageNo) {
                        pageNo = 1;
                    } else if (pageNo < 1 || pageNo > $scope.pagination.totalPages) {
                        return;
                    }
                    var url = '/ibook/device/loadDevices/' + pageNo;
                    $http.get(url).then(
                        function (result) {
                            /**
                             * result: {
                             *      data: {
                             *          statusCode: ,
                             *          msg: ,
                             *          token: ,
                             *          data: {
                             *
                             *              pageNo: ,
                             *              totalPages: ,
                             *          }
                             *      }
                             * }
                             */
                            var data = result.data;
                            if (data.statusCode === 200) {
                                $scope.deviceList = data.data.deviceList;
                                $scope.deviceOps = data.data.deviceOps;
                                var totalPages = data.data.totalPages;
                                var pageBarWidth = 10;
                                $scope.paginate =
                                    (function () {
                                        var pageStart = pageNo - pageBarWidth / 2 > 0 ? pageNo - pageBarWidth / 2 : 1;
                                        var pageEnd = pageStart + pageBarWidth - 1 > totalPages ? totalPages : pageStart + pageBarWidth - 1;
                                        var activePage = pageNo - pageStart + 1;

                                        var pages = [];
                                        for (var i = pageStart; i <= pageEnd; i++) {
                                            pages.push({
                                                pageNo: i,
                                                isActive: i === activePage
                                            });
                                        }
                                        var previous = {
                                            pageNo: activePage - 1,
                                            disabled: activePage === 1
                                        };

                                        var next = {
                                            pageNo: activePage + 1,
                                            disabled: activePage === totalPages
                                        };

                                        $scope.pagination = {
                                            totalPages: totalPages,
                                            pages: pages,
                                            pageBarWidth: pageBarWidth,
                                            previous: previous,
                                            next: next
                                        };

                                    })();
                            } else if (result.statusCode === 401) {
                                window.location.href = '/ibook/views/login.html';
                            } else {
                                toast(result.msg);
                            }
                        }
                    );

                };
            $scope.loadDeviceByPage();

            $scope.refreshDeviceModal = function (target) {
                var parent = $(target).parents('tr');
                $scope.deviceModal = {
                    deviceId: parent.children('.td-deviceId').text(),
                    deviceType: parent.children('.td-deviceType').text(),
                    deviceName: parent.children('.td-deviceName').text(),
                    deviceGroup: parent.children('.td-deviceGroup').text(),
                    deviceHostIPs: parent.find('.span-deviceHostIps').text(),
                    controllerIPs: parent.find('.span-controllerIps').text(),
                    deviceOps: parent.find('.btn-device-ops').text().trim(),
                    occupierUsername: parent.children('.td-occupierUsername').text(),
                    occupierName: parent.children('.td-occupierName').text(),
                    beginTime: parent.children('.td-beginTime').text(),
                    endTime: parent.children('.td-endTime').text(),
                    note: parent.children('.td-deviceNote').text()
                };

                if ($scope.deviceModal.deviceOps === $scope.deviceOps.APPLY) {
                    $('#modal-device-info .form-time').css('display', 'block');
                    timeSlideBar('.time-slidebar');
                } else {
                    $('#modal-device-info .form-time').css('display', 'none');
                }

            };

            $scope.postConfig = {
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                transformRequest: function (data) {
                    return $.param(data);
                }
            };

            $scope.applyDevice = function (deviceId) {

                var now = moment().format('YYYY-MM-DD ');
                var timeVal = $('.time-slidebar').val();
                var beginTime = moment().format('YYYY-MM-DD HH:mm');
                var endTime = now + padzero(parseInt(timeVal / 60), 2) + ':' + padzero(timeVal % 60, 2);

                var data = {
                    deviceId: deviceId,
                    beginTime: beginTime,
                    endTime: endTime
                };

                var url = '/ibook/device/applyDevice';
                $http.post(url, data, $scope.postConfig).then(
                    function (result) {
                        var data = result.data;
                        if (data.statusCode === 200) {
                            $scope.loadDeviceByPage();
                        } else if (result.statusCode === 401) {
                            window.location.href = '/ibook/views/login.html';
                        } else {
                            toast(result.msg);
                        }
                    }
                );
            };

            $scope.releaseDevice = function (deviceId) {

                var data = {
                    deviceId: deviceId
                };

                var url = '/ibook/device/releaseDevice';
                $http.post(url, data, $scope.postConfig).then(
                    function (result) {
                        var data = result.data;
                        if (data.statusCode === 200) {
                            $scope.loadDeviceByPage();
                        } else if (result.statusCode === 401) {
                            window.location.href = '/ibook/views/login.html';
                        } else {
                            toast(result.msg);
                        }
                    }
                );
            };

            $scope.loadLogOfDevice = function (target) {
                var parent = $(target).parents('tr');
                var deviceId = parent.children('.td-deviceId').text();
                var controllerIps = parent.find('.span-controllerIps').text();
                var data = {
                    deviceId: deviceId,
                    logNum: 15
                };

                $scope.logModal = {
                    controllerIPs: controllerIps
                };

                var url = '/ibook/device/loadLogByDeviceId';
                $http.get(url, {params: data}).then(
                    function (result) {
                        var data = result.data;
                        if (data.statusCode === 200) {
                            $scope.logModal.logList = data.data;
                        } else if (result.statusCode === 401) {
                            window.location.href = '/ibook/views/login.html';
                        } else {
                            toast(result.msg);
                        }
                    }
                );
            };

            $scope.operationDevice = function (target) {
                var deviceOps = $(target).val();
                var deviceId = parseInt($('.modal-device-id').text().trim());
                switch (deviceOps) {
                    case $scope.deviceOps.APPLY:
                        $scope.applyDevice(deviceId);
                        break;
                    case $scope.deviceOps.RELEASE:
                        $scope.releaseDevice(deviceId);
                        break;
                    default:
                        break;
                }
            };
        }
    );

