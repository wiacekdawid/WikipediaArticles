package com.wiacek.wikipediaarticles.api.objects

import com.google.gson.annotations.SerializedName

/**
 * Created by wiacek.dawid@gmail.com
 */
data class ArticleListResponseDto(
        @SerializedName("query")
        var geoSearchDto: GeoSearchDto)