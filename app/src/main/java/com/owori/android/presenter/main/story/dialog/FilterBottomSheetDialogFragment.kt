package com.owori.android.presenter.main.story.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.owori.android.databinding.FragmentFilterBottomSheetDialogBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FilterBottomSheetDialogFragment(private val onClickFilter: (filter: String) -> Unit, private val filter: String) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentFilterBottomSheetDialogBinding
    @Inject
    lateinit var filterViewModelFactory: FilterViewModel.AssistedFactory
    val viewModel: FilterViewModel by viewModels<FilterViewModel> { FilterViewModel.provideFactory(filterViewModelFactory, filter) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentFilterBottomSheetDialogBinding.inflate(inflater, container, false)
        initObserver()
        Log.d("bottomSheet", "created!!${filter}")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initObserver() {
        with(viewModel) {
            selectedFilter.observe(viewLifecycleOwner) {
                if (isFirst.value == false) {
                    onClickFilter(it)
                    dismiss()
                }
            }
        }
    }
}

