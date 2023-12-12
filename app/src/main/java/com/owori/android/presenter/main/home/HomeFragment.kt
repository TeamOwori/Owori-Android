package com.owori.android.presenter.main.home

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import com.owori.android.R
import com.owori.android.core.BaseDialogFragment
import com.owori.android.core.BaseFragment
import com.owori.android.databinding.FragmentHomeBinding
import com.owori.android.presenter.main.home.adapter.DdayAdapter
import com.owori.android.presenter.main.home.adapter.FamilyMemberAdapter
import com.owori.android.presenter.main.home.adapter.FamilyMemberWordAdapter
import com.owori.android.presenter.main.home.adapter.FamilyPhotoAdapter
import com.owori.android.presenter.util.SnapPagerScrollListener
import com.owori.android.presenter.util.SnapPagerScrollListener.Companion.ON_SCROLL
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModels()
    private val emotionListAdapter: FamilyMemberAdapter by lazy {
        FamilyMemberAdapter {
            EmotionActivity.startActivity(
                requireContext()
            )
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
    private val familyPhotoAdapter: FamilyPhotoAdapter by lazy { FamilyPhotoAdapter() }
    private val dDaySnapHelper = PagerSnapHelper()
    private val dDayScrollListener = SnapPagerScrollListener(dDaySnapHelper,
        ON_SCROLL,
        true,
        object : SnapPagerScrollListener.OnChangeListener {
            override fun onSnapped(position: Int) {}
        })
    private val familyPhotoSnapHelper = PagerSnapHelper()
    override fun onResume() {
        super.onResume()
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.yellow_ffeeb2)
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
                EmotionActivity.startActivity(requireContext())
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
                } else Toast.makeText(requireContext(), "1 ~ 50 글자를 입력해 주세요 🥲", Toast.LENGTH_SHORT)
                    .show()
            }
            showDeleteMyWordDialog.observe(viewLifecycleOwner) {
                BaseDialogFragment(title = "삭제하기",
                    contents = "‘서로에게 한마디’를 삭제하시겠습니까?",
                    onClickPositiveButton = { deleteMyWord() }).show(
                    requireActivity().supportFragmentManager,
                    "DeleteMyWordDialog"
                )
            }
            showEditCancelMyWordDialog.observe(viewLifecycleOwner) {
                BaseDialogFragment(
                    title = if (familyInfo.value?.me?.word == null) "작성 취소"
                            else "수정 취소",
                    contents = if (familyInfo.value?.me?.word == null) "작성한 내용 모두 사라집니다.\n작성을 취소하시겠습니까?"
                               else "수정 이전 내용으로 되돌아갑니다.\n수정을 취소하시겠습니까?",
                    onClickPositiveButton = { setEditMode(false) }).show(
                    requireActivity().supportFragmentManager,
                    "DeleteMyWordDialog"
                )
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
}