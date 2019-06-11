package com.pdm.mymovies.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pdm.mymovies.Database.entities.Movie
import com.pdm.mymovies.R
import kotlinx.android.synthetic.main.movie_info.view.*

abstract class MovieAdapter internal constructor(private val context: Context):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    private var inflater = LayoutInflater.from(context)
    private var movies = emptyList<Movie>()

    abstract fun setClickListenerToMovie(holder: MovieViewHolder, movieName:String)

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = inflater.inflate(R.layout.movie_info,parent,false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = movies[position]
        holder.title.text = currentMovie.Title
        holder.type.text = currentMovie.Type
        holder.year.text = currentMovie.Year

        Glide.with(context)
            .load(currentMovie.Poster)
            .centerCrop()
            .crossFade()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.poster)

        setClickListenerToMovie(holder,currentMovie.Title)
    }

    inner class MovieViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val title:TextView = itemView.movieTitle
        val year:TextView = itemView.movieYear
        val poster:ImageView = itemView.moviePoster
        val type:TextView = itemView.movieType
        val movieLinearLayout:LinearLayout = itemView.movieLinearLayout
    }

    internal fun setMovies(movies:List<Movie>){
        this.movies = movies
        notifyDataSetChanged()
    }

}