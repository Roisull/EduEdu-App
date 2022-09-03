package com.example.eduedu

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eduedu.model.ModelCategory

class CategoryAdapter(var dataCat: ArrayList<ModelCategory>, var context: Activity?): RecyclerView.Adapter<CategoryAdapter.MyViewHolder>(){

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var category = view.findViewById<TextView>(R.id.tv_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.category.text = dataCat[position].category
    }

    override fun getItemCount(): Int {
        return dataCat.size
    }

}