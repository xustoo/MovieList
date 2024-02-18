package com.xusto.movielist.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xusto.movielist.model.Movie
import com.xusto.movielist.model.MovieDetails

@Database(entities = arrayOf(Movie::class,MovieDetails::class), version = 1)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

    companion object{
        @Volatile private var instance :MovieDataBase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance?: synchronized(lock){
            instance?: makeDataBase(context).also{
                instance = it
            }
        }


    }

}
    private fun makeDataBase(context : Context) = Room.databaseBuilder(
        context.applicationContext,MovieDataBase::class.java,"movieDataBase").build()