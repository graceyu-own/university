package org.techas.falleaves.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DesUtils {

    public static String toMD5(String plainText) {
        byte[] secretBytes;

        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Except: Unknown Algorithm.");
        }
        StringBuilder md5code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code.insert(0, "0");
        }
        return md5code.toString();
    }

    public static String encodeBase64(String plainText) {
        if("".equals(plainText)) return null;
        return Base64.getUrlEncoder().encodeToString(plainText.getBytes(StandardCharsets.UTF_8));
    }

    public static String decodeBase64(String text) {
        if("".equals(text)) return null;
        return new String(Base64.getUrlDecoder().decode(text), StandardCharsets.UTF_8);
    }
}
