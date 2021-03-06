package com.achmadabrar.movieapp_mandiri.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.achmadabrar.movieapp_mandiri.core.base.BaseViewModel
import com.achmadabrar.movieapp_mandiri.data.datasource.ListMovieDataSource
import com.achmadabrar.movieapp_mandiri.data.factory.ListMovieFactory
import com.achmadabrar.movieapp_mandiri.data.model.Genre
import com.achmadabrar.movieapp_mandiri.data.model.ResponseGenres
import com.achmadabrar.movieapp_mandiri.data.network.NetworkState
import com.achmadabrar.movieapp_mandiri.data.network.TheMovieApiServices
import com.achmadabrar.movieapp_mandiri.data.model.Result
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val api: TheMovieApiServices
) : BaseViewModel<MovieViewModel>() {

    //section for get list movie
    var networkLiveData: LiveData<NetworkState>
    var listMovieLiveData: LiveData<PagedList<Result>>
    var listMovieFactory: ListMovieFactory

    var listByGenre: MutableLiveData<PagedList<Result>> = MutableLiveData()

    //section for get list genre
    val networkStatusLiveData: MutableLiveData<NetworkState> = MutableLiveData()
    private val supervisorJob = SupervisorJob()
    var genrePageLiveData: MutableLiveData<ResponseGenres> = MutableLiveData()
    var genreSelectedLiveData: MutableLiveData<Genre> = MutableLiveData()
    var genres: Genre?= null

    init {
        //for get genre
        getGenreFromApi()

        //for get the list movie
        listMovieFactory = ListMovieFactory(
            ioScope, api
        )

        val config = PagedList.Config.Builder()
            .setPageSize(ListMovieDataSource.PAGE_SIZE)
            .setInitialLoadSizeHint(ListMovieDataSource.INITIAL_PAGE)
            .setPrefetchDistance(ListMovieDataSource.PREFECTH_DISTANCE)
            .setEnablePlaceholders(false)
            .build()

        listMovieLiveData = LivePagedListBuilder<Int, Result>(listMovieFactory, config).build()
        networkLiveData = Transformations.switchMap(listMovieFactory.dataSourceLiveData) {
            it.networkStatusLiveData
        }
    }

    fun getGenreFromApi() {
        ioScope.launch { getJobErrorHandler() + supervisorJob
            val genres = api.getGenres()
            genrePageLiveData.postValue(genres)
            if (genres.genres.isEmpty()) {
                networkStatusLiveData.postValue(NetworkState.EMPTY)
            } else {
                networkStatusLiveData.postValue(NetworkState.LOADED)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.e(ListMovieDataSource::class.java.simpleName, "An error happened: $e")
        networkStatusLiveData.postValue(NetworkState.fialed(e.localizedMessage))
    }

    fun getGenreSelected(genre: Genre?) {
        genreSelectedLiveData.postValue(genre)
        genres = genre
    }

    fun getMovie(result: PagedList<Result>?) {
        result?.forEach {
            it.genreId.forEach {
                if (genres?.id != null) {
                    if (it.toLong() == genres!!.id) {
                        Log.d("genreId", "$it sama dengan ${genres!!.id}")
                        listByGenre.postValue(result)
                    }
                }
            }
        }
    }
}