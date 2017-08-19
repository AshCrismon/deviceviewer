package com.huawei.deviceviewer.controller;

import com.huawei.deviceviewer.entity.Device;
import com.huawei.deviceviewer.entity.Page;
import com.huawei.deviceviewer.entity.User;
import com.huawei.deviceviewer.exception.EntityNotFoundException;
import com.huawei.deviceviewer.exception.InvalidTimeRangeException;
import com.huawei.deviceviewer.service.DeviceService;
import com.huawei.deviceviewer.service.UserService;
import com.huawei.deviceviewer.utils.DateUtils;
import com.huawei.deviceviewer.vo.MessageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    private final String SESSION_ID = "token";

    @Autowired
    private MessageVO message;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public MessageVO loadAllDevices(HttpSession session) {

        String token = (String) session.getAttribute(SESSION_ID);
        List<Device> deviceList = deviceService.loadAllDevices();
        return renderMessage(wrapData(deviceList, token));
    }

    @RequestMapping("/loadPage/{pageNo}")
    public MessageVO loadDevicesByPage(@PathVariable(value = "pageNo") int pageNo, HttpSession session) {

        String token = (String) session.getAttribute(SESSION_ID);
        Page<Device> devicePage = deviceService.loadDevicesByPage(pageNo, 10);
        return renderMessage(wrapDevicePage(devicePage, wrapData(devicePage.getObjList(), token)));
    }

    @RequestMapping("/apply")
    public MessageVO applyDevice(@RequestParam(value = "deviceId") int deviceId,
                                 @RequestParam(value = "beginTime") String beginTime,
                                 @RequestParam(value = "endTime") String endTime,
                                 HttpSession session) throws EntityNotFoundException, InvalidTimeRangeException {

        String token = (String) session.getAttribute(SESSION_ID);
        deviceService.applyDevice(deviceId, token, beginTime, endTime);
        return renderMessage("申请成功！");
    }

    @RequestMapping("/cancel")
    public MessageVO cancelDevice(@RequestParam(value = "deviceId") int deviceId, HttpSession session) {
        String token = (String) session.getAttribute(SESSION_ID);
        deviceService.cancelDevice(deviceId, token);
        return renderMessage("成功解除占用！");
    }

    public List<Map<String, Object>> wrapData(List<Device> deviceList, String token) {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        for (Device device : deviceList) {
            Map<String, Object> deviceMap = new HashMap<>();
            deviceMap.put("deviceId", device.getId());
            deviceMap.put("deviceType", device.getDeviceType());
            deviceMap.put("deviceName", device.getDeviceName());
            deviceMap.put("deviceGroup", device.getDeviceGroup());
            deviceMap.put("deviceHostIPs", device.getDeviceHostIPs());
            deviceMap.put("controllerIPs", device.getControllerIPs());
            deviceMap.put("isOccupied", device.getIsOccupied());
            deviceMap.put("occupierName", "");
            deviceMap.put("occupierUsername", device.getOccupier());
            deviceMap.put("occupierIsMe", 0);
            deviceMap.put("beginTime", device.getBeginTime());
            deviceMap.put("endTime", device.getEndTime());
            deviceMap.put("note", device.getNote());
            deviceMap.put("hostAccount", device.getHostAccount());
            deviceMap.put("controllerAccount", device.getControllerAccount());

            if (applyTimeExpired(device.getBeginTime(), device.getEndTime())) {
                device.setBeginTime("");
                device.setEndTime("");
                device.setOccupier("");
                device.setIsOccupied(0);
                deviceService.updateDevice(device);
            }

            if (1 == device.getIsOccupied()) {
                User user = userService.loadByUsername(device.getOccupier());
                deviceMap.put("occupierName", user.getName());
                deviceMap.put("occupierUsername", user.getUsername());
                deviceMap.put("occupierIsMe", token.equals(device.getOccupier()) ? 1 : 0);
            }

            data.add(deviceMap);
        }
        return data;

    }

    public Map<String, Object> wrapDevicePage(Page<Device> devicePage, List<Map<String, Object>> deviceList) {

        Map<String, Object> wrappedDevicePage = new HashMap<>();
        wrappedDevicePage.put("pageNo", devicePage.getPageNo());
        wrappedDevicePage.put("pageSize", devicePage.getPageSize());
        wrappedDevicePage.put("totalCount", devicePage.getTotalCount());
        wrappedDevicePage.put("totalPages", devicePage.getTotalPages());
        wrappedDevicePage.put("deviceList", deviceList);
        return wrappedDevicePage;
    }

    public boolean applyTimeExpired(String beginTime, String endTime) {
        return !StringUtils.isAnyBlank(beginTime, endTime) && DateUtils.beforeNow(endTime);
    }

    private MessageVO renderMessage(Object data) {
        message.setStatusCode(HttpStatus.OK.value());
        message.setData(data);
        return message;
    }

}
