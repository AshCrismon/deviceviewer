package com.huawei.deviceviewer.controller;

import com.huawei.deviceviewer.Application;
import com.huawei.deviceviewer.config.AbstractTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class DeviceControllerTest extends AbstractTestConfig {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testLoadDevices() throws Exception {
        mockMvc.perform(get("/device/loadDevices")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testLoadDevicesByPage() throws Exception {
        mockMvc.perform(get("/device/loadDevices/1")
                .param("pageSize", "20")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testApplyDevice() throws Exception {
        mockMvc.perform(post("/device/applyDevice")
                .param("deviceId", "1")
                .param("username", "s00423985")
                .param("beginTime", "2017-09-01 13:00")
                .param("endTime", "2017-09-02 14:00")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testReleaseDevice() throws Exception {
        mockMvc.perform(post("/device/releaseDevice")
                .param("deviceId", "1")
                .param("username", "s00423985")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testLoadLogByDeviceId() throws Exception {
        mockMvc.perform(get("/device/loadLogByDeviceId")
                .param("deviceId", "1")
                .param("logNum", "10")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }
}
