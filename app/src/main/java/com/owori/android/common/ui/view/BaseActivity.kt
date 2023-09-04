package com.owori.android.common.ui.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import com.owori.android.common.ui.viewmodel.BaseViewModel

abstract class BaseActivity<B : ViewDataBinding, V : BaseViewModel>(@LayoutRes private val layoutId: Int) :
    AppCompatActivity() {
    abstract val viewModel: V
    lateinit var binding: B
    abstract fun setBindingVariables(binding: B)
    abstract fun initView()
    protected abstract fun initObserver()
    protected open fun navigate(navigation: Navigation) {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        setBindingVariables(binding)
        with(binding) {
            lifecycleOwner = this@BaseActivity
            executePendingBindings()
        }
        initObserver()
        initView()
    }
}