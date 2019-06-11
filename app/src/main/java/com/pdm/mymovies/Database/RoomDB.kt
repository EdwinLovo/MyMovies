package com.pdm.mymovies.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pdm.mymovies.Database.daos.MovieDAO
import com.pdm.mymovies.Database.daos.MovieDetailDAO
import com.pdm.mymovies.Database.entities.Movie
import com.pdm.mymovies.Database.entities.MovieDetail

@Database(entities = [MovieDetail::class, Movie::class], version = 3, exportSchema = false)

public abstract class RoomDB : RoomDatabase() {

    abstract fun movieDetailsDao():MovieDetailDAO
    abstract fun movieDao():MovieDAO

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(
            context: Context
        ): RoomDB {

            if (INSTANCE != null) {
                return INSTANCE!!
            } else {
                synchronized(this) {
                    INSTANCE = Room
                        .databaseBuilder(context, RoomDB::class.java, "Movie_Database")
                        .fallbackToDestructiveMigration()
                        .build()
                    return INSTANCE!!
                }
            }
        }

    }

}