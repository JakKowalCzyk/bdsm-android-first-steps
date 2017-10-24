package com.kowalczyk.bdsm;

/**
 * Created by JKowalczyk on 2017-10-24.
 */
public class MessageStore {

    public static String message;
    public static String hashedPassword;

    public static String getMessage() {
        return message;
    }

    public static String getHashedPassword() {
        return hashedPassword;
    }
}
