package com.example.movieapp.api

import com.example.movieapp.model.Datas
import com.example.movieapp.model.Result
import com.example.movieapp.model.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {

@GET ("top_rated")
fun getMovies (
    @Query("api_key") apiKey :String
):Call<Results>


    @GET ("{movie_id}")
    fun getMoviesData(
        @Path("movie_id") movieId :Int,
        @Query("api_key") apiKey: String
    ):Call<Datas>
}