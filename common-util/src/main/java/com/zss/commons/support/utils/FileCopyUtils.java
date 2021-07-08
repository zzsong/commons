package com.zss.commons.support.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileCopyUtils {

    public static void transferTo(String fromFile, String toFile) throws IOException
    {
        File fileToCopy = new File(fromFile);
        FileInputStream inputStream = new FileInputStream(fileToCopy);
        FileChannel inChannel = inputStream.getChannel();

        File newFile = new File(toFile);
        FileOutputStream outputStream = new FileOutputStream(newFile);
        FileChannel outChannel = outputStream.getChannel();

        inChannel.transferTo(0, fileToCopy.length(), outChannel);

        inputStream.close();
        outputStream.close();
    }
}
