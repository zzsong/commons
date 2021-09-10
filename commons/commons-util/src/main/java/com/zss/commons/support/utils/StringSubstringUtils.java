package com.zss.commons.support.utils;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * 按字节进行截取，汉字转utf-8 一个汉字对应3个字节
 *
 * mysql
 *与 CHAR 相比，VARCHAR 值存储为 1 字节或 2 字节长度的前缀加数据。 *
 * 长度前缀表示值中的字节数。 *
 * 如果值需要不超过 255 个字节，则列使用一个长度字节，如果值可能需要超过 255 个字节，则使用两个长度字节。
 *
 * mysql:
 * Value 	    CHAR(4) 	Storage Required 	VARCHAR(4) 	Storage Required
 * '' 	        '    ' 	    4 bytes 	        '' 	        1 byte
 * 'ab' 	    'ab  ' 	    4 bytes 	        'ab' 	    3 bytes
 * 'abcd' 	    'abcd' 	    4 bytes 	        'abcd' 	    5 bytes
 * 'abcdefgh' 	'abcd' 	    4 bytes 	        'abcd' 	    5 bytes
 *
 *
 *
 */
public class StringSubstringUtils {

    public static String substringByByte(String src, int byteSize){
        if (StringUtils.isBlank(src))return "";
        return new String(substring(src.getBytes(StandardCharsets.UTF_8), 0, byteSize));
    }

    public static String substringByByte(String src, int start, int end){
        if (StringUtils.isBlank(src))return "";
        return new String(substring(src.getBytes(StandardCharsets.UTF_8), start, end));
    }

    public static byte[] substring(byte[] src, int start, int end){
        if (src==null){
            return new byte[0];
        }
        if (start >= end){
            return new byte[0];
        }
        int length = Math.min(src.length,end - start);
        byte[] destBytes = new byte[length];
        System.arraycopy(src,0, destBytes,0,length);
        return destBytes;
    }
}
