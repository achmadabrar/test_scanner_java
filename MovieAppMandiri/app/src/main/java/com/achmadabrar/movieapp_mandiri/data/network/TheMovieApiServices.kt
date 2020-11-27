package com.achmadabrar.movieapp_mandiri.data.network

import com.achmadabrar.movieapp_mandiri.data.model.ResponseGenres
import retrofit2.http.GET

interface TheMovieApiServices {

    companion object{
        const val GENRES = "genre/movie/list"
    }

    @GET (GENRES)
    fun get() : ResponseGenres
}