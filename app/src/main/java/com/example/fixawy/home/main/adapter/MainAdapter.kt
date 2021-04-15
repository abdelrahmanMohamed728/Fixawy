package com.example.fixawy.home.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.base.OnClickItem
import com.example.fixawy.R
import com.example.fixawy.model.Fixer
import kotlinx.android.synthetic.main.fixer_list_layout.view.*

class MainAdapter(var context: Context, var fixers: List<Fixer>, var onClickItem: OnClickItem) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.fixer_list_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return fixers.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        var fixer = fixers[position]
        holder.nameTextView.text = fixer.name
        holder.job_text_view.text = fixer.job?.name
        holder.mobile_text_view.text = fixer.mobile
        holder.rating_bar.rating = fixer.rating!!
        holder.reserve_button.setOnClickListener {
            onClickItem.onItemClicked(position)
        }
    }


    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameTextView: TextView = view.name_text_view
        var job_text_view: TextView = view.job_text_view
        var mobile_text_view: TextView = view.phone_text_view
        var rating_bar: RatingBar = view.stars_rating_bar
        var reserve_button : Button = view.reserve_button
    }
}