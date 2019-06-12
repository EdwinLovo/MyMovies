package com.pdm.mymovies.Fragments


import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        var adapter = object : MovieAdapter(view.context){
            override fun setClickListenerToMovie(holder: MovieViewHolder, movieName: String) {
                holder.movieLinearLayout.setOnClickListener {
                    if (isConnected(view.context)){
                        movieViewModel.retrieveDetailMovies(movieName)
                        if(orientation()){
                            val nextAction = HomeFragmentDirections.nextAction()
                            Navigation.findNavController(it).navigate(nextAction)
                        }
                    } else {
                        Toast.makeText(view.context,"No internet connection", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        val recyclerView = view.recyclerviewMovies
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        movieViewModel.allMovies.observe(this, Observer { movies ->
            movies?.let { adapter.setMovies(it) }
        })
        /*search.setOnClickListener {
            movieViewModel.retrieveMovies(title.text.toString())
        }*/

        view.search.setOnClickListener {
            if (isConnected(it.context)){
                movieViewModel.retrieveMovies(view.title.text.toString())
            } else {
                Toast.makeText(it.context,"No internet connection", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun orientation():Boolean=resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    fun isConnected(context: Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork:NetworkInfo? = connectivityManager.activeNetworkInfo
        val connected:Boolean = activeNetwork?.isConnected == true
        return connected
    }

}
