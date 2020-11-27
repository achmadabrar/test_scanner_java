package com.achmadabrar.myapplication.view.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseActivity
import com.achmadabrar.myapplication.view.fragment.HomeFragment
import com.achmadabrar.myapplication.view.fragment.ListLeagueFragment
import com.achmadabrar.myapplication.view.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, ListLeagueFragment())
        transaction.commit()
    }
}