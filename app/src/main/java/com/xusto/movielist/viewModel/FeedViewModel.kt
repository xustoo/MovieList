package com.xusto.movielist.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.xusto.movielist.dataBase.MovieDao
import com.xusto.movielist.dataBase.MovieDataBase
import com.xusto.movielist.model.Movie
import com.xusto.movielist.service.APIService
import com.xusto.movielist.utils.CustomSharedPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.util.UUID

class FeedViewModel(app : Application) : BaseViewModel(app) {
    private val movieAPIService = APIService()
    private val disposable = CompositeDisposable()
    private val customSharedPreference = CustomSharedPreference(getApplication())
    val movies = MutableLiveData<List<Movie>>()
    val movieLoading = MutableLiveData<Boolean>()
    val movieError = MutableLiveData<Boolean>()

    private var refreshTime = 10*60*1000*1000*1000L

    fun refreshData(){

        val updateTime = customSharedPreference.getTime()
        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime <refreshTime){
            getDataFromDataBase()
        }else{
            getDataFromAPI()
        }

    }
    private fun getDataFromAPI(){
        movieLoading.value = true
        disposable.add(
            movieAPIService.getDataFromMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Movie>>(){
                    override fun onSuccess(t: List<Movie>) {
                        storeInSQLite(t)
                    }

                    override fun onError(e: Throwable) {
                        movieLoading.value = false
                        movieError.value = true
                        e.printStackTrace()
                    }

                }
        ))
    }
    fun refreshFromAPI(){
        getDataFromAPI()
    }
    private fun storeInSQLite(list : List<Movie>){

        launch {
            val dao = MovieDataBase(getApplication()).movieDao()
            dao.deleteAll()
            val listLong = dao.insertAll(*list.toTypedArray()) //list to -> individual unit
            var i = 0
            while(i<list.size){
                list[i].id =listLong[i].toInt()
                i++
            }
            showMovies(list)
        }
        customSharedPreference.saveTime(System.nanoTime())
    }
    private fun getDataFromDataBase(){
        movieLoading.value = true
        launch {
            val movies = MovieDataBase(getApplication()).movieDao().getAll()
            showMovies(movies)
            Toast.makeText(getApplication(),"Movies from DataBase",Toast.LENGTH_LONG).show()
        }
    }
    private fun showMovies(movieList : List<Movie>){
        movies.value = movieList
        movieError.value = false
        movieLoading.value = false
    }



}