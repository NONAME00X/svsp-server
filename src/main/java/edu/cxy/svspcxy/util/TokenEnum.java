package edu.cxy.svspcxy.util;

/**
 * 罗列三个状态
 *  TOKEN_EXPIRE  token过期
 *  TOKEN_BAD     token不合法，肯定是伪造的
 *  TOKEN_SUCCESS token没问题
 */
public enum TokenEnum {
    TOKEN_EXPIRE,TOKEN_BAD,TOKEN_SUCCESS
}