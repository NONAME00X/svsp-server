package edu.cxy.svspcxy.util;

import lombok.Data;

import java.util.HashSet;
import java.util.UUID;

/**
 * @author: Mr·Xiang
 * @create 2024-06-27 14:12
 */
@Data
public class KeyUtil {
    // 存放生成的key
    private static final HashSet<String> KEYS_SET = new HashSet<>();

    // 获取key
    public static String getKey(){
        String key = UUID.randomUUID().toString();
        KEYS_SET.add(key);
        return key;
    }

    // 删除key，删除成功返回true，失败返回false
    public static boolean removeKey(String key){
        return KEYS_SET.remove(key);
    }
}
