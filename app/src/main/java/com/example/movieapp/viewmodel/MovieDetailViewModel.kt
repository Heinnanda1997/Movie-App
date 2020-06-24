package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.api.MovieApi
import com.example.movieapp.model.Datas
import com.example.movieapp.model.Genre
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MovieDetailViewModel :ViewModel(){
var movieDetail :MutableLiveData<Datas> = MutableLiveData()

    fun getMovieDetail():LiveData<Datas> = movieDetail

    var movieDetailApi=MovieApi()
    fun loadMovieDetail(movieId:Int, apiKey:String){
     var movieApiCall = movieDetailApi.getMoviesData(apiKey,movieId)
        movieApiCall.enqueue(object : retrofit2.Callback<Datas>{
            override fun onFailure(call: Call<Datas>, t: Throwable) {
                Log.d("error","Fail")

            }

            override fun onResponse(call: Call<Datas>, response: Response<Datas>) {
                Log.d("success","Successful")
               val b = response.body()!!
                movieDetail.value = b
            }

        })




    }
}