package com.xusto.movielist.service

import com.xusto.movielist.model.Movie
import com.xusto.movielist.model.MovieDetails
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("most-popular")
    fun getMovie(): Single<List<Movie>>
    //fun getMovie(@Query("page") page : Int) : Single<List<Movie>>

    @GET("show-details")
    //fun getDetails(@Query("q") tvShowId: String): Call<MovieDetails>

    fun getDetails() : Single<List<MovieDetails>>
}