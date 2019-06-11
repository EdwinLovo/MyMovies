package com.pdm.mymovies.Models

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pdm.mymovies.Database.RoomDB
import com.pdm.mymovies.Database.entities.Movie
import com.pdm.mymovies.Database.entities.MovieDetail
import com.pdm.mymovies.Database.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel (private val app: Application): AndroidViewModel(app){
    private val repository : MovieRepository
    val allMovies : LiveData<List<Movie>>
    val allDetailedMovies : LiveData<List<MovieDetail>>


    init {
        val movieDao = RoomDB.getInstance(app).movieDao()
        val movieDetailDao = RoomDB.getInstance(app).movieDetailsDao()
        repository = MovieRepository(movieDao,movieDetailDao)
        allMovies = repository.AllMovies
        allDetailedMovies = repository.AllDetailedMovies
    }

    fun insertMovie (movie: Movie) = viewModelScope.launch(Dispatchers.IO){
        repository.insertMovie(movie)
    }
    fun insertDetailedMovie (movie: MovieDetail) = viewModelScope.launch(Dispatchers.IO){
        repository.insertDetailedMovie(movie)
    }
    fun nukemovies () = viewModelScope.launch(Dispatchers.IO){
        repository.nukeMovies()
    }
    fun nukedetailedmovies () = viewModelScope.launch(Dispatchers.IO){
        repository.nukeDetailedMovies()
    }

    fun retrieveMovies(movie:String) = viewModelScope.launch(Dispatchers.IO){
        this@MovieViewModel.nukemovies()
        val response=repository.retrieveMoviesAsync(movie).await()

        if (response.isSuccessful) with(response.body()?.search){
            this?.forEach {
                this@MovieViewModel.insertMovie(it)
                Log.d("CODIGO", it.Title+" ingresada correctamente")
            }
        } else with(response){
            Log.d("CODIGO", "Error: $response")
            when(this.code()){
                404->{
                    Toast.makeText(app, "Movie not found", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    fun retrieveDetailMovies(movie:String) = viewModelScope.launch(Dispatchers.IO){
        this@MovieViewModel.nukedetailedmovies()
        val response=repository.retrieveDetailMoviesAsync(movie).await()
        if(response.isSuccessful) with(response){
            this.body()?.forEach{
                this@MovieViewModel.insertDetailedMovie(it)
            }
        }else with(response){
            when(this.code()){
                404->{
                    android.widget.Toast.makeText(app,"Movie not found", android.widget.Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}