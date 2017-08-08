package com.huawei.deviceviewer.exception;

/**
 * Created by Intellij IDEA.
 * Author: gyyjq
 * Date: 2017/8/8
 * Email: yadysun@gmail.com
 */
public class DuplicateAccountException extends RuntimeException {
    public DuplicateAccountException(){
        super("duplicate account found!");
    }

    public DuplicateAccountException(String msg){
        super(msg);
    }
}
