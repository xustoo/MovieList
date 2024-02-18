
package com.xusto.movielist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.xusto.movielist.R
import com.xusto.movielist.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.movie_row.*
import kotlinx.android.synthetic.main.movie_row.movieName
import kotlinx.android.synthetic.main.movie_row.movieStarted
import kotlinx.android.synthetic.main.movie_row.movieStatus


class MovieFragment : Fragment() {

    private lateinit var viewModel : MovieViewModel
    private var movieUuid = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            movieUuid = MovieFragmentArgs.fromBundle(it).movieUid
        }
        viewModel = ViewModelProviders.of(this)[MovieViewModel::class.java]
        viewModel.getDataFromRoom(movieUuid)
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer {movie ->
            movie?.let{
                movieName.text = movie.name
                movieNetwork.text = movie.network
                movieStarted.text = movie.startDate
                movieStatus.text = movie.status
                movieDescrition.text = movie.country


            }
        })
    }

}