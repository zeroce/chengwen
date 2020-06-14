package cn.zeroce.design.chengwen.dto;

import lombok.Data;

@Data
public class PageQueryDTO<R> {
    private Integer total;
    private R items;
}
