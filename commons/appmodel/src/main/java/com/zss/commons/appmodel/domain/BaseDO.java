package com.zss.commons.appmodel.domain;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected long id;

    //辅助信息记录
    private long flagBit;
    private JSONObject features = new JSONObject();

    protected LocalDateTime createTime;
    protected LocalDateTime updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFlagBit() {
        return flagBit;
    }

    public void setFlagBit(long flagBit) {
        this.flagBit = flagBit;
    }

    public void addFlagBit(long bit){
        this.flagBit |= bit;
    }
    public void removeFlagBit(long bit){
        this.flagBit &= bit ^ Long.MAX_VALUE;
    }

    public <T> T getFeature(String columnName, Class<T> clazz){
        return features.getObject(columnName, clazz);
    }
    public String getFeature(String columnName){
        return features.getString(columnName);
    }
    public void setupFeature(String columnName,Object object){
        features.put(columnName, object);
    }
    public void setupFeatures(JSONObject features) {
        this.features = features;
    }
    public void removeFeature(String columnName){
        features.remove(columnName);
    }


    //便于写入DB
    public String getFeatures() {
        return JSON.toJSONString(features, SerializerFeature.UseISO8601DateFormat);
    }
    public void setFeatures(String features){
        this.features = JSONObject.parseObject(features, Feature.AllowISO8601DateFormat);
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
