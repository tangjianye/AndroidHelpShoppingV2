package com.product.common.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Util {
	  
    /*** 
     * MD5加码 生成32位md5码 
     */  
    public static String string2MD5(String inStr){  
//        MessageDigest md5;
//        try{
//            md5 = MessageDigest.getInstance("MD5");
//        }catch (Exception e){
//            System.out.println(e.toString());
//            e.printStackTrace();
//            return "";
//        }
//        char[] charArray = inStr.toCharArray();
//        byte[] byteArray = new byte[charArray.length];
//
//        for (int i = 0; i < charArray.length; i++)
//            byteArray[i] = (byte) charArray[i];
//        byte[] md5Bytes = md5.digest(byteArray);
//        StringBuffer hexValue = new StringBuffer();
//        for (int i = 0; i < md5Bytes.length; i++){
//            int val = ((int) md5Bytes[i]) & 0xff;
//            if (val < 16)
//                hexValue.append("0");
//            hexValue.append(Integer.toHexString(val));
//        }
//        return hexValue.toString();
        MessageDigest messageDigest = null;
        try
        {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(inStr.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e)
        {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++)
        {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();

    }  
  
    /** 
     * 加密解密算法 执行一次加密，两次解密 
     */   
    public static String convertMD5(String inStr){  
  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    } 
    
    // 测试主函数  
    public static void main(String args[]) {
//        String s = new String("tangfuqiang");
//        System.out.println("原始：" + s);
//        System.out.println("MD5后：" + string2MD5(s));
//        System.out.println("加密的：" + convertMD5(s));
//        System.out.println("解密的：" + convertMD5(convertMD5(s)));
        String conent = "{\"Request\":{\"Header\":{\"TerminalNo\":\"Lenovo S856\",\"AppTypeCode\":\"9\",\"FunCode\":5114,\"ReqTime\":\"2015-06-08\",\"AppCode\":\"444907439\"},\"Body\":{\"AccountPassword\":\"e10adc3949ba59abbe56e057f20f883e\",\"Account\":\"18674843603\",\"Phr_BaseInfo\":{\"Name\":\"甘旺3\","/* "IDCard":"52032819730924772X","SelfPhone":"18674843603","NowAddress":"123","BirthDay":"\/Date(117648000000+0800)\/","Sex":2},"UserInfo":{"MobilePhone":"18674843603","UserName":"甘旺3","NickName":"测试人员2"}}}} */
//        String conent = "{\"Request\":{\"Header\":{\"TerminalNo\":\"Lenovo S856\",\"AppTypeCode\":\"9\",\"FunCode\":5113,\"ReqTime\":\"2015-06-08\",\"AppCode\":\"444907439\"},\"Body\":{\"AccountPassword\":\"e10adc3949ba59abbe56e057f20f883e\",\"Account\":\"18674843603\",\"UserInfo\":{\"UserId\":50699}}}}"
//        String conent = "request"
                +"x4/uoT6cTUCwUdx8+kw3pHjAJBekdtiA"
                +"p6c7s4nanolegy5vczj94vdr9rros77t";
        System.out.println(MD5Util.string2MD5(conent));
    }
}
