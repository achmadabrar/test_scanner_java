package com.achmadabrar.myapplication.view.viewmodel

import android.util.Log
import com.achmadabrar.myapplication.core.base.BaseViewModel
import com.achmadabrar.myapplication.data.models.League
import com.achmadabrar.myapplication.data.networks.FootbalScheduleApi
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val scheduleApi: FootbalScheduleApi
) : BaseViewModel<HomeViewModel>() {

    init {

    }


}