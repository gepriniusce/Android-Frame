package pr.tongson.library.utils

import android.util.Log

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/9/23
 * @Version
 * @Since
 * @Description
 */
object L {

    const val VERBOSE = Log.VERBOSE
    const val DEBUG = Log.DEBUG
    const val INFO = Log.INFO
    const val WARN = Log.WARN
    const val ERROR = Log.ERROR


    /**
     * 日志的等级，可以进行配置，最好在Application中进行全局的配置
     */
    var limitLogLevel = ERROR

    var isDebug = true

    private var TAG = "TongsonTAG"

    @JvmStatic
    fun e(msg: String) {
        if (!LogUtils.isDebug) {
            return
        }
        if (ERROR >= limitLogLevel) {
            if (msg.isNotBlank()) {
                val tag = getTag()
                Log.e(TAG, tag + msg)
            }
        }
    }

    @JvmStatic
    fun w(msg: String) {
        if (!LogUtils.isDebug) {
            return
        }
        if (WARN >= limitLogLevel) {
            if (msg.isNotBlank()) {
                val tag = getTag()
                Log.w(TAG, tag + msg)
            }
        }
    }

    @JvmStatic
    fun i(msg: String) {
        if (!LogUtils.isDebug) {
            return
        }
        if (INFO >= limitLogLevel) {
            if (msg.isNotBlank()) {
                val tag = getTag()
                Log.i(TAG, tag + msg)
            }

        }
    }

    @JvmStatic
    fun d(msg: String) {
        if (!LogUtils.isDebug) {
            return
        }
        if (DEBUG >= limitLogLevel) {
            if (msg.isNotBlank()) {
                val tag = getTag()
                Log.d(TAG, tag + msg)
            }
        }
    }

    @JvmStatic
    fun v(msg: String) {
        if (!LogUtils.isDebug) {
            return
        }
        if (VERBOSE >= limitLogLevel) {
            if (msg.isNotBlank()) {
                val tag = getTag()
                Log.v(TAG, tag + msg)
            }
        }
    }

    private fun getTag(): String {
        val sElements = Thread.currentThread().stackTrace
        var stackOffset = getStackOffset(sElements)
        stackOffset++
        val builder = StringBuilder()
        builder.append("[CurrentThreadName:" + Thread.currentThread().name).append(";")
                .append(sElements[stackOffset].className)
                .append(".")
                .append(sElements[stackOffset].methodName)
                .append("(")
                .append(sElements[stackOffset].fileName)
                .append(":")
                .append(sElements[stackOffset].lineNumber)
                .append(")")
        return builder.toString()
    }

    private fun getStackOffset(trace: Array<StackTraceElement>): Int {
        val MIN_STACK_OFFSET = 3
        var i = MIN_STACK_OFFSET
        while (i < trace.size) {
            val e = trace[i]
            val name = e.className
            if (name != L::class.java.name) {
                return --i
            }
            i++
        }
        return -1
    }
}