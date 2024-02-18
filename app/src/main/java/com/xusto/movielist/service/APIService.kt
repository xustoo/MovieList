package com.xusto.movielist.service

import com.xusto.movielist.model.Movie
import com.xusto.movielist.model.MovieDetails
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
//https://www.episodate.com/api/
class APIService {

    private val BASE_URL = "https://www.episodate.com/api/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieAPI::class.java)

    fun getDataFromMovie() : Single<List<Movie>> {
        return api.getMovie()
    }
    fun getDataFromDetails() : Single<List<MovieDetails>>{
        return api.getDetails()
    }

}
