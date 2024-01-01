package com.owori.android.core

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.fragment.app.DialogFragment
import com.owori.android.databinding.DialogBaseBinding

class BaseDialogFragment(
    private val title: String,
    private val contents: String,
    private val positiveButtonText: String? = null,
    private val cancelButtonText: String? = null,
    private val onClickPositiveButton: () -> Unit = {}
) : DialogFragment() {
    private var _binding: DialogBaseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogBaseBinding.inflate(inflater, container, false)
        val view = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        with(binding) {
            titleTextview.text = title
            contentsTextview.text = contents

            positiveButtonText?.let { positiveText.text = it }
            cancelButtonText?.let { cancelText.text = it }

            cancelButton.setOnClickListener {
                dialog?.dismiss()
            }

            positiveButton.setOnClickListener {
                onClickPositiveButton()
                dialog?.dismiss()
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        this@BaseDialogFragment.dialog?.window?.setLayout(WRAP_CONTENT, WRAP_CONTENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}