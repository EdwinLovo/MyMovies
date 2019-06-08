package com.pdm.mymovies.Database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity(tableName = "movies")  // NOMBRE DE LA TABLA DE LA BASE
data class Movie (

    @PrimaryKey
    @field:Json(name = "id")//TODO PARA QUE ME MAPEE SEGUN LOS VALORES DE LA API
    val id: Long,

    @field:Json(name = "Title")
    val title: String,

    @field:Json(name = "Year")
    @ColumnInfo(name="Year")
    val year:String,

    @field:Json(name = "Released")
    @ColumnInfo(name="Released")
    val released:String,

    @field:Json(name = "Runtime")
    @ColumnInfo(name="Runtime")
    val runtime:String,

    @field:Json(name = "Genre")
    @ColumnInfo(name="Genre")
    val genre:String,

    @field:Json(name = "Director")
    @ColumnInfo(name="Director")
    val director:String,

    @field:Json(name = "Actors")
    @ColumnInfo(name="Actors")
    val actors:String,

    @field:Json(name = "Plot")
    @ColumnInfo(name="Plot")
    val plot:String,

    @field:Json(name = "Language")
    @ColumnInfo(name="Language")
    val language:String,

    @field:Json(name = "Rating")
    @ColumnInfo(name="Rating")
    val rating:String,

    @field:Json(name = "Poster")
    @ColumnInfo(name="Poster")
    val poster:String
)
