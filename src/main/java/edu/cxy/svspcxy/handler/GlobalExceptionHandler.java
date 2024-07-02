package edu.cxy.svspcxy.handler;

import edu.cxy.svspcxy.exception.AccountOrPasswordErrorException;
import edu.cxy.svspcxy.request.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器：处理在service中抛出的异常
 * @author: Mr·Xiang
 * @create 2024-07-02 14:48
 *
 * advice：通知    AOP：面向切面编程
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 指定捕获异常，调用下面的方法处理
    @ExceptionHandler(AccountOrPasswordErrorException.class)
    public ResponseResult<String> handler(AccountOrPasswordErrorException e){
        System.out.println("捕获异常了");
        // 直接将结果返回给前端
        return new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), null);
    }
}
