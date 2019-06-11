package com.pdm.mymovies.Services

import com.pdm.mymovies.Database.entities.Movie
import com.squareup.moshi.Json

data class Coincidences(

    @field:Json(name = "totalResults")
    var totalResults:String,

    @field:Json(name = "Search")
    var search:List<Movie>,

    @field:Json(name = "Response")
    var response:String
)