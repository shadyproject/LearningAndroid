package com.nike.listsupportedcrypto;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private static final int LOGCAT_CHARACTER_LIMIT = 4000;
    private static boolean objectReflectionEnabled;
    private static boolean logDateFormattingEnabled;



  /**
     * Super ultra uber verbose (why not?)
     * Top tip: This log level also includes the capability to split a huge message into log-sized chunks so we can read the whole thing!
     */
    public static void suuv(String tag, String message) {
        suuv(tag, message, null);
    }

    /**
     * Super ultra uber verbose (why not?)
     * Top tip: This log level also includes the capability to split a huge message into log-sized chunks so we can read the whole thing!
     */
    public static void suuv(String tag, String message, Throwable throwable) {
        suuv(tag, message, throwable, 0);
    }

    /**
     * Super ultra uber verbose (why not?)
     * Top tip: This log level also includes the capability to split a huge message into log-sized chunks so we can read the whole thing!
     */
    public static void suuv(String tag, String message, Throwable throwable, int splitCount) {
            String newTag = tag;
            String splitLogEntrySeparator = ":\n";  //First character must be a visible character or the logger ignores it!  Hence the colon preceeding the \n
            if (message.length() > LOGCAT_CHARACTER_LIMIT) //This message is longer than the logcat limit!  Split it up so we can see the entire thing!
            {
                //These log entries will all start on a new line for easier visual log scanning, and to keep copy / paste operations simpler
                newTag += " split " + ++splitCount;
                android.util.Log.v(newTag, splitLogEntrySeparator + message.substring(0, LOGCAT_CHARACTER_LIMIT), throwable);
                suuv(tag, message.substring(LOGCAT_CHARACTER_LIMIT), throwable, splitCount);
            } else {
                splitCount += splitCount > 0 ? 1 : 0; //Only increment if this is already part of a split, then this is the end of a split
                newTag += splitCount > 0 ? " split " + splitCount : "";
                message = splitCount > 0 ? splitLogEntrySeparator + message : message;  //add newline only if part of existing split
                android.util.Log.v(newTag, message, throwable);
            }
    }
}