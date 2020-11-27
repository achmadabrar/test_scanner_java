package com.achmadabrar.aruna_test.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.achmadabrar.aruna_test.core.base.BaseViewModel
import com.achmadabrar.aruna_test.data.model.Post
import com.achmadabrar.aruna_test.data.network.JsonPlaceHolderApi
import com.achmadabrar.aruna_test.data.network.NetworkState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    val jsonPlaceHolderApi: JsonPlaceHolderApi
): BaseViewModel<PostsViewModel>() {

    var networkLiveData: MutableLiveData<NetworkState> = MutableLiveData()
    var postsLiveData: MutableLiveData<List<Post>> = MutableLiveData()

    private val supervisorJob = SupervisorJob()

    init {
        loadPosts()
    }

    fun loadPosts() {
        ioScope.launch( getJobErrorHandler() + supervisorJob) {
           val posts = jsonPlaceHolderApi.getPosts()
            postsLiveData.postValue(posts)
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.e(PostsViewModel::class.simpleName, "An error happened: $e")
        networkLiveData.postValue(NetworkState.fialed(e.localizedMessage))
        networkLiveData.postValue(NetworkState.FAILED)
    }
}