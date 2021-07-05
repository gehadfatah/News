package com.goda.newstk.features.news.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.goda.newstk.MainActivity
import com.goda.newstk.R
import com.goda.newstk.data.localDb.Article
import com.goda.newstk.databinding.FragmentNewsDetailBinding
import com.goda.newstk.features.news.presentation.ui.adapters.BindingAdapter
import com.goda.newstk.presentation.common.onLinkClick
import com.goda.newstk.utils.NewsConstants
import com.goda.newstk.utils.Utilities

class NewDetailFragment :
    Fragment() {
    private lateinit var article :Article
    private val args: NewDetailFragmentArgs by navArgs()

    private var _binding:FragmentNewsDetailBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentNewsDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      /*  if (arguments != null) {
            article = arguments?.getParcelable(NewsConstants.KEY_ARTICLE)

        }*/

    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        article=args.newsDetail?:Article(source = null,title = "",description = null,url = "",content = null,author = null,urlToImage = null,publishedAt = null)
        setUp()
    }

    private fun setUp() {
        setUpToolbar()
        setArticle()
    }

    private fun setArticle() {
        _binding?.let { fragmentNewsDetailBinding ->
            with(fragmentNewsDetailBinding) {
                article?.let { article1 ->
                    collapsingToolbar.title = article1.title ?: ""
                    headline.text = article1.title ?: ""
                    description.text = article1.description ?: ""
                    sourcetv.text = article1.source ?: ""
                    (getString(R.string.by)+article1.author).also { authorTextView.text = it }
                    content.text=article1.content
                    BindingAdapter.underlinedText(source,article1.url)
                    source.setOnClickListener {
                        view->view.onLinkClick()
                    }
                    if (article1.publishedAt != null) publishDate.text = Utilities.formatDate(article1.publishedAt)
                    Glide.with(requireActivity())
                        .asBitmap()
                        .thumbnail(0.2f)
                        .placeholder(R.drawable.images)
                        .load(article1.urlToImage)
                        .into(image)
                }
            }
        }

    }

    private fun setUpToolbar() {
        if (activity != null) {
            (activity as MainActivity).setSupportActionBar(_binding?.toolbar)
            val actionBar = (activity as MainActivity).supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
            actionBar?.setDisplayShowTitleEnabled(false)
        }
        _binding?.toolbar?.setNavigationOnClickListener {
            if (activity != null) {
                activity?.onBackPressed()
            }
        }
    }
}