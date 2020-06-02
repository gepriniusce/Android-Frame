package pr.tongson.library.utils;

import android.text.format.DateFormat;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author tongson
 */
public class LogUtils {

    public static final String TAG = "TongsonDebug";
    private static String sTagPre = null;

    public static boolean Debug = true;
    private static boolean enableWrite = false;

    public static final int VERBOSE = Log.VERBOSE;
    public static final int DEBUG = Log.DEBUG;
    public static final int INFO = Log.INFO;
    public static final int WARN = Log.WARN;
    public static final int ERROR = Log.ERROR;

    public static final String LOG_SUFFIX = "txt";
    /**
     * 日志存放目录名
     */
    public static final String WORK_LOG_DIR_NAME = ".log";
    /**
     * 基本目录名
     */
    public static final String WORK_BASE_DIR_NAME = "Immortal";
    /**
     * 默认在SD卡的存放目录
     */
    public static final String DEFAULT_SDCARD_WORK_DIR = android.os.Environment.
            getExternalStorageDirectory().
            getAbsolutePath() + File.separator + WORK_BASE_DIR_NAME;
    /**
     * 默认的日志存放目录
     */
    public static final String DEFAULT_SDCARD_LOG_DIR = DEFAULT_SDCARD_WORK_DIR + File.separator + WORK_LOG_DIR_NAME;


    private static final String DEFAULT_LOG_DIR = DEFAULT_SDCARD_LOG_DIR + File.separator + "common";

    private static String mLogDir = DEFAULT_LOG_DIR;

    private LogUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 是否设置调试模式
     */
    public static void setDebug(boolean isdebug) {
        Debug = isdebug;
    }

    /**
     * 是否允许写入SD的日志
     */
    public static void setEnableWriteLog(boolean enable) {
        enableWrite = enable;
    }

    /**
     * 设置日志的保存目录，默认为/sdcard/Immortal/.log/common
     *
     * @param dirPath
     */
    public static void setupLogDir(String dirPath) {
        mLogDir = dirPath;
    }

    /**
     * 设置前缀TAG. Logcat的TAG标签将会以一下方式展示</br>
     * <pre>
     * preTag|class|method|line
     * </pre>
     * 使用preTag可以方便的与其他程序产生的日志进行分离
     *
     * @param preTag
     */
    public static void setPreTag(String preTag) {
        sTagPre = preTag;
    }

    /**
     * 获取日志保存目录
     */
    public static String getLogDir() {
        return mLogDir;
    }


    // ==================================INFO==================================

    public static void i(String msg, Throwable tr) {
        if (!Debug) {
            return;
        }
        i(getLogTag(), msg, tr);
    }

    public static void i(String msg) {
        if (!Debug) {
            return;
        }
        i(getLogTag(), msg, (Throwable) null);
    }

    public static void i(Object tag, String msg) {
        i(tag, msg, (Throwable) null);
    }

    public static void i(Object tag, String msg, Throwable tr) {
        log(INFO, tag, msg, tr);
    }

    public static void i(Object tag, String format, Object... objects) {
        String msg;
        try {
            msg = String.format(format, objects);
        } catch (Exception e) {
            msg = String.valueOf(objects);
        }
        log(INFO, tag, msg, null);
    }

    // ==================================INFO==================================


    // ==================================ERROR==================================

    public static void e(String str) {
        if (!Debug) {
            return;
        }
        e(getLogTag(), str, (Throwable) null);
    }

    public static void e(String msg, Throwable tr) {
        if (!Debug) {
            return;
        }
        e(getLogTag(), msg, tr);
    }

    public static void e(Object tag, String msg) {
        if (!Debug) {
            return;
        }
        e(tag, msg, (Throwable) null);
    }

    public static void e(Object tag, String msg, Throwable tr) {
        log(ERROR, tag, msg, tr);
    }

    public static void e(Object tag, String format, Object... objects) {
        String msg;
        try {
            msg = String.format(format, objects);
        } catch (Exception e) {
            msg = String.valueOf(objects);
        }
        log(ERROR, tag, msg, null);
    }
    // ==================================ERROR==================================


    // ==================================VERBOSE==================================

    public static void v(String msg, Throwable tr) {
        if (!Debug) {
            return;
        }
        v(getLogTag(), msg, tr);
    }

    public static void v(String msg) {
        if (!Debug) {
            return;
        }
        v(getLogTag(), msg, (Throwable) null);
    }

    public static void v(Object tag, String str) {
        v(tag, str, (Throwable) null);
    }

    public static void v(Object tag, String msg, Throwable tr) {
        log(VERBOSE, tag, msg, tr);
    }

    public static void v(Object tag, String format, Object... objects) {
        String msg;
        try {
            msg = String.format(format, objects);
        } catch (Exception e) {
            msg = String.valueOf(objects);
        }
        log(VERBOSE, tag, msg, null);
    }

    // ==================================VERBOSE==================================


    // ==================================DEBUG==================================

    public static void d(String msg, Throwable tr) {
        if (!Debug) {
            return;
        }
        d(getLogTag(), msg, tr);
    }

