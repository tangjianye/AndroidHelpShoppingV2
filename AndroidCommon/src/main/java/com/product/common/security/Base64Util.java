package com.product.common.security;

import android.util.Base64;

public class Base64Util {
    public static String encode(String toEncodeContent) {
        if(toEncodeContent==null){
            return null;
        }
        return Base64.encodeToString(toEncodeContent.getBytes(), Base64.DEFAULT);
    }
    
    public static String decode(String toDecodeContent) {
        if(toDecodeContent==null){
            return null;
        }
        return new String(Base64.decode(toDecodeContent,Base64.DEFAULT));
    }
}
