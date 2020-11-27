package com.achmadabrar.myapplication.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.achmadabrar.myapplication.core.base.BaseViewModel
import com.achmadabrar.myapplication.data.models.League
import com.achmadabrar.myapplication.data.models.LeagueUiModel
import com.achmadabrar.myapplication.data.networks.FootbalScheduleApi
import io.reactivex.Single
import javax.inject.Inject

class ListLeagueViewModel @Inject constructor(
    private val scheduleApi: FootbalScheduleApi
) : BaseViewModel<ListLeagueViewModel>() {

    private val _LeagueUiLiveData: MutableLiveData<LeagueUiModel> = MutableLiveData()
    val leagueUiLiveData: LiveData<LeagueUiModel>
        get() = _LeagueUiLiveData

    private var leagueUiModel: LeagueUiModel = LeagueUiModel()
        set(value) {
            field = value
            _LeagueUiLiveData.postValue(value)
        }

    var listLeagueLiveData: MutableLiveData<List<LeagueUiModel>> = MutableLiveData()
    var list = mutableListOf<LeagueUiModel>()

    init {
        loadLeagueData()
    }

    private fun loadLeagueData() {
        disposables.add(
            getAllLeagues().doOnSuccess {
                it.forEach {
                    getLeagueDetail(it.id).subscribe({
                        it.map {
                            if (it.sport.equals("Soccer") && !it.logo.isNullOrEmpty()) {
                                val data = leagueUiModel.copy(id = it.id, name = it.leagueName, logo = it.logo)
                                list.add(data)
                            }
                        }
                    }, {
                    })
                }
            }.subscribe({
                listLeagueLiveData.postValue(list)
            }, {
                Log.d("error", "${it.localizedMessage}")
            })
        )
    }

    private fun getAllLeagues(): Single<List<League>> {
        return scheduleApi.getAllLeagues().map { leagues ->
            leagues.leagues.map {
                League(it.id, it.leagueName, it.sport, null, null, null, null, null, null)
            }
        }
    }

    private fun getLeagueDetail(idLeague: Long): Single<List<League>> {
        return scheduleApi.getDetailLeague(idLeague).map { leagues ->
            leagues.leagues.map {
                League(
                    id = it.id,
                    leagueName = it.leagueName,
                    sport = it.sport,
                    website = it.website,
                    facebook = it.facebook,
                    twitter = it.twitter,
                    youtube = it.youtube,
                    description = it.description,
                    logo = it.logo
                )
            }
        }
    }
}