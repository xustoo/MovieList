package com.xusto.movielist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xusto.movielist.R
import com.xusto.movielist.model.Movie
import com.xusto.movielist.utils.downloadFromUrl
import com.xusto.movielist.utils.placeHolderProgressBar
import kotlinx.android.synthetic.main.movie_row.view.*


class MovieAdapter(val movieList : ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_row, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        //get TextView in API
        holder.view.movieName.text = movieList[position].name
        holder.view.movieStarted.text = movieList[position].startDate
        holder.view.movieNetwork.text = movieList[position].network
        holder.view.movieStatus.text = movieList[position].status

        //get ImageView from API with GLIDE
        holder.view.movieImage.downloadFromUrl(
            movieList[position].thumbnail!!,
            placeHolderProgressBar(holder.view.context)
        )


    }

    override fun getItemCount(): Int {
        return movieList.size
    }


    fun updateMovieList(list: List<Movie>) {
        movieList.clear()
        movieList.addAll(list)

    }
}