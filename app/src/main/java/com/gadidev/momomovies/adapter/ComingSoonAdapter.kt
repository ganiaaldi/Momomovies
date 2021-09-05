package com.gadidev.momomovies.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gadidev.momomovies.databinding.ItemComingsoonBinding
import com.gadidev.momomovies.model.Movies
import com.gadidev.momomovies.model.ResultMovies

class ComingSoonAdapter(private val listener: MovieItemListener) : RecyclerView.Adapter<MovieViewHolder>() {

    interface MovieItemListener {
        fun onClickedMovie(movieId: String)
    }

    private val items = ArrayList<ResultMovies>()

    fun setItems(items: ArrayList<ResultMovies>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: ItemComingsoonBinding = ItemComingsoonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(items[position])
}

class MovieViewHolder(private val itemBinding: ItemComingsoonBinding, private val listener: ComingSoonAdapter.MovieItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var movie: ResultMovies

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: ResultMovies) {
        this.movie = item
        itemBinding.tvNameMovies.text = item.title
        Glide.with(itemBinding.root)
            .load(item.thumbnail)
            .into(itemBinding.imageViewMovies)
    }

    override fun onClick(v: View?) {
        listener.onClickedMovie(movie.title)
    }
}