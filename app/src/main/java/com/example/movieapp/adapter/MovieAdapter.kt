package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.Result
import com.example.movieapp.model.Results
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter (var movieList:List<Result> = ArrayList()):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>()
{

    private var clickListener:ClickListener? =null
    fun setOnClickListener(clickListener:ClickListener){
        this.clickListener = clickListener
    }
    inner class MovieViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        private lateinit var nowPlaying:Result
      fun bindMovie(result: Result){
          this.nowPlaying = result
         itemView.txt_movie.text = result.title
          Picasso.get()
              .load("https://image.tmdb.org/t/p/w200${result.poster_path}")
              .into(itemView.imageView)
      }
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickListener?.onClick(nowPlaying)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
      val view =LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movieList.get(position))
    }
    fun updateMovie(result:List<Result>){
        this.movieList=result
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(result: Result)
    }
}



