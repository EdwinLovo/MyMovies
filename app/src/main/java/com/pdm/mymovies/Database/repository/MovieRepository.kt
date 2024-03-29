package com.pdm.mymovies.Database.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.pdm.mymovies.Database.daos.MovieDAO
import com.pdm.mymovies.Database.daos.MovieDetailDAO
import com.pdm.mymovies.Database.entities.Movie
import com.pdm.mymovies.Database.entities.MovieDetail
import com.pdm.mymovies.Services.Coincidences
import com.pdm.mymovies.Services.MoviesService
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MovieRepository(private val movieDao:MovieDAO,private val movieDetailDAO: MovieDetailDAO) {

    //get movie
    val AllMovies: LiveData<List<Movie>> = movieDao.getAllMovies()
    val AllDetailedMovies: LiveData<MovieDetail> = movieDetailDAO.getAllDetailedMovies()

    //insert movie
    @WorkerThread
    suspend fun insertMovie(movie:Movie) = movieDao.insert(movie)

    @WorkerThread
    suspend fun insertDetailedMovie(movieDetail:MovieDetail) = movieDetailDAO.insert(movieDetail)

    @WorkerThread
    suspend fun nukeMovies() = movieDao.nukeTable()

    @WorkerThread
    suspend fun nukeDetailedMovies() = movieDetailDAO.nukeTable()

    fun retrieveMoviesAsync(movie:String): Deferred<Response<Coincidences>> =
        MoviesService.getMovieService().getMovies(movie,"55b04979")

    fun retrieveDetailMoviesAsync(movie:String): Deferred<Response<MovieDetail>> =
        MoviesService.getMovieService().getDetailedMovie(movie, "55b04979")
}