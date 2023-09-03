package com.owrori.android.ui.common.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.owrori.android.ui.common.presentation.viewmodel.BaseViewModel

abstract class BaseFragment<B : ViewDataBinding, V : BaseViewModel>(@LayoutRes private val layoutId: Int) :
    Fragment() {
    abstract val viewModel: V
    val binding: B get() = _binding!!
    private var _binding: B? = null

    abstract fun setBindingVariables()
    abstract fun initObserver()
    abstract fun initView()

    protected open fun navigate(navigation: Navigation) {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(layoutInflater, layoutId, container, false)
        setBindingVariables()
        with(binding) {
            lifecycleOwner = this@BaseFragment
            executePendingBindings()
            return root
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}