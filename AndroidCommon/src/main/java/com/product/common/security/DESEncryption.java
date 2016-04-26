package com.product.common.security;
import android.util.Base64;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESEncryption {
    
    private static final String FORMAT_UTF8 = "UTF8";
    private static final String FORMAT_ASCII = "ASCII";
    public static final String ALG = "DES";
    public static final String DES_ENCRYPTION_SCHEME = "DES/CBC/PKCS5Padding";
    private KeySpec myKeySpec;
    private SecretKeyFactory mySecretKeyFactory;
    private Cipher cipher;
    byte[] keyAsBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    IvParameterSpec mIv;
    SecretKey mKey;

    public DESEncryption() throws  Exception{
        this(DESEncryption.generateClearTextKey());
    }

    public DESEncryption(String clearKey) throws Exception {
        myEncryptionKey = clearKey;
        myEncryptionScheme = DES_ENCRYPTION_SCHEME;
        keyAsBytes = myEncryptionKey.getBytes(FORMAT_ASCII);
        myKeySpec = new DESKeySpec(keyAsBytes);
        mySecretKeyFactory = SecretKeyFactory.getInstance(ALG);
        cipher = Cipher.getInstance(myEncryptionScheme);
        mKey = mySecretKeyFactory.generateSecret(myKeySpec);
        mIv = new IvParameterSpec(keyAsBytes);
    }

    public String getClearKey() {
        return myEncryptionKey;
    }

    public String encrypt(String clearText) throws Exception{
        cipher.init(Cipher.ENCRYPT_MODE, mKey, mIv);
        byte[] encoded = cipher.doFinal(clearText.getBytes(FORMAT_UTF8));
        return Base64.encodeToString(encoded, Base64.DEFAULT);
    }

    /**
     * Method To Decrypt An Ecrypted String
     */
    public String decrypt(String encryptedString) {
        String decryptedText = null;
        try {
            byte[] encryptedText = Base64.decode(encryptedString,Base64.DEFAULT);
            cipher.init(Cipher.DECRYPT_MODE, mKey,mIv);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText = new String(plainText,FORMAT_UTF8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
    
    
    public static byte[] hex2byte(byte[] b) {
        System.out.println("hex2byte: "+new Date(System.nanoTime()).toLocaleString());
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte)(Integer.parseInt(item, 16));
            
        }
        System.out.println("hex2byte: "+new Date(System.nanoTime()).toLocaleString());
        return b2;
    }

  //For DotNet format.
    public static String byte2hex(byte[] b,boolean isFromDotNet) {
        String hs = "";
        String stmp = "";

        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
            //DotNet format.
            if(isFromDotNet) {
                if(n!=b.length-1) {
                    hs+="-";
                }
            }
        }
        return hs.toUpperCase();
    }
    
    public static String generateClearTextKey() {
        SecureRandom seRandom = new SecureRandom();
        byte[] key = new byte[4];
        seRandom.nextBytes(key);
        return byte2hex(key, false);
    }
}
