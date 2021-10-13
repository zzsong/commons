package com.zss.commons.upload.domain;

import com.zss.commons.appmodel.domain.BaseDO;

/**
 * moduleCode + taskTypeId
 */
public class FileModule extends BaseDO {

    //用于业务类型区分文件，唯一键，只能是字母与数字
    private String moduleCode;

    private long taskTypeId;

    private String description;


}
