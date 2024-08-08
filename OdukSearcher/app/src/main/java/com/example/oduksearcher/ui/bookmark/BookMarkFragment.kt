package com.example.oduksearcher.ui.bookmark

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oduksearcher.databinding.FragmentBookMarkBinding
import com.example.oduksearcher.ui.search.SearchAdapter
import com.example.oduksearcher.util.GridSpacingItemDecoration
import com.example.oduksearcher.util.px

class BookMarkFragment : Fragment() {
    private var _binding: FragmentBookMarkBinding? = null
    private val binding get() = _binding!!

    private val bookMarkViewModel: BookMarkViewModel by activityViewModels {
        BookMarkViewModelFactory(requireContext())
    }
    private val searchAdapter by lazy { SearchAdapter { bookMarkViewModel.removeBookmark(it) } }

    companion object {
        fun newInstance() = BookMarkFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookMarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initViewModel() = with(bookMarkViewModel) {
        bookmarkList.observe(viewLifecycleOwner) {
            Log.d("dataCHK",it.toString())
            searchAdapter.submitList(it)
        }
    }

    private fun initView() = with(binding) {
        bookmarkRvSearchRes.run {
            adapter = searchAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(GridSpacingItemDecoration(2, 16f.px))
        }
    }
}