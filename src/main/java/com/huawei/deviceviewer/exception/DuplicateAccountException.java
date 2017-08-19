package com.huawei.deviceviewer.exception;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/8
 * Email: sunyadi@huawei.com
 */
public class DuplicateAccountException extends RuntimeException {
    public DuplicateAccountException(){
        super("用户名已经存在！");
    }

    public DuplicateAccountException(String msg){
        super(msg);
    }
}
