package com.huawei.deviceviewer.controller;

import com.huawei.deviceviewer.entity.Device;
import com.huawei.deviceviewer.entity.Page;
import com.huawei.deviceviewer.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private DeviceService deviceService;
    private Map<String, Object> message = new HashMap<>();

    @RequestMapping("/list")
    public Map<String, Object> loadAllDevices(HttpSession session) {
        String username = (String) session.getAttribute("sessionId");
        List<Device> deviceList = deviceService.loadAll();
        List<Map<String, Object>> wrappedDeviceList = wrapDeviceList(deviceList, username);
        return renderMessage("true", wrappedDeviceList);
    }

    @RequestMapping("/loadPage/{pageNo}")
    public Map<String, Object> loadDevicesByPage(@PathVariable(value = "pageNo") int pageNo,
                                                 HttpSession session) {
        String username = (String) session.getAttribute("sessionId");
        Page<Device> devicePage = deviceService.loadByPage(pageNo, 10);
        List<Map<String, Object>> wrappedDeviceList = wrapDeviceList(devicePage.getObjList(), username);
        Map<String, Object> wrappedDevicePage = wrapDevicePage(devicePage, wrappedDeviceList);
        return renderMessage("true", wrappedDevicePage);
    }

    @RequestMapping("/apply")
    public Map<String, Object> applyDevice(@RequestParam(value = "deviceId") int deviceId,
                                           @RequestParam(value = "beginTime") String beginTime,
                                           @RequestParam(value = "endTime") String endTime,
                                           HttpSession session) {
        String username = (String) session.getAttribute("sessionId");
        deviceService.applyDevice(deviceId, username, beginTime, endTime);
        return renderMessage("true", "申请成功！");
    }

    @RequestMapping("/cancel")
    public Map<String, Object> cancelDevice(@RequestParam(value = "deviceId") int deviceId,
                                           HttpSession session) {
        String username = (String) session.getAttribute("sessionId");
        deviceService.cancelDevice(deviceId, username);
        return renderMessage("true", "成功解除占用！");
    }

    public List<Map<String, Object>> wrapDeviceList(List<Device> deviceList, String username) {
        List<Map<String, Object>> wrappedDeviceList = new ArrayList<>();

        for (Device device : deviceList) {
            Map<String, Object> deviceMap = new HashMap<>();
            deviceMap.put("id", device.getId());
            deviceMap.put("type", device.getType());
            deviceMap.put("name", device.getName());
            deviceMap.put("ips", device.getIps());
            deviceMap.put("occupier", device.getOccupier());

            if (!device.getBeginTime().isEmpty() && !device.getEndTime().isEmpty() && device.getBeginTime().compareTo(device.getEndTime()) >= 0) {
                device.setBeginTime("");
                device.setEndTime("");
                device.setOccupier("");
                device.setIsOccupied(0);
                deviceService.updateDevice(device);
            }

            deviceMap.put("beginTime", device.getBeginTime());
            deviceMap.put("endTime", device.getEndTime());
            deviceMap.put("isOccupied", device.getIsOccupied());

            if (device.getOccupier().equals(username)) {
                deviceMap.put("occupierIsMe", 1);
            } else {
                deviceMap.put("occupierIsMe", 0);
            }
            wrappedDeviceList.add(deviceMap);
        }
        return wrappedDeviceList;

    }

    public Map<String, Object> wrapDevicePage(Page<Device> devicePage, List<Map<String, Object>> wrappedDeviceList) {
        
        Map<String, Object> wrappedDevicePage = new HashMap<>();
        wrappedDevicePage.put("pageNo", devicePage.getPageNo());
        wrappedDevicePage.put("pageSize", devicePage.getPageSize());
        wrappedDevicePage.put("totalCount", devicePage.getTotalCount());
        wrappedDevicePage.put("totalPages", devicePage.getTotalPages());
        wrappedDevicePage.put("deviceList", wrappedDeviceList);
        return wrappedDevicePage;
    }


    private Map<String, Object> renderMessage(String status, Object msg) {
        message.put("status", status);
        message.put("msg", msg);
        return message;
    }
}
