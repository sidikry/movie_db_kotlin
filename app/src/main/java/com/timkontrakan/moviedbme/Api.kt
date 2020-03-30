package com.timkontrakan.moviedbme

import okhttp3.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "14953188405961ad80873021665c9f67",
        @Query("page") page: Int
    ): retrofit2.Call<GetMovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = "14953188405961ad80873021665c9f67",
        @Query("page") page: Int
    ): retrofit2.Call<GetMovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String = "14953188405961ad80873021665c9f67",
        @Query("page") page: Int
    ): retrofit2.Call<GetMovieResponse>
}