package com.xm.androidlv.example

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import com.xm.androidlv.BR
import com.xm.androidlv.R
import com.xm.androidlv.adapter.BasePageAdapter
import com.xm.androidlv.adapter.BaseViewHolder

/**
 *Create by lvhaoran
 *on 2019/9/21
 */
class ExamplePageAdapter(context: Context) : BasePageAdapter<ExampleData.IconList>(context, diff) {

    companion object {
        val diff = object : DiffUtil.ItemCallback<ExampleData.IconList>() {
            override fun areItemsTheSame(
                oldItem: ExampleData.IconList,
                newItem: ExampleData.IconList
            ): Boolean = oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: ExampleData.IconList,
                newItem: ExampleData.IconList
            ): Boolean = oldItem == newItem
        }
    }

    override fun getItemLayoutId(): Int = R.layout.example_item

    override fun onBindItem(holder: BaseViewHolder, position: Int) {
        holder.viewBinding.setVariable(BR.iconData, getItem(position))
        //数据更改时立即执行数据的更新
        holder.viewBinding.executePendingBindings()
    }
}