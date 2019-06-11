package com.pdm.mymovies.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pdm.mymovies.Models.MovieViewModel

import com.pdm.mymovies.R
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : Fragment() {

    lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        init(view)
        return view
    }

    fun init(view: View){
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        val movie = movieViewModel.allDetailedMovies
        movieViewModel.allDetailedMovies.observe(this, Observer { movie->
            movie?.let {
                movieTitle.text = it.title
                movieActors.text = it.actors
                moviePlot.text = it.plot
                movieGenre.text = it.genre
                movieReleased.text = it.released
                movieRuntime.text = it.runtime
                movieType.text = it.type
                movieYear.text = it.year
                Glide.with(view.context)
                        .load(it.poster)
                        .centerCrop()
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(moviePoster)
            }
        })
    }

}
