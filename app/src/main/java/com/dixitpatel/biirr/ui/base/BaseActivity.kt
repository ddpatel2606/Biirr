package com.dixitpatel.biirr.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Base Activity : all activity will extend this and pass their ViewModel object as Generic type.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected inline fun <reified T : ViewDataBinding> binding(
            @LayoutRes resId: Int
    ): Lazy<T> = lazy { DataBindingUtil.setContentView<T>(this, resId) }


    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}