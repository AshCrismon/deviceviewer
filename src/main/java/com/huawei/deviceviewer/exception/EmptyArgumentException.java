package com.huawei.deviceviewer.exception;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/13
 * Email: sunyadi@huawei.com
 */
public class EmptyArgumentException extends RuntimeException {
    public EmptyArgumentException(){
        super("存在为空的参数！");
    }

    public EmptyArgumentException(String msg){
        super(msg);
    }
}
