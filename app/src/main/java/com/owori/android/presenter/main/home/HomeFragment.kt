package com.owori.android.presenter.main.home

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import com.owori.android.R
import com.owori.android.core.BaseDialogFragment
import com.owori.android.core.BaseFragment
import com.owori.android.databinding.FragmentHomeBinding
import com.owori.android.presenter.emotion.EmotionActivity
import com.owori.android.presenter.main.home.adapter.DdayAdapter
import com.owori.android.presenter.main.home.adapter.FamilyMemberAdapter
import com.owori.android.presenter.main.home.adapter.FamilyMemberWordAdapter
import com.owori.android.presenter.main.home.adapter.FamilyPhotoAdapter
import com.owori.android.presenter.main.home.mypage.MyPageActivity
import com.owori.android.presenter.main.home.notice.NoticeActivity
import com.owori.android.presenter.model.FamilyPhotoItem
import com.owori.android.presenter.model.FamilyPhotoItem.FamilyPhotoViewType.PHOTO
import com.owori.android.presenter.model.PhotoData
import com.owori.android.presenter.util.SnapPagerScrollListener
import com.owori.android.presenter.util.SnapPagerScrollListener.Companion.ON_SCROLL
import dagger.hilt.android.AndroidEntryPoint
import gun0912.tedimagepicker.builder.TedImagePicker


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModels()
    private val emotionListAdapter: FamilyMemberAdapter by lazy {
        FamilyMemberAdapter { clickedFamilyMemberId ->
            if (clickedFamilyMemberId == viewModel.familyInfo.value?.me?.id) {
                EmotionActivity.startActivity(
                    requireContext()
                )
            }
        }
    }
    private val inputMethodManager: InputMethodManager by lazy {
        requireActivity().getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
    }
    private val dDayListAdapter: DdayAdapter by lazy {
        DdayAdapter { id ->
            viewModel.deleteDdayItem(
                id
            )
        }
    }
    private val familyPhotoAdapter: FamilyPhotoAdapter by lazy { FamilyPhotoAdapter { viewModel.onClickAddPhoto() } }
    private val dDaySnapHelper = PagerSnapHelper()
    private val dDayScrollListener =
        SnapPagerScrollListener(
            dDaySnapHelper,
            ON_SCROLL,
            true,
            object : SnapPagerScrollListener.OnChangeListener {
                override fun onSnapped(position: Int) = Unit
            })
    private val familyPhotoSnapHelper = PagerSnapHelper()
    override fun onResume() {
        super.onResume()
        setStatusBarColor(getColor(requireContext(), R.color.yellow_ffeeb2))
    }

    private val familyPhotoScrollListener = SnapPagerScrollListener(familyPhotoSnapHelper,
        ON_SCROLL,
        true,
        object : SnapPagerScrollListener.OnChangeListener {
            override fun onSnapped(position: Int) {}
        })
    private val familyMemberWordAdapter: FamilyMemberWordAdapter by lazy { FamilyMemberWordAdapter() }

    override fun setBindingVariables() {
        with(binding) {
            vm = viewModel
        }
    }

    override fun initObserver() {
        with(viewModel) {
            familyEmotionList.observe(viewLifecycleOwner) {
                emotionListAdapter.submitList(it)
            }
            dDayList.observe(viewLifecycleOwner) {
                dDayListAdapter.submitList(it)
            }
            familyPhotoList.observe(viewLifecycleOwner) {
                familyPhotoAdapter.submitList(it)
            }
            familyMemberList.observe(viewLifecycleOwner) {
                familyMemberWordAdapter.submitList(it)
            }
            noticeButtonClicked.observe(viewLifecycleOwner) {
                NoticeActivity.startActivity(requireContext())
            }
            emotionButtonClicked.observe(viewLifecycleOwner) {
                MyPageActivity.startActivity(requireContext())
            }
            isEditMode.observe(viewLifecycleOwner) { _isEditMode ->
                if (_isEditMode) {
                    with(binding.myWordEditorView) {
                        isFocusableInTouchMode = true
                        requestFocus()
                    }
                    inputMethodManager.showSoftInput(binding.myWordEditorView, 0)
                }
            }
            editCompleted.observe(viewLifecycleOwner) {
                val editedMyWord = binding.myWordEditorView.text.toString()
                if (editedMyWord.isNotBlank()) {
                    editMyWord(binding.myWordEditorView.text.toString())
                } else Toast.makeText(
                    requireContext(),
                    getString(R.string.message_write_right_word_in_range),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            showDeleteMyWordDialog.observe(viewLifecycleOwner) {
                BaseDialogFragment(title = getString(R.string.dialog_delete_title),
                    contents = getString(R.string.dialog_delete_contents),
                    onClickPositiveButton = { deleteMyWord() })
                    .show(
                        requireActivity().supportFragmentManager,
                        getString(R.string.dialog_delete)
                    )
            }
            showEditCancelMyWordDialog.observe(viewLifecycleOwner) {
                BaseDialogFragment(
                    title = if (familyInfo.value?.me?.word == null) getString(R.string.dialog_write_cancel_title)
                    else getString(R.string.dialog_edit_cancel_title),
                    contents = if (familyInfo.value?.me?.word == null) getString(R.string.dialog_write_cancel_contents)
                    else getString(R.string.dialog_edit_cancel_contents),
                    onClickPositiveButton = { setEditMode(false) }).show(
                    requireActivity().supportFragmentManager,
                    getString(R.string.dialog_edit)
                )
            }
            showTedImagePicker.observe(viewLifecycleOwner) {
                TedImagePicker.with(requireContext())
                    .max(MAX_PHOTO_SIZE - (familyPhotoList.value?.filter {it.photoData != null }?.size ?: 0), SIZE_WARN)
                    .showCameraTile(false)
                    .startMultiImage { uris ->
                        viewModel.setFamilyPhotoList(uris.mapIndexed { index, uri ->
                            FamilyPhotoItem(PHOTO, PhotoData(id = index, imageSrc = uri.toString()))
                        })
                    }
            }
        }
    }

    override fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        initEmotionRecyclerView()
        initDdayRecyclerView()
        initFamilyPhotoRecyclerView()
        initFamilyMemberWordRecyclerView()
    }

    private fun initFamilyPhotoRecyclerView() {
        with(binding.familyPhotoRecyclerView) {
            familyPhotoSnapHelper.attachToRecyclerView(this)
            adapter = familyPhotoAdapter
            addOnScrollListener(familyPhotoScrollListener)
        }
    }

    private fun initDdayRecyclerView() {
        with(binding.dDayRecyclerView) {
            dDaySnapHelper.attachToRecyclerView(this)
            adapter = dDayListAdapter
            addOnScrollListener(dDayScrollListener)
        }
    }

    private fun initEmotionRecyclerView() {
        binding.emotionRecyclerView.adapter = emotionListAdapter
    }

    private fun initFamilyMemberWordRecyclerView() {
        binding.familyMemberWordRecyclerView.adapter = familyMemberWordAdapter
    }

    companion object {
        const val MAX_PHOTO_SIZE = 5
        const val SIZE_WARN = "최대 5장의 사진만 선택 가능합니다."
    }
}