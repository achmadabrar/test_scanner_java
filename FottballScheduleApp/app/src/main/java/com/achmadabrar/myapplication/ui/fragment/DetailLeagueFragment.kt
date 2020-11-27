package com.achmadabrar.myapplication.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseFragment

/**
 * Abrar
 */
class DetailLeagueFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_league, container, false)
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            DetailLeagueFragment().apply {
                arguments = Bundle().apply {
                    //
                }
            }
    }
}