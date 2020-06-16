package com.example.movieapp.api

import com.example.movieapp.model.Result
import com.example.movieapp.model.Results
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {
    private val movieInterface:MovieInterface  //singleton

    companion object {
        const val BASE_URL ="https://api.themoviedb.org/3/movie/"
    }
    init {
        val retrofit =Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        movieInterface=retrofit.create(MovieInterface::class.java)
    }

       fun getMovies(apiKey :String):Call<Results>{
           return movieInterface.getMovies(apiKey)
       }


}