    public static void d(String msg) {
        if (!Debug) {
            return;
        }
        d(getLogTag(), msg, (Throwable) null);
    }

    public static void d(Object tag, String msg) {
        d(tag, msg, (Throwable) null);
    }

    public static void d(Object tag, String msg, Throwable tr) {
        log(DEBUG, tag, msg, tr);
    }

    public static void d(Object tag, String format, Object... objects) {
        String msg;
        try {
            msg = String.format(format, objects);
        } catch (Exception e) {
            msg = String.valueOf(objects);
        }
        log(DEBUG, tag, msg, null);
    }
    // ==================================DEBUG==================================


    // ==================================WARN==================================

    public static void w(String msg) {
        if (!Debug) {
            return;
        }
        w(getLogTag(), msg, (Throwable) null);
    }

    public static void w(String msg, Throwable tr) {
        if (!Debug) {
            return;
        }
        w(getLogTag(), msg, tr);
    }

    public static void w(Object tag, String msg) {
        w(tag, msg, (Throwable) null);
    }

    public static void w(Object tag, String msg, Throwable tr) {
        log(WARN, tag, msg, tr);
    }

    public static void w(Object tag, String format, Object... objects) {
        String msg;
        try {
            msg = String.format(format, objects);
        } catch (Exception e) {
            msg = String.valueOf(objects);
        }
        log(WARN, tag, msg, null);
    }
    // ==================================WARN==================================


    // ==================================printStackTrace==================================

    public static void printStackTrace(Throwable t) {
        if (!Debug) {
            return;
        }
        log(ERROR, "", "", t);
    }
    // ==================================printStackTrace==================================


    /**
     * 获取类名与方法名
     */
    private static String getLogTag() {
        StringBuilder builder = new StringBuilder();
        try {
            builder.append("[CurrentThreadName:").append(Thread.currentThread().getName()).append(";");
            StackTraceElement stes[] = Thread.currentThread().getStackTrace();
            StackTraceElement ste = stes[6];
            final String steStr = ste.toString();
            String fileName = ste.getFileName();
            builder.append(fileName.substring(0, fileName.lastIndexOf(".") + 1));
            builder.append(ste.getMethodName());
            builder.append(steStr.substring(steStr.lastIndexOf("(")));
            builder.append("]");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * @param priority {@link #VERBOSE}, {@link #DEBUG}, {@link #WARN}, {@link #ERROR}, {@link #INFO}
     * @param tagO     the tag of message
     * @param msg      the message
     * @param tr       the exception
     */
    private static void log(int priority, Object tagO, String msg, Throwable tr) {
        String tag = String.valueOf(tagO);
        if (enableWrite && (WARN == priority || ERROR == priority || VERBOSE == priority)) {
            writeLogFile(getLogStyle(priority, tag, msg, tr));
        }
        if (!Debug) {
            return;
        }
        StringBuilder logMsg = new StringBuilder();
        logMsg.append(msg);
        // 加上tr
        if (tr != null) {
            logMsg.append("\n");
            logMsg.append(Log.getStackTraceString(tr));
        }
        Log.println(priority, getLogTag(), logMsg.toString());
    }

    /**
     * @param priority {@link #VERBOSE}, {@link #DEBUG}, {@link #WARN}, {@link #ERROR}, {@link #INFO}
     */
    public static String getLogStyle(int priority, Object tag, String msg, Throwable tr) {
        String type;
        switch (priority) {
            case DEBUG:
                type = "DEBUG";
                break;
            case INFO:
                type = "INFO";
                break;
            case WARN:
                type = "WARN";
                break;
            case ERROR:
                type = "ERROR";
                break;
            default:
                type = "VERBOSE";
                break;
        }
        StringBuilder log = new StringBuilder();
        String date = DateFormat.format("yyyy-MM-dd aa hh:mm:ss", System.currentTimeMillis()).toString();
        log.append("[").append(date).append("]");
        log.append("[").append(type).append("]");
        log.append("[").append(tag).append("]");
        log.append(msg);
        if (tr != null) {
            log.append(" ");
            log.append(Log.getStackTraceString(tr));
        }
        return log.toString();
    }

    /**
     * 写入调试日志
     *
     * @param log 要写入的调试内容
     */
    public synchronized static void writeLogFile(String log) {
        long currentTime = System.currentTimeMillis();
        //保存同一小时内的日志
        String logFileName = DateFormat.format("yyyy-MM-dd_hh", currentTime).toString();
        writeLogFile(logFileName + "." + LOG_SUFFIX, log);
    }

    /**
     * 写入调试日志
     *
     * @param filename 要保存的文件名
     * @param content  要写入的调试内容
     */
    public synchronized static void writeLogFile(String filename, String content) {
        try {
            File saveLogDir = new File(mLogDir);
            if (!saveLogDir.exists()) {
                saveLogDir.mkdirs();
            }
            // 创建文件夹失败了
            if (!saveLogDir.exists()) {
                return;
            }
            File fileName = new File(saveLogDir, filename);
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
