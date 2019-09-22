package com.xm.androidlv.architecture.mvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import com.jaeger.library.StatusBarUtil
import com.xm.androidlv.R
import com.xm.androidlv.databinding.ActivityMainBinding
import com.xm.androidlv.extentions.getResColor
import com.xm.androidlv.extentions.setLightStatusBarForM
import com.xm.androidlv.net.resource.Resource
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

/**
 *Create by lvhaoran
 *on 2019/9/20
 *
 * activity基类
 */
abstract class BaseActivity<T> : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    protected lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, bindLayoutResId())
        initializeUI()
        val uiObservable = Observer<Resource<T>> {
            handleResource(it)
        }
        observableUI(uiObservable)
    }

    abstract fun bindLayoutResId(): Int
    abstract fun observableUI(observer: Observer<Resource<T>>)
    abstract fun subscribeUI(data: T)

    fun initializeUI() {
        initStatusBar()
    }

    open fun initStatusBar() {
        setStatusBarDartMode()
        setStatusBarColor(getStatusBarColor())
    }

    fun setStatusBarDartMode() {
        setLightStatusBarForM(this, true)
    }

    fun setStatusBarColor(color: Int) {
        StatusBarUtil.setColor(this, color, 0)
    }

    open fun getStatusBarColor(): Int {
        return getResColor(R.color.colorPrimary)
    }

    fun subscribeLoading() {}
    fun subscribeError(message: String) {}


    fun handleResource(resource: Resource<T>) {
        when (resource) {
            is Resource.Loading -> {
                subscribeLoading()
            }
            is Resource.Success -> {
                subscribeUI(resource.data!!)
            }
            is Resource.Error -> {
                subscribeError(resource.message)
            }
        }
    }


    fun requestPermissions(requestCode: Int, perms: Array<String>) {
        EasyPermissions.requestPermissions(this, "需要以下权限，请允许", requestCode, *perms)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        //权限申请失败
        val sb = StringBuffer()
        for (str in perms) {
            sb.append(str).append("\n")
        }
        sb.replace(sb.length - 2, sb.length, "")
        //用户点击拒绝并且不在询问时
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this)
                .setRationale("此功能需要${sb}权限，否则无法正常使用，是否打开设置")
                .setPositiveButton("打开")
                .setNegativeButton("关闭")
                .build()
                .show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        //权限申请成功
        Log.i("EasyPermissions", "获取成功的权限\$perms")
    }
}