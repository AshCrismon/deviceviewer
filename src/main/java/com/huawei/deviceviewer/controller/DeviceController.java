package com.huawei.deviceviewer.controller;

import com.huawei.deviceviewer.entity.log.Log;
import com.huawei.deviceviewer.entity.common.MessageTip;
import com.huawei.deviceviewer.entity.common.Page;
import com.huawei.deviceviewer.entity.device.Device;
import com.huawei.deviceviewer.entity.device.DeviceOps;
import com.huawei.deviceviewer.entity.device.DeviceState;
import com.huawei.deviceviewer.entity.user.User;
import com.huawei.deviceviewer.service.DeviceService;
import com.huawei.deviceviewer.service.LogService;
import com.huawei.deviceviewer.service.UserService;
import com.huawei.deviceviewer.utils.DateUtils;
import com.huawei.deviceviewer.vo.MessageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/5
 * Email: sunyadi@huawei.com
 */
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private MessageVO message;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    private String currentToken;

    @RequestMapping(value = "/loadDevices", method = RequestMethod.GET)
    public MessageVO loadAllDevices() {
        List<Device> deviceList = deviceService.loadAllDevices();
        return renderMessage(deviceList);
    }

    @RequestMapping(value = "/loadDevices/{pageNo}", method = RequestMethod.GET)
    public MessageVO loadDevicesByPage(@PathVariable(value = "pageNo") int pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                       HttpSession session) {
        currentToken = (String)session.getAttribute(UserController.SESSION_ID);
        Page<Device> devicePage = deviceService.loadDevicesByPage(pageNo, pageSize);
        return renderMessage(wrapData(devicePage));
    }

    @RequestMapping(value = "/applyDevice", method = RequestMethod.POST)
    public MessageVO applyDevice(@RequestParam(value = "deviceId") int deviceId,
                                 @RequestParam(value = "beginTime") String beginTime,
                                 @RequestParam(value = "endTime") String endTime) {

        deviceService.applyDevice(deviceId, currentToken, beginTime, endTime);
        log(deviceId, currentToken, DeviceOps.APPLY, beginTime, endTime);
        return renderMessage(MessageTip.DEVICE_APPLY_SUCCESS);
    }

    @RequestMapping(value = "/releaseDevice", method = RequestMethod.POST)
    public MessageVO releaseDevice(@RequestParam(value = "deviceId") int deviceId) {
        deviceService.releaseDevice(deviceId, currentToken);
        log(deviceId, currentToken, DeviceOps.RELEASE, "", "");
        return renderMessage(MessageTip.DEVICE_RELEASE_SUCCESS);
    }

    @RequestMapping(value = "/loadLogByDeviceId", method = RequestMethod.GET)
    public MessageVO loadLogByDeviceId(@RequestParam(value = "deviceId") int deviceId,
                                       @RequestParam(value = "logNum") int limit) {
        List<Log> logList = logService.loadByDeviceId(deviceId, limit);
        return renderMessage(logList);
    }

    private Map<String, Object> wrapData(Page<Device> devicePage) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> deviceList = new ArrayList<Map<String, Object>>();

        for (Device device : devicePage.getObjList()) {
            Map<String, Object> deviceMap = new HashMap<>();
            deviceMap.put("deviceId", device.getId());
            deviceMap.put("deviceType", device.getDeviceType());
            deviceMap.put("deviceName", device.getDeviceName());
            deviceMap.put("deviceGroup", device.getDeviceGroup());
            deviceMap.put("deviceHostIPs", device.getDeviceHostIPs());
            deviceMap.put("controllerIPs", device.getControllerIPs());
            deviceMap.put("occupancyState", DeviceState.OCCUPYING.value() == device.getOccupancyState());
            deviceMap.put("occupierName", "");
            deviceMap.put("occupierUsername", device.getOccupier());
            deviceMap.put("currentOccupier", false);
            deviceMap.put("deviceOps", DeviceOps.APPLY.desc());
            deviceMap.put("highlight", false);
            deviceMap.put("readOnly", false);
            deviceMap.put("beginTime", device.getBeginTime());
            deviceMap.put("endTime", device.getEndTime());
            deviceMap.put("note", device.getNote());
            deviceMap.put("hostAccount", device.getHostAccount());
            deviceMap.put("controllerAccount", device.getControllerAccount());

            if (applyTimeExpired(device.getBeginTime(), device.getEndTime())) {
                device.setBeginTime("");
                device.setEndTime("");
                device.setOccupier("");
                device.setOccupancyState(DeviceState.FREE.value());
                deviceService.updateDevice(device);
            }

            if (DeviceState.OCCUPYING.value() == device.getOccupancyState()) {
                User user = userService.loadByUsername(device.getOccupier());
                deviceMap.put("occupierName", user.getName());
                deviceMap.put("occupierUsername", user.getUsername());

                if(currentToken.equals(user.getUsername())){
                    deviceMap.put("currentOccupier", true);
                    deviceMap.put("deviceOps", DeviceOps.RELEASE.desc());
                }else{
                    deviceMap.put("readOnly", true);
                }
            }

            if ("CI".equals(device.getNote())){
                deviceMap.put("highlight", true);
                deviceMap.put("readOnly", true);
            }
            deviceList.add(deviceMap);
        }
        result.put("pageNo", devicePage.getPageNo());
        result.put("pageSize", devicePage.getPageSize());
        result.put("totalCount", devicePage.getTotalCount());
        result.put("totalPages", devicePage.getTotalPages());
        result.put("deviceList", deviceList);
        result.put("deviceOps", DeviceOps.enumMap);
        return result;
    }

    private boolean applyTimeExpired(String beginTime, String endTime) {
        return !StringUtils.isAnyBlank(beginTime, endTime) && DateUtils.beforeNow(endTime);
    }

    private void log(int deviceId, String username, DeviceOps ops, String beginTime, String endTime) {
        logService.insertLog(new Log(deviceId, username, ops.value(), beginTime, endTime));
    }

    private MessageVO renderMessage(Object data) {
        message.setStatusCode(HttpStatus.OK.value());
        message.setData(data);
        message.setMsg(MessageTip.SUCCESS.value());
        return message;
    }

    private MessageVO renderMessage(MessageTip messageTip) {
        message.setStatusCode(HttpStatus.OK.value());
        message.setMsg(messageTip.value());
        return message;
    }

}
