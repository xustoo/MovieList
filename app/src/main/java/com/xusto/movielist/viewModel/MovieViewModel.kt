package com.xusto.movielist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.xusto.movielist.dataBase.MovieDataBase
import com.xusto.movielist.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieViewModel(app : Application) : BaseViewModel(app){
    val movieLiveData = MutableLiveData<Movie>()

    fun getDataFromRoom(uuid: Int){
        launch {
            val dao = MovieDataBase(getApplication()).movieDao()
            val movie =dao.getMovie(uuid)
            movieLiveData.value = movie
        }
    }

}