package com.pdm.mymovies.Database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdm.mymovies.Database.entities.MovieDetail


@Dao
interface MovieDetailDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieDetail:MovieDetail)

    @Query("SELECT*FROM moviedetail")
    fun getAllDetailedMovies():LiveData<List<MovieDetail>>

    @Query("DELETE FROM moviedetail")
    suspend fun nukeTable()

}