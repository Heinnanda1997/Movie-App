package com.example.movieapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

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
     observeViewModel()

    }
    private fun observeViewModel(){
     movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
     movieViewModel.getResult().observe(viewLifecycleOwner, Observer {
         movieAdapter.updateMovie(it)
     })
    }

    override fun onResume() {
        super.onResume()
        movieViewModel.loadResult()
    }

}