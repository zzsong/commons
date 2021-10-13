package com.zss.commons.upload.manager.filehandler;

import com.zss.commons.appmodel.result.BaseResult;

public abstract class AbstractUploadFileHandler implements UploadFileHandler{

    protected BaseResult check(long recordId){
        BaseResult baseResult = new BaseResult();


        return baseResult;
    }
}
