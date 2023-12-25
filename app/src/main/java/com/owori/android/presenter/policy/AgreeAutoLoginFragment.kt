package com.owori.android.presenter.policy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.owori.android.databinding.FragmentAgreeAutoLoginBinding


class AgreeAutoLoginFragment: BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAgreeAutoLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAgreeAutoLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.autoLoginOk.setOnClickListener {
//            navigateTo()
        }
        binding.autoLoginNo.setOnClickListener {
//            navigateTo()
        }
    }


}