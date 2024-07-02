package edu.cxy.svspcxy.exception;

/**
 * 账号或者密码错误的异常
 * @author: Mr·Xiang
 * @create 2024-07-02 14:42
 */
public class AccountOrPasswordErrorException extends RuntimeException{

    public AccountOrPasswordErrorException(String message){
        super(message);
    }
}
