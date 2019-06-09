package com.pdm.mymovies.Database.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.pdm.mymovies.Database.daos.MovieDAO
import com.pdm.mymovies.Database.entities.Movie

class MovieRepository(private val movieDao:MovieDAO) {

    //get movie
    fun getAllMovies(): LiveData<List<Movie>> = movieDao.getAllMovies()

    //insert movie
    @WorkerThread
    suspend fun insert(movies:Movie) = movieDao.insert(movies)
}