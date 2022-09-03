package com.example.eduedu.Adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eduedu.R
import com.example.eduedu.R.id
import com.example.eduedu.model.KelasModel

class KelasAdapter(private val list: ArrayList<KelasModel>):
    RecyclerView.Adapter<KelasAdapter.KelasViewHolder>(){
    inner class KelasViewHolder(v: View):RecyclerView.ViewHolder(v) {
        val tvTitleKelas: TextView
        val tvPriceKelas: TextView
        val ivThumbnailVideo: ImageView

        init {
            tvTitleKelas = v.findViewById(id.tv_title_video)
            tvPriceKelas = v.findViewById(id.tv_price_video)
            ivThumbnailVideo = v.findViewById(id.iv_thumbnail_video)
        }

        fun bind(data: KelasModel){
            val title: String = data.title
            val price: Int = data.price
            val image: Bitmap = data.image

            tvTitleKelas.text = title
            tvPriceKelas.text = price.toString()
            ivThumbnailVideo.setImageBitmap(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            KelasAdapter.KelasViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.item_video,
            parent, false)

        return KelasViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: KelasViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}