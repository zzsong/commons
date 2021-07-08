package com.zss.commons.support.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    public static boolean slowEquals(byte[] a, byte[] b)
    {
        int diff = a.length ^ b.length;
        for(int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }


    /**
     * 是否包含非法字符(包括空格)
     *
     * @param str
     * @return
     */
    public static boolean containInvalidChars(String str) {
        String SPECIAL_STR = "#~!@%^&*();'\"?><[]{}\\|,:/=-+—“”‘.`$；，。！@#￥%……&*（）——+？～＠＃￥％……＆×（）——＋｜｛｝？《》：“”、；‘’【】";
        for (int i = 0; i < str.length(); i++) {
            if (SPECIAL_STR.indexOf(str.charAt(i)) != -1||String.valueOf(str.charAt(i)).equals(" ")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是汉字
     * @param words 输入字符串
     * @return
     */
    public static boolean isChinese(String words) {
        int count = 0;
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(words);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count++;
            }
        }
        if (words.length() == count) {
            return true;
        }
        return false;
    }

    /**
     * 验证是否是手机号码
     *  false不是手机号 <br/>
     *
     *	前3位：网络识别号          第4～7位：地区编码              第8～11位：用户号码
     * @param phone
     * @return
     */
    public static boolean isPhoneNum(String phone) {
        if(!StringUtils.isNumeric(phone)){
            return false;
        }
        String regex = "(1[3|4|5|6|7|8]\\d{9})$";// 手机,电话
        return match(regex, phone);
    }



    /**
     * 验证正则表达式是否合法
     *
     * @param regex
     * @param str
     * @return
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isTradeDate(int tradeDate) {
        String regex = "20\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1,2]\\d|3[0,1])";
        return match(regex, String.valueOf(tradeDate));
    }

    public static boolean isEmail(String str) {
        // 判断是否为邮箱地址
        String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return match(regex, str);
    }

    /**
     * 对用户名包含连续5个数字的字符串(长度为len)转换成len-4个字符+4个*进行屏蔽
     * @param account
     * @return
     * @create_time 2011-2-28 上午09:25:10
     */
    public static String shieldAccount(String account) {
        if (StringUtils.isBlank(account)) {
            return account;
        }
        String accountReg = account.replaceAll("([\\d]{5,})", "^$0#");
        int indexBegin = accountReg.indexOf("^");
        int indexEnd = accountReg.indexOf("#");
        StringBuffer sb = new StringBuffer();
        if (indexBegin != -1 && indexEnd != -1) {
            sb.append(accountReg.substring(0, indexBegin));
            sb.append(accountReg.substring(indexBegin + 1, indexEnd- 4));
            sb.append("****");
            sb.append(accountReg.substring(indexEnd+1));
            return sb.toString();
        }
        return account;
    }

    /**
     * 身份证号码进行屏蔽， 前四位＋**** +后四位
     * @param certNo
     * @return
     */
    public static String shieldCertNo(String certNo){
        if(StringUtils.isBlank(certNo)){
            return certNo;
        }
        certNo = StringUtils.substring(certNo, 0, 4) + "****" + StringUtils.substring(certNo, certNo.length()-4, certNo.length());
        return certNo;
    }

    /**
     * 手机号码进行屏蔽， 前三位＋**** +后四位
     * @param phone
     * @return
     */
    public static String shieldPhone(String phone){
        if(StringUtils.isBlank(phone)){
            return phone;
        }
        phone = StringUtils.substring(phone, 0, 3) + "****" + StringUtils.substring(phone, phone.length()-4, phone.length());
        return phone;
    }

    /**
     * 按每字符进行交叉合并
     * @param a
     * @param b
     * @return
     */
    public static String strCrossMerge(String a, String b){
        if (StringUtils.isBlank(a)){
            return b;
        }
        if (StringUtils.isBlank(b)){
            return a;
        }
        if (a.toCharArray().length == b.toCharArray().length){
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < a.toCharArray().length; i++) {
                buffer.append(a.toCharArray()[i]).append(b.toCharArray()[i]);
            }
            return buffer.toString();
        } else {
            char[] max , min ;
            if (a.toCharArray().length>b.toCharArray().length){
                max = a.toCharArray();
                min = b.toCharArray();
            } else {
                max = b.toCharArray();
                min = a.toCharArray();
            }
            char[] str = new char[a.toCharArray().length+b.toCharArray().length];
            for (int i = 0; i < min.length; i++) {
                str[2*i] = min[i];
                str[2*i + 1] = max[i];
            }
            for (int i = min.length*2, j=0; i < str.length; i++,j++) {
                str[i] = max[min.length + j];
            }
            return String.valueOf(str);
        }
    }



}
