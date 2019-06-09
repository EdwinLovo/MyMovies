package com.pdm.mymovies.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pdm.mymovies.Database.daos.MovieDAO
import com.pdm.mymovies.Database.entities.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)

public abstract class RoomDB : RoomDatabase() {

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
                        .build()
                    return INSTANCE!!
                }
            }
        }

    }

}