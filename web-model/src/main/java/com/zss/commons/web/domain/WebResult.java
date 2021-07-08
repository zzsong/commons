package com.zss.commons.web.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WebResult<T>  implements Serializable {

    private static final long serialVersionUID = 1L;

    private int status;
    private String message;
    private T model;
    private String timestamp;

    public WebResult(){
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public WebResult(int status, String message){
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public WebResult setResultCode(ResultStatus resultStatus){
        this.status = resultStatus.getStatus();
        return this;
    }

    public WebResult setResultMessage(String message){
        this.message = message;
        return this;
    }

    public WebResult setData(T model){
        this.model = model;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
