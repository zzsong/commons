package com.zss.commons.support.codec;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DigestFileSha512 {

    public static String sha512(File file) throws IOException {
        return DigestUtils.sha512Hex(new FileInputStream(file));
    }

}
