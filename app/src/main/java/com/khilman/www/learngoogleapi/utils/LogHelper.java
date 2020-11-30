package com.khilman.www.learngoogleapi.utils;

import android.util.Log;

import com.khilman.www.learngoogleapi.BuildConfig;

import java.util.Arrays;

/**
 * Created by Septian Adi Wijaya on 27/11/2020.
 * please be sure to add credential if you use people's code
 */
public class LogHelper {
    private static boolean LOGGING_ENABLED = BuildConfig.DEBUG;
    private static final int STACK_TRACE_LEVELS_UP = 5;

    public static void e(String tag) {
        if (LOGGING_ENABLED) {
            Log.e(tag, getClassNameMethodNameAndLineNumber());
        }
    }

    public static void e(String tag, String message) {
        if (LOGGING_ENABLED) {
            Log.e(tag, getClassNameMethodNameAndLineNumber() + ", " + message);
        }
    }

    public static void e(String tag, Object... messages) {
        if (LOGGING_ENABLED)
            Log.e(tag, getClassNameMethodNameAndLineNumber() + ", " + Arrays.deepToString(messages));
    }

    /**
     * Get the current line number. Note, this will only work as called from
     * this class as it has to go a predetermined number of steps up the stack
     * trace. In this case 5.
     *
     * @return int - Current line number.
     */
    private static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getLineNumber();
    }

    /**
     * Get the current method name. Note, this will only work as called from
     * this class as it has to go a predetermined number of steps up the stack
     * trace. In this case 5.
     *
     * @return String - Current line number.
     */
    private static String getMethodName() {
        return Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getMethodName();
    }

    /**
     * Returns the class name, method name, and line number from the currently
     * executing log call in the form <class_name>.<method_name>()-<line_number>
     *
     * @return String - String representing class name, method name, and line
     * number.
     */
    private static String getClassNameMethodNameAndLineNumber() {
        return "Line " + getLineNumber() + ", " + getMethodName() + "()";
    }
}
