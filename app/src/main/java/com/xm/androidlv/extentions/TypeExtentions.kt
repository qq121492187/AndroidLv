package com.xm.androidlv.extentions



//类型扩展方法

/**
 * boolean扩展方法
 * 当true时执行传入的任务
 */
fun Boolean.yes(block: () -> Unit): Boolean {
    if (this) block?.invoke()
    return this
}

/**
 * boolean扩展方法
 * 当false时执行传入的任务
 */
fun Boolean.no(block: () -> Unit): Boolean {
    if (!this) block?.invoke()
    return this
}

/**
 * Int扩展方法，方便px和dp之间转换
 */
//fun Int.dp2px(): Int {
//    return DimenUtils.dp2px(this)
//}