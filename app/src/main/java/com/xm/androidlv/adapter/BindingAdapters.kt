package com.xm.androidlv.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 *Create by lvhaoran
 *on 2019/9/21
 *
 * dataBinding的bindingAdapter
 * 自定义属性提供xml中使用
 */

/**
 * image加载url图片
 */
@BindingAdapter("imageUrl")
fun loadImageForUrl(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(imageView.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
}
