package com.xy.wms.enums;

/**
 * 分配状态枚举类
 */
public enum  StateStatus {
    // 未到库
    UNSTATE(0),
    // 已到库
    STATED(1);

    private Integer type;

    StateStatus(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
