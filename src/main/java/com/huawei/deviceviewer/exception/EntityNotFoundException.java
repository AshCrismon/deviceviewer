package com.huawei.deviceviewer.exception;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/5
 * Email: sunyadi@huawei.com
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(){
        super("Entity not found!");
    }

    public EntityNotFoundException(String msg){
        super(msg);
    }
}
