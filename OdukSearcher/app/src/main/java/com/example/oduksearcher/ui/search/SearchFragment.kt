package com.example.oduksearcher.ui.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oduksearcher.ui.bookmark.BookMarkViewModel
import com.example.oduksearcher.ui.bookmark.BookMarkViewModelFactory
import com.example.oduksearcher.databinding.FragmentSearchBinding
import com.example.oduksearcher.ui.ListItem
import com.example.oduksearcher.ui.copy
import com.example.oduksearcher.ui.find
import com.example.oduksearcher.ui.isBookmarked
import com.example.oduksearcher.util.GridSpacingItemDecoration
import com.example.oduksearcher.util.hideKeyBoard
import com.example.oduksearcher.util.px

private const val SEARCH_TEXT = "search_text"

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchViewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory()
    }
    private val bookmarkViewModel: BookMarkViewModel by activityViewModels {
        BookMarkViewModelFactory(requireContext())
    }
    private val searchAdapter by lazy {
        SearchAdapter { item ->
            if (item.isBookmarked) bookmarkViewModel.removeBookmark(item)
            else {
                item.copy(isBookmarked = true).also {
                    bookmarkViewModel.addBookmark(it)
                }
            }
        }
    }

    private var currentPage = 1
    private val result = mutableListOf<ListItem>()
    private val loadingItem = ListItem.LoadingItem(true) as ListItem
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onAttach(context: Context) {//처음 화면 붙여질때
        super.onAttach(context)
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val searchText = result.data?.getStringExtra(SEARCH_TEXT)
                    binding.etSearch.setText(searchText)
                    handleSubmitInput()
                }
            }
    }

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
        initScrollListener()
    }

    private fun initViewModel() {
        searchViewModel.searchResult.observe(viewLifecycleOwner) {
            result.addAll(it)
            searchAdapter.submitList(listOf(*result.toTypedArray(), loadingItem))
        }
        bookmarkViewModel.bookmarkList.observe(viewLifecycleOwner) { bookmarks ->
            result.replaceAll { r ->
                when {
                    bookmarks.find(r) == null && r.isBookmarked -> r.copy(isBookmarked = false)
                    bookmarks.find(r) != null && !r.isBookmarked -> r.copy(isBookmarked = true)
                    else -> r
                }
            }
            searchAdapter.submitList(result.toList())
        }
    }

    private fun initView() = with(binding) {
        btnSearch.setOnClickListener {//검색 눌렀을때
            val searchText = etSearch.text.toString()
            if (searchText.isNotEmpty()) {
                requireContext().hideKeyBoard(btnSearch)

                currentPage = 0
                result.clear()
                fetchNextPage()
                return@setOnClickListener
            }else{
                Toast.makeText(requireContext(), "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        rvSearch.run {
            adapter = searchAdapter
            layoutManager = GridLayoutManager(requireContext(), 2).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (searchAdapter.getItemViewType(position)) {
                            SearchAdapter.TYPE_LOADING -> 2
                            else -> 1
                        }
                    }
                }
            }
            addItemDecoration(GridSpacingItemDecoration(2, 16f.px))
        }
        fabToTop.setOnClickListener { rvSearch.smoothScrollToPosition(0) }
    }

    private fun handleSubmitInput() = with(binding) {
        val searchText = etSearch.text.toString()

        if (searchText.isNotEmpty()) {
            currentPage = 0
            result.clear()
            fetchNextPage()
            Toast.makeText(requireContext(), "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
    }

    private fun fetchNextPage() = with(binding) {//페이지 추가로딩
        searchAdapter.stopLoading()
        searchViewModel.fetchSearchResult(etSearch.text.toString(), ++currentPage)
    }

    private fun initScrollListener() = with(binding) {
        rvSearch.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var isVisible = false
            val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 200 }
            val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 200 }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // infinite scroll
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter?.itemCount ?: 0

                if (!rvSearch.canScrollVertically(1) && lastVisibleItemPosition >= itemTotalCount - 1) {
                    fetchNextPage()
                }

                // scroll to top
                // 더이상 위로 스크롤 할 수 없거나 버튼이 보이고 아래로 스크롤 중일 때
                if (!recyclerView.canScrollVertically(-1) || (isVisible && dy > 0)) {
                    fabToTop.visibility = View.GONE
                    fabToTop.startAnimation(fadeOut)
                    isVisible = false
                }

                // 버튼이 보이지 않고 위로 스크롤 중일 때
                else if (!isVisible && dy < 0) {
                    fabToTop.visibility = View.VISIBLE
                    fabToTop.startAnimation(fadeIn)
                    isVisible = true
                }
            }
        })
    }
}