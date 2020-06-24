package com.example.movieapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.model.Datas
import com.example.movieapp.viewmodel.MovieDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment : Fragment() {
   private lateinit var modelDetailViewModel : MovieDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeDetailViewModel()

       val callback : OnBackPressedCallback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
            findNavController().popBackStack()
           }

       }
      requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun observeDetailViewModel(){
         modelDetailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        modelDetailViewModel.getMovieDetail().observe(viewLifecycleOwner, Observer {
           bindMovie(it)
        } )
    }

   //
    fun bindMovie(datas: Datas){
       Picasso.get()
           .load("https://image.tmdb.org/t/p/w500/"+datas.poster_path)
           .into(imageViewOne)

        textTor.text =textTor.text.toString()+datas.title
        textRating.text =datas.vote_count.toString()
        textDate.text= datas.release_date
        textDescription.text=datas.overview

    }

    override fun onResume() {
        super.onResume()
        var movieArgs =arguments.let { MovieDetailFragmentArgs.fromBundle(it!!) }
        var movieId =movieArgs.movieId
        modelDetailViewModel.loadMovieDetail(movieId,"941425239d2f820600df2a8c7b206a31")
    }


}