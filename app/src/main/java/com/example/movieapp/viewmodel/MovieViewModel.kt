package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.api.MovieApi
import com.example.movieapp.model.Result
import com.example.movieapp.model.Results
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MovieViewModel :ViewModel() {
var result :MutableLiveData<List<Result>> = MutableLiveData()

    //getter
    fun getResult(): LiveData<List<Result>> = result

    private val movieApi :MovieApi =MovieApi()

    //Setter
    fun loadResult() {
        val apiCall = movieApi.getMovies("941425239d2f820600df2a8c7b206a31")
        apiCall.enqueue(object : retrofit2.Callback<Results>{
            override fun onFailure(call: Call<Results>, t: Throwable) {

            }

            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                val a =response.body()?.results
                response.isSuccessful.let {
                    val resultList = response.body()?.results ?: emptyList()
                    result.value =resultList

                }
            }

        })

    }
}