package com.xusto.movielist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xusto.movielist.R
import com.xusto.movielist.adapter.MovieAdapter
import com.xusto.movielist.viewModel.FeedViewModel
import com.xusto.movielist.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.movie_row.*


class FeedFragment : Fragment() {

    private lateinit var viewModel : FeedViewModel
    private val movieAdapter = MovieAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this)[FeedViewModel::class.java]
        viewModel.refreshData()

        movieList.layoutManager = LinearLayoutManager(context)
        movieList.adapter = movieAdapter

        swipeRefreshLayout.setOnRefreshListener {
            movieList.visibility = View.GONE
            movieError.visibility  = View.GONE
            movieLoading.visibility = View.VISIBLE

            viewModel.refreshFromAPI()

            swipeRefreshLayout.isRefreshing = false
        }

        fun observeLiveData(){
            viewModel.movies.observe(viewLifecycleOwner, Observer {movie ->
                movie?.let{
                    movieLoading.visibility = View.VISIBLE
                    movieAdapter.updateMovieList(it)

                }
            })
            viewModel.movieError.observe(viewLifecycleOwner, Observer { error->
                error?.let{
                    if(it){
                        movieError.visibility = View.VISIBLE
                    }else{
                        movieError.visibility = View.GONE
                    }

                }

            })
            viewModel.movieLoading.observe(viewLifecycleOwner, Observer { loading->
                loading?.let{
                    if(it){
                        movieLoading.visibility = View.VISIBLE
                        movieError.visibility = View.GONE
                        movieList.visibility = View.GONE
                }else{
                    movieLoading.visibility = View.GONE
                    }

            }
                })
        }



    }


}