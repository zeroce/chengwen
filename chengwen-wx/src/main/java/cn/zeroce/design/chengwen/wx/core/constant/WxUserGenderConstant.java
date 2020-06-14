package cn.zeroce.design.chengwen.wx.core.constant;

/**
 * @author: zeroce
 * @date 20.4.16 18:30
 */
public enum  WxUserGenderConstant {
    UNKNOW(0, "未知"),
    MAN(1, "先生"),
    WOMAN(2, "女士"),
    ;
    private Integer value;
    private String name;

    WxUserGenderConstant(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
