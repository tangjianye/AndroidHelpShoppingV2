package com.product.common.utils;

import android.util.Log;

public class LogUtils {
//    public static final boolean DEBUG = true;
//    public static final boolean DEBUG_DRAW = false;
//    public static final boolean DEBUG_DRAG = true;
//    public static final boolean DEBUG_KEY = true;
//    public static final boolean DEBUG_LAYOUT = false;
//    public static final boolean DEBUG_LOADER = true;
//    public static final boolean DEBUG_MOTION = false;
//    public static final boolean DEBUG_PERFORMANCE = true;
//    public static final boolean DEBUG_SURFACEWIDGET = true;
//    public static final boolean DEBUG_UNREAD = false;
//    public static final boolean DEBUG_AUTOTESTCASE = true;

    private static boolean Debug = true;
    private static String MODULE_NAME = "com.product.common";
    private static final LogUtils INSTANCE = new LogUtils();

    /**
     * private constructor here, It is a singleton class.
     */
    private LogUtils() {

    }

    /**
     * The FileManagerLog is a singleton class, this static method can be used
     * to obtain the unique instance of this class.
     *
     * @return The global unique instance of FileManagerLog.
     */
    public static LogUtils getInstance() {
        return INSTANCE;
    }

    /**
     * DEBUG配置
     *
     * @param debug 是否打开debug开关
     * @param TAG   打印TAG
     */
    public static void init(boolean debug, String TAG) {
        Debug = debug;
        MODULE_NAME = TAG;
    }

    /**
     * The method prints the log, level error.
     *
     * @param tag the tag of the class.
     * @param msg the message to print.
     */
    public static void e(String tag, String msg) {
        if (Debug) {
            Log.e(MODULE_NAME, tag + ", " + msg);
        }
    }

    /**
     * The method prints the log, level error.
     *
     * @param tag the tag of the class.
     * @param msg the message to print.
     * @param t   an exception to log.
     */
    public static void e(String tag, String msg, Throwable t) {
        if (Debug) {
            Log.e(MODULE_NAME, tag + ", " + msg, t);
        }
    }

    /**
     * The method prints the log, level warning.
     *
     * @param tag the tag of the class.
     * @param msg the message to print.
     */
    public static void w(String tag, String msg) {
        if (Debug) {
            Log.w(MODULE_NAME, tag + ", " + msg);
        }
    }

    /**
     * The method prints the log, level warning.
     *
     * @param tag the tag of the class.
     * @param msg the message to print.
     * @param t   an exception to log.
     */
    public static void w(String tag, String msg, Throwable t) {
        if (Debug) {
            Log.w(MODULE_NAME, tag + ", " + msg, t);
        }
    }

    /**
     * The method prints the log, level debug.
     *
     * @param tag the tag of the class.
     * @param msg the message to print.
     */
    public static void i(String tag, String msg) {
        if (Debug) {
            Log.i(MODULE_NAME, tag + ", " + msg);
        }
    }

    /**
     * The method prints the log, level debug.
     *
     * @param tag the tag of the class.
     * @param msg the message to print.
     * @param t   an exception to log.
     */
    public static void i(String tag, String msg, Throwable t) {
        if (Debug) {
            Log.i(MODULE_NAME, tag + ", " + msg, t);
        }
    }

    /**
     * The method prints the log, level debug.
     *
     * @param tag the tag of the class.
     * @param msg the message to print.
     */
    public static void d(String tag, String msg) {
        if (Debug) {
            Log.e(MODULE_NAME, tag + ", " + msg);
        }
    }

    /**
     * The method prints the log, level debug.
     *
     * @param tag the tag of the class.
     * @param msg the message to print.
     * @param t   An exception to log.
     */
    public static void d(String tag, String msg, Throwable t) {
        if (Debug) {
            Log.d(MODULE_NAME, tag + ", " + msg, t);
        }
    }

    /**
     * The method prints the log, level debug.
     *
     * @param tag the tag of the class.
     * @param msg the message to print.
     */
    public static void v(String tag, String msg) {
        if (Debug) {
            Log.v(MODULE_NAME, tag + ", " + msg);
        }
    }

    /**
     * The method prints the log, level debug.
     *
     * @param tag the tag of the class.
     * @param msg the message to print.
     * @param t   An exception to log.
     */
    public static void v(String tag, String msg, Throwable t) {
        if (Debug) {
            Log.v(MODULE_NAME, tag + ", " + msg, t);
        }
    }

    public static void printTrace(String tag) {
        if (Debug) {
            Log.v(MODULE_NAME, tag + ", Trace start");
            Thread.dumpStack();
            Log.v(MODULE_NAME, tag + ", Trace end");
        }
    }
}