package com.xusto.movielist.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xusto.movielist.model.Movie

@Dao
interface MovieDao {

    @Insert
    //suspend
    suspend fun insertAll(vararg movie : Movie) : List<Long>


    @Query("SELECT * FROM movie")
    suspend fun getAll() : List<Movie>

    @Query("SELECT * FROM movie WHERE id = :movieID")
    suspend fun getMovie(movieID : Int) : Movie

    @Query("DELETE FROM movie")
    suspend fun deleteAll()
}