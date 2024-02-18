package com.xusto.movielist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class Movie(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name : String? = null,

    @ColumnInfo(name = "start_date")
    @SerializedName("start_date")
    var startDate : String? = null,

    @ColumnInfo(name = "country")
    @SerializedName("country")
    var country : String? = null,

    @ColumnInfo(name ="network")
    @SerializedName("network")
    var network : String? = null,

    @ColumnInfo(name = "status")
    @SerializedName("status")
    var status : String? = null,

    @ColumnInfo(name = "image_thumbnail_path")
    @SerializedName("image_thumbnail_path")
    var thumbnail : String? = null

) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}