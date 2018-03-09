package com.example.shink.marvelheroesinfo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringJoiner;

import okhttp3.Interceptor;

/**
 * Created by shink on 06.03.2018.
 */

public class Hash {
    public static String hashCreate(final String hash){
        final String MD5 = "MD5";
        try {
            //create method to create hash with md5 crypto
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(hash.getBytes());
            //create byte array form string of key
            byte[] digestM = digest.digest();
            //create hex string of hash
            StringBuffer sb = new StringBuffer();
            for (byte b : digestM) {
                sb.append(String.format("%02x", b & 0xff));
            }
            //return hash
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
