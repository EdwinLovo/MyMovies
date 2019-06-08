package com.pdm.mymovies.Database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdm.mymovies.Database.entities.Movie


@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies:Movie)

    @Query("SELECT*FROM movies")
    fun getAllMovies():LiveData<List<Movie>>

    @Query("DELETE FROM movies")
    suspend fun nukeTable()

}