package com.xm.androidlv.extentions

import android.app.Activity
import android.content.Context
import android.os.Looper
import android.widget.Toast
import androidx.core.content.ContextCompat
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult

val mainHandler: android.os.Handler by lazy { android.os.Handler(Looper.getMainLooper()) }

fun Context.toastCorrect(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    UiThread {
//        ToastUtil.showToast(this, resources.getString(R.string.icon_correct), msg, duration)
    }
}

fun Context.toastWarning(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    UiThread {
//        ToastUtil.showToast(this, resources.getString(R.string.icon_warning), msg, duration)
    }
}

fun Context.toastError(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    UiThread {
//        ToastUtil.showToast(this, resources.getString(R.string.icon_error), msg, duration)
    }
}

fun Context.toastLoading(msg: String = "加载中") {
//    ToastUtil.showToastLoading(this, msg)
}

fun Context.toastHide() {
//    ToastUtil.hideToast()
}

fun Context.UiThread(task: () -> Unit) {
    mainHandler.post {
        task?.invoke()
    }
}

/**
 * 跳转扩展
 */
inline fun <reified T : Activity> Context._go(vararg params: Pair<String, Any?>) {
    startActivity<T>(*params)
}

inline fun <reified T : Activity> Activity._go(requestCode: Int, vararg params: Pair<String, Any?>) {
    startActivityForResult<T>(requestCode, *params)
}

/**
 * 获取屏幕宽
 */
fun Context.getScreenWidth(): Int {
    return resources.displayMetrics.widthPixels
}

/**
 * 获取状态栏高度
 *
 * @param context context
 * @return 状态栏高度
 */
fun Context.getStatusBarHeight(): Int {
    // 获得状态栏高度
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return resources.getDimensionPixelSize(resourceId)
}

/**
 * 获取color
 */
fun Context.getResColor(resId: Int): Int {
    return ContextCompat.getColor(this, resId)
}



