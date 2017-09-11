package com.wiacek.wikipediaarticles.api.objects

import com.google.gson.annotations.SerializedName

/**
 * Created by wiacek.dawid@gmail.com
 */

data class ArticleDto(
        @SerializedName("pageid")
        var pageid: String = "",
        @SerializedName("ns")
        var ns: String = "",
        @SerializedName("title")
        var title: String = "",
        @SerializedName("lat")
        var lat: String = "",
        @SerializedName("lon")
        var lon: String = "",
        @SerializedName("dist")
        var dist: String = "",
        @SerializedName("primary")
        var primary: String = "")