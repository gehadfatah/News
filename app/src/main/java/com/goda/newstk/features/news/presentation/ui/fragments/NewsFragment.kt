package com.goda.newstk.features.news.presentation.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goda.newstk.R
import com.goda.newstk.features.news.presentation.ui.adapters.ArticlesAdapter
import com.goda.newstk.features.news.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.*

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val viewModel by viewModels<NewsViewModel>()
    private val adapter = ArticlesAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDrawer()
        initListeners()
        initRecycler()
        initSwipeRefresh()
    }

    private fun initDrawer() {


    }

    private fun initListeners() {
        initViewModelListeners()
    }

    private fun initViewModelListeners() {
        viewModel.articles.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })


    }


    private fun initRecycler() {
        newsRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        newsRecycler.adapter = adapter
        newsRecycler.scrollToPosition(0)

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                if (positionStart == 0) newsRecycler.scrollToPosition(0)
            }
        })
    }

    private fun initSwipeRefresh() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.updateNews()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}