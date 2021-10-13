package com.zss.commons.upload.enumeration;

import com.zss.commons.mybatis.enumeration.BaseEnum;

public enum ExecuteStatus implements BaseEnum {

    INITIALIZE(1,"初始化"),
    PROCESS(2,"进行中"),
    ERROR(3,"异常"),
    FINISH(4,"完成"),

    ;

    private int index;
    private String description;

    ExecuteStatus(int index, String description){
        this.index = index;
        this.description = description;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getDescription() {
        return description;
    }

}
