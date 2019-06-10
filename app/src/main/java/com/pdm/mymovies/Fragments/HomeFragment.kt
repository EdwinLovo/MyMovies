package com.pdm.mymovies.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdm.mymovies.Adapters.MovieAdapter
import com.pdm.mymovies.Models.MovieViewModel

import com.pdm.mymovies.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    lateinit var movieViewModel:MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        init(view)
        return  view
    }

    fun init(view: View){

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        var adapter = object : MovieAdapter(view.context){}
        val recyclerView = view.recyclerviewMovies
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        movieViewModel.allMovies.observe(this, Observer { movies ->
            movies?.let { adapter.setMovies(it) }
        })

        /*search.setOnClickListener {
            movieViewModel.retrieveMovies(title.text.toString())
        }*/

        view.search.setOnClickListener {
            movieViewModel.retrieveMovies(view.title.text.toString())
        }

    }

}
