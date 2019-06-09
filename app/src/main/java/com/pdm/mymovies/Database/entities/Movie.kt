package com.pdm.mymovies.Database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

//Esta tabla almacenara las coincidencias por busqueda de titulo de pelicula
@Entity(tableName = "movie")
data class Movie (
    @PrimaryKey
    @field:Json(name = "imdbID")
    val id: String,

    @field:Json(name = "Title")
    val title: String,

    @field:Json(name = "Year")
    @ColumnInfo(name="Year")
    val year:String,

    @field:Json(name = "Poster")
    @ColumnInfo(name="Poster")
    val poster:String,

    @field:Json(name = "Type")
    @ColumnInfo(name="Type")
    val type:String
)