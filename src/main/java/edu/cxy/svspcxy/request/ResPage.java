package edu.cxy.svspcxy.request;

import lombok.Data;

/**
 * 分页类：封装分页信息
 * @author: Mr·Xiang
 * @create 2024-07-03 11:17
 */
@Data
public class ResPage<T> {
    private Integer page;   // 当前页
    private Integer size;   // 当前页大小
    private Integer totalPage; // 总页数
    private Long total;     // 总条数
    private T data;         // 当前页的数据
}
