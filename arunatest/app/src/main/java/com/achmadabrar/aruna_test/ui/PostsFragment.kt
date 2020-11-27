package com.achmadabrar.aruna_test.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.aruna_test.R
import com.tokopedia.searchonboardingtokped.core.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


class PostsFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: PostsViewModel

    lateinit var adapter: PostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(PostsViewModel::class.java)

        viewModel.postsLiveData.observe(viewLifecycleOwner, Observer {
            adapter = PostsAdapter(it)
            loadRecyclerView()
        })
    }

    fun loadRecyclerView() {
        recycler_posts.adapter = adapter
        recycler_posts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
    }
}