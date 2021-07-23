package com.zss.commons.support.ftp;

import com.google.common.base.Splitter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FtpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(FtpClientUtils.class);

    public static FTPClient connectionFTPClient(FtpProperties properties) {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            // 连接FPT服务器,设置IP及端口
            ftpClient.connect(properties.getFtpHost(), properties.getFtpPort());
            // 设置用户名和密码
            ftpClient.login(properties.getFtpUserName(), properties.getFtpPassword());
            // 设置连接超时时间,10000毫秒
            ftpClient.setConnectTimeout(100000);
            // 设置中文编码集，防止中文乱码
            ftpClient.setControlEncoding("UTF-8");
            //Host attempting data connection x.x.x.x is not the same as server y.y.y.y
//            ftpClient.setRemoteVerificationEnabled(false);
            // 设置PassiveMode传输
            ftpClient.enterLocalPassiveMode();
            //设置二进制传输，使用BINARY_FILE_TYPE，ASC容易造成文件损坏
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                logger.info("未连接到FTP，用户名或密码错误");
                ftpClient.disconnect();
            } else {
                logger.info("FTP连接成功");
            }
        }  catch (IOException e) {
            logger.error("FTP的IP地址[{}]可能错误，请正确配置",properties.getFtpHost(),e);
        } catch (Exception e) {
            logger.error("FTP的连接异常",e);
        }
        return ftpClient;
    }

    public boolean closeFTP(FTPClient ftp){
        try {
            ftp.logout();
        } catch (Exception e) {
            logger.error("FTP关闭失败",e);
        }finally{
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    logger.error("FTP关闭失败",ioe);
                }
            }
        }
        return false;
    }

    public boolean uploadFile(FTPClient ftpClient, String remoteFtpPath, String localFile){
        try {
            return uploadFileUnClose(ftpClient, remoteFtpPath, localFile);
        } finally {
            closeFTP(ftpClient);
        }
    }

    /**
     *
     * @param ftpClient
     * @param remoteFtpFile ftp文件全路径
     * @param localFile     上传文件全路径
     * @return
     */
    public boolean uploadFileUnClose(FTPClient ftpClient, String remoteFtpFile, String localFile){
        if (ftpClient == null){
            return false;
        }
        if (!new File(localFile).exists()){
            logger.error("localFile:[{}]文件不存在", localFile);
            return false;
        }

        try(InputStream in = new FileInputStream(localFile)){
            String workDir = FilenameUtils.getFullPath(remoteFtpFile);
            //判断FPT目标文件夹时候存在不存在则创建
            if(!ftpClient.changeWorkingDirectory(workDir)){
                StringBuffer buffer = new StringBuffer();
                Splitter.on("/").omitEmptyStrings().splitToList(workDir).forEach(f->{
                    buffer.append("/").append(f);
                    try {
                        if (!ftpClient.changeWorkingDirectory(buffer.toString())){
                            ftpClient.makeDirectory(buffer.toString());
                        }
                    } catch (IOException e) {
                        logger.error("changeWorkingDirectory.error", e);
                    }
                });
            }
            //跳转目标目录
            ftpClient.changeWorkingDirectory(workDir);

            return ftpClient.storeFile(remoteFtpFile, in);
        } catch (IOException e) {
            logger.error("upload.file.error",e);
            return false;
        }
    }

    /**
     *
     * @param ftpClient
     * @param remoteFile ftp文件全路径
     * @param downPath  下载文件目录
     * @return
     */
    public static boolean downloadFile(FTPClient ftpClient, String remoteFile, String downPath){
        if (ftpClient == null){
            return false;
        }
        String workDir = FilenameUtils.getFullPath(remoteFile);

        String downDir = FilenameUtils.getFullPath(downPath);
        if (!new File(downDir).exists()){
            try {
                FileUtils.forceMkdir(new File(downDir));
            } catch (IOException e) {
                logger.error("download.dir.md.error",e);
            }
        }
        String downloadFile = downPath + File.separator + FilenameUtils.getName(remoteFile);

        try(OutputStream out = new FileOutputStream(downloadFile)){
            ftpClient.changeWorkingDirectory(workDir);
            return ftpClient.retrieveFile(remoteFile, out);
        } catch (IOException e) {
            logger.error("download.file.error",e);
            return false;
        }
    }

}
