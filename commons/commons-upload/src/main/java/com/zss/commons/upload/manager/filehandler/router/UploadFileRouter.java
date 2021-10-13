package com.zss.commons.upload.manager.filehandler.router;

import com.zss.commons.upload.manager.filehandler.UploadFileHandler;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Named
public class UploadFileRouter {

    private static Map<Long, UploadFileHandler> handlerMap = new ConcurrentHashMap<>();

    @Inject
    private List<UploadFileHandler> handlerList ;

    @PostConstruct
    private void init(){
        if (handlerList!= null){
            handlerList.forEach(h->handlerMap.put(h.getFileModuleId(), h));
        }
    }
}
