package com.huawei.deviceviewer.entity.device;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/19
 * Email: sunyadi@huawei.com
 */
public enum DeviceOps {

    APPLY("APPLY", 1, "申请"),
    RELEASE("RELEASE", 0, "释放"),
    LOG("LOG", 2, "日志");

    private String key;
    private int value;
    private String desc;
    public static Map<String, String> enumMap = new HashMap<String, String>();

    private DeviceOps(String key, int value, String desc){
        this.key = key;
        this.value = value;
        this.desc = desc;
    }

    public String key() { return this.key; }
    public String desc(){
        return this.desc;
    }
    public int value(){
        return this.value;
    }

    static {
        for (DeviceOps deviceOps : EnumSet.allOf(DeviceOps.class)) {
            enumMap.put(deviceOps.key, deviceOps.desc);
        }
    }
}
