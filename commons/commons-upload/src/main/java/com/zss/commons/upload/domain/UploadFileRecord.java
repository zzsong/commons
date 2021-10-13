package com.zss.commons.upload.domain;

import com.zss.commons.appmodel.domain.BaseDO;

import java.time.LocalDateTime;

/**
 * 上传文件纪录
 *
 */
public class UploadFileRecord extends BaseDO {

    private int occurDate;
    //文件模块
    private int fileModuleId;
    //上传时间
    private LocalDateTime uploadTime;
    //源文件名
    private String fileOriginalName;
    //存储文件名
    private String fileDiskName;
    private long fileContentLength;
    //物理路径
    private String fileDiskPath;
    private String sha512Hex;

    private String serviceIp;
    private String operatorAccount;



}
