package com.zss.commons.support.codec;

import com.google.common.base.Splitter;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class DigestHexUtils {

    /**
     * 文件摘要
     * @param file
     * @return
     * @throws IOException
     */
    public final static String genCheckSum(File file) throws IOException {
        return DigestUtils.sha512Hex(new FileInputStream(file));
    }

    public final static String genCheckSum(File file, DigestAlgoType algoType) throws IOException {
        switch (algoType){
            case MD5:return DigestUtils.md5Hex(new FileInputStream(file));
            case SHA_256:return DigestUtils.sha256Hex(new FileInputStream(file));
            case SHA_512:return DigestUtils.sha512Hex(new FileInputStream(file));
            case SHA3_256:return DigestUtils.sha3_256Hex(new FileInputStream(file));
            case SHA3_512:return DigestUtils.sha3_512Hex(new FileInputStream(file));
        }
        return DigestUtils.sha512Hex(new FileInputStream(file));
    }

    /**
     * 字符串摘要
     * @param content
     * @return
     */
    public final static String encodeHex(String content){
        return DigestUtils.sha512Hex(content.getBytes(StandardCharsets.UTF_8));
    }
    public final static String encodeHex(String content, DigestAlgoType algoType){
        switch (algoType){
            case MD5:return DigestUtils.md5Hex(content.getBytes(StandardCharsets.UTF_8));
            case SHA_256:return DigestUtils.sha256Hex(content.getBytes(StandardCharsets.UTF_8));
            case SHA_512:return DigestUtils.sha512Hex(content.getBytes(StandardCharsets.UTF_8));
            case SHA3_256:return DigestUtils.sha3_256Hex(content.getBytes(StandardCharsets.UTF_8));
            case SHA3_512:return DigestUtils.sha3_512Hex(content.getBytes(StandardCharsets.UTF_8));
        }
        return encodeHex(content);
    }


    /**
     * 参数摘要
     * 参数进行排序
     * @param paramMap
     * @return
     */
    public static String paramHex(Map<String, String> paramMap){
        return DigestUtils.sha512Hex(compareJoining(paramMap));
    }
    public static String paramHex(Map<String, String> paramMap, DigestAlgoType algoType){
        switch (algoType){
            case MD5:return DigestUtils.md5Hex(compareJoining(paramMap).getBytes(StandardCharsets.UTF_8));
            case SHA_256:return DigestUtils.sha256Hex(compareJoining(paramMap).getBytes(StandardCharsets.UTF_8));
            case SHA_512:return DigestUtils.sha512Hex(compareJoining(paramMap).getBytes(StandardCharsets.UTF_8));
            case SHA3_256:return DigestUtils.sha3_256Hex(compareJoining(paramMap).getBytes(StandardCharsets.UTF_8));
            case SHA3_512:return DigestUtils.sha3_512Hex(compareJoining(paramMap).getBytes(StandardCharsets.UTF_8));
        }
        return DigestUtils.sha512Hex(compareJoining(paramMap));
    }
    /**
     * 请求url参数字符串: a=1&b=2&c=3
     * 按参数key进行排序摘要
     * @param requestParam
     * @return
     */
    public static String sha512RequestParam(String requestParam){
        Splitter.MapSplitter mapSplitter = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on("="));
        Map<String, String> result = mapSplitter.split(requestParam);
        return paramHex(result);
    }

    private static String compareJoining(Map<String,String> map){
        return map.keySet().stream().sorted().map(k->String.format("%s=%s",k,map.get(k))).collect(Collectors.joining("&"));
    }
}
