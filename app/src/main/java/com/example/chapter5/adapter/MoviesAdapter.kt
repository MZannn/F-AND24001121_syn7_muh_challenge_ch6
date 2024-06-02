package com.example.chapter5.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter5.R
import com.example.chapter5.model.response.MovieResponse
import com.example.chapter5.model.response.Result

class MoviesAdapter(
    private var list: List<Result>,
    private val onClick: (Result) -> Unit
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById<CardView>(R.id.movieItem)
        val title: TextView = itemView.findViewById<TextView>(R.id.tvTitle)
        val content: TextView = itemView.findViewById<TextView>(R.id.tvContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.title.text = item.title
        holder.content.text = item.overview
        holder.cardView.setOnClickListener {
            onClick(item)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


}