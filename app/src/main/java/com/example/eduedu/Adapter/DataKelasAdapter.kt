package com.example.eduedu.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.eduedu.DatabaseHelper
import com.example.eduedu.Fragments.AccountFragment
import com.example.eduedu.R
import com.example.eduedu.R.id
import com.example.eduedu.admin.EditKelasActivity
import com.example.eduedu.model.KelasModel

class DataKelasAdapter(private val list: ArrayList<KelasModel>):
RecyclerView.Adapter<DataKelasAdapter.DataKelasViewHolder>(){
    inner class DataKelasViewHolder(v: View): RecyclerView.ViewHolder(v){
        val tvTitleKelas: TextView
        val tvPriceKelas: TextView
        val ivEditDataKelas: ImageView
        val ivDeleteDataKelas: ImageView

        init {
            tvTitleKelas = v.findViewById(id.tv_title_data_kelas)
            tvPriceKelas = v.findViewById(id.tv_price_data_kelas)
            ivEditDataKelas = v.findViewById(id.iv_edit_data_kelas)
            ivDeleteDataKelas = v.findViewById(id.iv_delete_data_kelas)
        }

        fun bind(data: KelasModel){
            val id: Int = data.kelasId
            val title: String = data.title
            val price: Int = data.price

            tvTitleKelas.text = title
            tvPriceKelas.text = price.toString()

            ivEditDataKelas.setOnClickListener{
                val intent = Intent(ivEditDataKelas.context, EditKelasActivity::class.java)
                intent.putExtra("id", id)
                ivEditDataKelas.context.startActivity(intent)
            }

            ivDeleteDataKelas.setOnClickListener{
                val databaseHelper = DatabaseHelper(it.context)
                databaseHelper.deleteKelas(id)

                val fragment: Fragment = AccountFragment()

                val activity: AppCompatActivity = it.context as AppCompatActivity

                activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataKelasAdapter.DataKelasViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.item_data_kelas,
        parent,false)


        return DataKelasViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: DataKelasViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}