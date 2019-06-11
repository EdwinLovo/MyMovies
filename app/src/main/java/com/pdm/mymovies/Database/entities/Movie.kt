package com.pdm.mymovies.Database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

//Esta tabla almacenara las coincidencias por busqueda de titulo de pelicula
@Entity(tableName = "movie")
data class Movie (
    @PrimaryKey
    val imdbID: String,

    val Title: String,

    val Year:String,

    val Poster:String,

    val Type:String
)