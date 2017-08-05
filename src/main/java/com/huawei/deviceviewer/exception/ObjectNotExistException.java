package com.huawei.deviceviewer.exception;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/5
 * Email: sunyadi@huawei.com
 */
public class ObjectNotExistException extends RuntimeException {

    public ObjectNotExistException(){
        super("object not exist!");
    }

    public ObjectNotExistException(String msg){
        super(msg);
    }
}
