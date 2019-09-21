package com.xm.androidlv.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import java.lang.Exception

/**
 *Create by lvhaoran
 *on 2019/9/21
 */
abstract class BasePageAdapter<T>(
    val mContext: Context,
    diffCallBack: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, BaseViewHolder>(diffCallBack) {

    private val HeadType = -2
    private val mInflater by lazy { LayoutInflater.from(mContext) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            HeadType -> createHeaderViewHolder(parent)
            else -> createBodyViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (needHeader() && position == 0) {
            onBindHeader()
        } else {
            onBindItem(holder,position)
        }
    }

    private fun createHeaderViewHolder(parent: ViewGroup): HeaderViewHolder {
        if (getHeaderLayoutId() == -1) {
            throw Exception("header layout is -1")
        }
        val viewBinding =
            DataBindingUtil.inflate<ViewDataBinding>(mInflater, getHeaderLayoutId(), parent, false)
        return HeaderViewHolder(viewBinding)
    }

    private fun createBodyViewHolder(parent: ViewGroup): BaseViewHolder {
        val viewBinding =
            DataBindingUtil.inflate<ViewDataBinding>(mInflater, getItemLayoutId(), parent, false)
        return BaseViewHolder(viewBinding)
    }

    abstract fun getItemLayoutId(): Int
    abstract fun onBindItem(holder: BaseViewHolder, position: Int)
    fun getHeaderLayoutId(): Int = -1
    fun onBindHeader() {}

    override fun getItemCount(): Int {
        currentList?.let {
            return if (needHeader())
                it.size + 1
            else
                super.getItemCount()
        }
        return super.getItemCount()
    }

    override fun getItemViewType(position: Int): Int {
        if (needHeader() && position == 0) return HeadType
        return super.getItemViewType(position)
    }

    open fun needHeader(): Boolean {
        return false
    }

    class HeaderViewHolder(viewBinding: ViewDataBinding) : BaseViewHolder(viewBinding)

}