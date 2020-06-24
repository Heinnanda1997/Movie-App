package com.example.movieapp.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.model.Result
import com.example.movieapp.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment(), MovieAdapter.ClickListener {

    private lateinit var movieAdapter:MovieAdapter
    private lateinit var viewManager:RecyclerView.LayoutManager
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager =GridLayoutManager(context,2)
       movieAdapter=MovieAdapter()
        recyclermovie.apply {
            adapter =movieAdapter
           layoutManager =viewManager
        }
        movieAdapter.setOnClickListener(this)
        observeViewModel()

    }
    private fun observeViewModel(){
     movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
     movieViewModel.getResult().observe(viewLifecycleOwner, Observer {
         movieAdapter.updateMovie(it.results)
     })
    }

    override fun onResume() {
        super.onResume()
        movieViewModel.loadResult()
    }

    override fun onClick(result: Result) {
        Log.d("Id", id.toString())
        var id = result.id
        var act = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(id)
        findNavController().navigate(act)
    }
    }






    //override fun onClick(id: Int) {
        //if (!TextUtils.isEmpty(nowPlayingList.id.toString())) {
         //   var movieID = nowPlayingList.id
          //  var movieTitle = nowPlayingList.title
           // var action = NowPlayingFragmentDirections.actionNavGalleryToMovieDetailFragment(
             //   movieID,
            //    movieTitle
           // )
           // findNavController().navigate(action)
        //}
    //}



