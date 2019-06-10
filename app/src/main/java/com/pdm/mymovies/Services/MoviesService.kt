package com.pdm.mymovies.Services

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pdm.mymovies.Database.entities.Movie
import com.pdm.mymovies.Database.entities.MovieDetail
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap




const val GITHUB_BASE_URL = "https://www.omdbapi.com/"
const val TOKEN_API = "55b04979"

interface MoviesService{

    @GET("/&s={movie}")
    fun getMovies(@Path("movie") movie:String,@Query("apikey") apikey:String): Deferred<Response<List<Movie>>>
/*
    @GET("/&t={movie}")
    fun getDetailedMovie(@Path("movie") movie:String,@Query("apikey") apikey:String): Deferred<Response<List<MovieDetail>>>
*/
    @GET("/?apikey=55b04979&t={movie}")
    fun getDetailedMovie(@Path("movie") movie:String): Deferred<Response<List<MovieDetail>>>


    companion object {
        fun getMovieService():MoviesService= Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(MoviesService::class.java)
    }
}