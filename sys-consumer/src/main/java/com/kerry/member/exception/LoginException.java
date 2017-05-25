package com.kerry.member.exception;

/**
 * Created by wangshen on 2017/5/19.
 */
public class LoginException extends Exception {

    public LoginException(){
        super();
    }

    public LoginException(String message) {
        super(message);
    }
}
