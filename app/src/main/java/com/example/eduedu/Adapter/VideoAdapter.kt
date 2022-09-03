package com.example.eduedu.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eduedu.R
import com.example.eduedu.model.ModelVideo

class VideoAdapter(var data: ArrayList<ModelVideo>, var context: Activity?): RecyclerView.Adapter<VideoAdapter.MyViewHolder>() {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var title = view.findViewById<TextView>(R.id.tv_title_video)
        var price = view.findViewById<TextView>(R.id.tv_price_video)
        var imageVideo = view.findViewById<ImageView>(R.id.iv_thumbnail_video)
        var imageRating = view.findViewById<ImageView>(R.id.iv_rating_video)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = data[position].title
        holder.price.text = data[position].price.toString()
        holder.imageVideo.setImageResource(data[position].imageVideo)
        holder.imageRating.setImageResource(data[position].imageRating)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}