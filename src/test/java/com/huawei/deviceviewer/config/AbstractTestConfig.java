package com.huawei.deviceviewer.config;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
public class AbstractTestConfig {

    public void print(String remark) {
        System.out.println("++++++++++++++++++++++++++++" + remark + "++++++++++++++++++++++++++++");
    }

    public void print(Object result) {
        System.out.println(result);
    }

    public void print(Iterable<?> itr) {
        print("begin");
        Iterator<?> it = itr.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
        print("end");
    }
}
