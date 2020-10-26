package com.mz.common.utils;

import com.google.common.base.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 脱敏处理工具类
 */
public class DesensitizedUtil {


    /**
     * 对身份证进行脱敏处理
     * @param idNumber
     * @return
     */
    public static String desensitizedIdNumber(String idNumber){
        if (!Strings.isNullOrEmpty(idNumber)) {
            return StringUtils.left(idNumber, 4).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(idNumber, 3), StringUtils.length(idNumber), "*"), "****"));
        }
        return idNumber;
    }

    /**
     * 对手机号进行脱敏处理
     * @param phoneNumber
     * @return
     */
    public static String desensitizedPhoneNumber(String phoneNumber){
        if(StringUtils.isNotEmpty(phoneNumber)){
            phoneNumber = phoneNumber.replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2");
        }
        return phoneNumber;
    }

    //验证手机号码
    public static boolean phoneNumber(String number)
    {
        String rgx = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        return isCorrect(rgx, number);
    }

    //验证身份证号码
    public static boolean idCardNumber(String number)
    {
        //身份证
        String idCard = "^\\d{6}(19|20)*[0-99]{2}(0[1-9]{1}|10|11|12)(0[1-9]{1}|1[0-9]|2[0-9]|30|31)(\\w*)$";
        //护照
        String passport = "^[a-zA-Z0-9]{5,17}$";
        //港澳通行证验证
        String hkmakao = "^[HMhm]{1}([0-9]{10}|[0-9]{8})$";
        return isCorrect(idCard, number) || isCorrect(passport, number) || isCorrect(hkmakao, number);
    }

    //正则验证
    private static boolean isCorrect(String rgx, String res)
    {
        if(StringUtils.isBlank(res)){
            return false;
        }
        Pattern p = Pattern.compile(rgx);
        Matcher m = p.matcher(res);
        return m.matches();
    }

}
