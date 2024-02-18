package com.xusto.movielist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MovieDetails(
    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url : String? = null,

    @ColumnInfo(name = "description")
    @SerializedName("decription")
    var description : String? = null,

    @ColumnInfo(name = "rating")
    @SerializedName("rating")
    var rating : String? = null,

    @ColumnInfo(name = "genres")
    @SerializedName("genres")
    var genres : String? = null,

    @ColumnInfo(name = "runtime")
    @SerializedName("runtime")
    var runtime : String? = null,

    @ColumnInfo(name = "pictures")
    @SerializedName("pictures")
    var pictures : String? = null

){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
