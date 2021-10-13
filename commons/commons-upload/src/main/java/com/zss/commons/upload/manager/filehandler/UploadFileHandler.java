package com.zss.commons.upload.manager.filehandler;

import com.zss.commons.appmodel.result.BaseResult;

public interface UploadFileHandler {

    long getFileModuleId();

    BaseResult processFile(long recordId);

}
