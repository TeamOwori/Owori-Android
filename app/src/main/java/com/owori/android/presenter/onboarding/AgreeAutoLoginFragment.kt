package com.owori.android.auth.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.owori.android.R
import com.owori.android.databinding.FragmentAgreeAutoLoginBinding


class AgreeAutoLoginFragment: BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAgreeAutoLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAgreeAutoLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


}