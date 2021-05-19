package com.example.simpleapp;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static String Hashing(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] result = messageDigest.digest();

            StringBuilder pass_hash = new StringBuilder();

            for (byte b : result) {
                pass_hash.append(String.format("%02x", b));
            }
            return pass_hash.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
