package com.owori.android.core

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.owori.android.databinding.DialogBaseBinding

class BaseDialogFragment(
    private val title: String,
    private val contents: String,
    private val onClickPositiveButton: () -> Unit = {}
) : DialogFragment() {
    private var _binding: DialogBaseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogBaseBinding.inflate(inflater, container, false)
        val view = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        with(binding) {
            titleTextview.text = title
            contentsTextview.text = contents

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
        requireContext().dialogFragmentResize(this@BaseDialogFragment, dialogX, dialogY)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun Context.dialogFragmentResize(dialogFragment: DialogFragment, width: Float, height: Float) {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        if (Build.VERSION.SDK_INT < 30) {
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            val window = dialogFragment.dialog?.window
            val x = (size.x * width).toInt()
            val y = (size.y * height).toInt()
            window?.setLayout(x, y)
        } else {
            val rect = windowManager.currentWindowMetrics.bounds
            val window = dialogFragment.dialog?.window
            val x = (rect.width() * width).toInt()
            val y = (rect.height() * height).toInt()
            window?.setLayout(x, y)
        }
    }

    companion object {
        private const val dialogX = 0.9f
        private const val dialogY = 0.3f
    }
}