package com.ilmint.sharedui.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteResult (
    val page: Int,
    val results: List<RemoteMovie>,
    @SerialName("total_pages") var totalPages: Int,
    @SerialName("total_results") var totalResults: Int,
)

@Serializable
data class RemoteMovie (
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("release_date") var releaseDate: String,
    @SerialName("poster_path") var posterPath: String,
    @SerialName("backdrop_path") var backdropPath: String,
    @SerialName("original_title") var originalTitle: String,
    @SerialName("original_language") var originalLanguage: String,
    val popularity: Double,
    @SerialName("vote_average") val voteAverage: Double,
)
