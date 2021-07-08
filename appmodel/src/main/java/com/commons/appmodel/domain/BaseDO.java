package com.commons.appmodel.domain;


import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected long id;
    protected LocalDateTime createTime;
    protected LocalDateTime updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
