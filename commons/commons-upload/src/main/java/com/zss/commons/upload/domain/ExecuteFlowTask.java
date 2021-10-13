package com.zss.commons.upload.domain;

import com.zss.commons.appmodel.domain.BaseDO;
import com.zss.commons.upload.enumeration.ExecuteStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * occurDate + taskTypeId + batch
 */
public class ExecuteFlowTask extends BaseDO {

    private int occurDate;
    private long taskTypeId;
    private int batch;

    private LocalDateTime beginTime;
    private LocalDateTime finishTime;
    private ExecuteStatus executeStatus;

    private String serverIp;
    private String userIp;
    private String userAccount;

    private String errorCode;
    private String errorMsg;
    private String detailStack;

    private long finishCount;
    private long totalCount;
    private BigDecimal progressBar = BigDecimal.ZERO ;


}
