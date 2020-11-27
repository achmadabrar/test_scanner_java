package com.achmadabrar.myapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseFragment
import com.achmadabrar.myapplication.data.models.LeagueUiModel
import com.achmadabrar.myapplication.view.ListLeagueItemDecoration
import com.achmadabrar.myapplication.view.adapters.ListLeagueAdapter
import com.achmadabrar.myapplication.view.viewholders.ListLeagueViewHolder
import com.achmadabrar.myapplication.view.viewmodel.ListLeagueViewModel
import kotlinx.android.synthetic.main.fragment_list_league.*
import javax.inject.Inject


/**
 * Abrar
 */
class ListLeagueFragment : BaseFragment(), ListLeagueViewHolder.Listener {

    @Inject
    lateinit var viewModel: ListLeagueViewModel
    var adapter: ListLeagueAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(ListLeagueViewModel::class.java)

        viewModel.listLeagueLiveData.observe(viewLifecycleOwner, Observer {
            adapter = ListLeagueAdapter(it, this)
            loadRecyclerView()
        })
    }

    fun loadRecyclerView() {
        recycler_list_league.adapter = adapter
        recycler_list_league.layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        recycler_list_league.addItemDecoration(ListLeagueItemDecoration())
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            ListLeagueFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onClickItem(leagueUiModel: LeagueUiModel) {
        Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
    }
}