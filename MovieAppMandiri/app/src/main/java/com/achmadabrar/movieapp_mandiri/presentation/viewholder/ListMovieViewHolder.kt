package com.achmadabrar.movieapp_mandiri.presentation.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.movieapp_mandiri.data.model.Result
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_movie_item.view.*

class ListMovieFragmentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(result: Result) {
        with(itemView) {
            Glide.with(this)
                .load(result.posterPath)
                .into(imageViewMovie)

            textViewTitle.text = result.title
            textViewDesc.text = result.overview
            vote.text = result.vote.toString()
            textViewYear.text = result.releaseDate.toString()
        }
    }
}