package com.example.daytoday.searchreponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PicSumPhotosResponse {

    @Expose
    @SerializedName("id")
    val id: String? = null


    @Expose
    @SerializedName("author")
    val author: String? = null


    @Expose
    @SerializedName("width")
    val width: String? = null


    @Expose
    @SerializedName("height")
    val height: String? = null


    @Expose
    @SerializedName("url")
    val url: String? = null


    @Expose
    @SerializedName("download_url")
    val download_url: String? = null

}