package com.xm.androidlv.extentions

import android.animation.ValueAnimator
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView


const val MIN_DELAY_TIME: Int = 500 // 最小间隔500ms
private var lastClickTime: Long = 0

fun isFastClick(): Boolean {
    var flag = false
    val currentTime = System.currentTimeMillis()
    println(lastClickTime)
    if ((currentTime - lastClickTime) >= MIN_DELAY_TIME) {
        flag = true
    }
    lastClickTime = currentTime
    return flag
}

fun isFastClick(task: () -> Unit) {
    val currentTime = System.currentTimeMillis()
    println(lastClickTime)
    if ((currentTime - lastClickTime) >= MIN_DELAY_TIME) {
        task?.invoke()
    }
    lastClickTime = currentTime
}

/**
 * 扩展view点击事件 处理点击过快
 */
fun View._onClickWithoutFast(block: (it: View) -> Unit) {
    this.setOnClickListener { isFastClick { block?.invoke(this@_onClickWithoutFast) } }
}


/**
 * 扩展imageView，glide加载图片
 *
 * @param url   图片地址
 * @param requestOptions    参数设置
 * @param transitionOptions 动画设置，默认淡入淡出动画
 */
//fun ImageView.loadWidthGlide(url: String, requestOptions: RequestOptions = RequestOptions(), transitionOptions: TransitionOptions<*, Drawable> = DrawableTransitionOptions()) {
//    Glide.with(this.context)
//            .load(url)
//            .apply(requestOptions)
//            .transition(transitionOptions)
//            .into(this)
//}

/**
 * 获取imageview的bitmap
 */
fun ImageView.getBitmap(): Bitmap? {
    isDrawingCacheEnabled = true
    val bitmap = Bitmap.createBitmap(drawingCache)
    isDrawingCacheEnabled = false
    return bitmap
}
