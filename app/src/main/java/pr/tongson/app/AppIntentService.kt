package pr.tongson.app

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log
import pr.tongson.library.utils.L
import pr.tongson.library.utils.LogUtils

// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
private const val ACTION_FOO = "pr.tongson.app.action.FOO"
private const val ACTION_BAZ = "pr.tongson.app.action.BAZ"

// TODO: Rename parameters
private const val EXTRA_PARAM1 = "pr.tongson.app.extra.PARAM1"
private const val EXTRA_PARAM2 = "pr.tongson.app.extra.PARAM2"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.

 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.

 */
class AppIntentService : IntentService("AppIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_FOO -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionFoo(param1, param2)
            }
            ACTION_BAZ -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionBaz(param1, param2)
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionFoo(param1: String, param2: String) {
//        Log.i("AppIntentService", "param1:" + param1 + ";param2:" + param2)
        L.i("AppIntentService param1:" + param1 + ";param2:" + param2)
//        LogUtils.i("AppIntentService param1:" + param1 + ";param2:" + param2)
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionBaz(param1: String, param2: String) {
//        Log.i("AppIntentService", "AppIntentService param1:" + param1 + ";param2:" + param2)
        L.i("AppIntentService param1:" + param1 + ";param2:" + param2)
//        LogUtils.i("AppIntentService param1:" + param1 + ";param2:" + param2)
    }

    companion object {
        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionFoo(context: Context, param1: String, param2: String) {
            val intent = Intent(context, AppIntentService::class.java).apply {
                action = ACTION_FOO
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }

        /**
         * Starts this service to perform action Baz with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionBaz(context: Context, param1: String, param2: String) {
            val intent = Intent(context, AppIntentService::class.java).apply {
                action = ACTION_BAZ
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }
    }
}