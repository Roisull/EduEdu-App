package com.example.eduedu.dataView.dataFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eduedu.Adapter.KelasAdapter
import com.example.eduedu.DatabaseHelper
import com.example.eduedu.R

class DataKelasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_data_kelas, container, false)

//        // instance Data Kelas
//        val rvKelas: RecyclerView = view.findViewById(R.id.rv_data_kelas)
//        val databaseHelper = DatabaseHelper(this.requireContext())
//        // cal function show data kelas
//        val listData = databaseHelper.showKelas()
//        // set layout recycler view
//        rvKelas.layoutManager = LinearLayoutManager(activity)
//        // set adapter recycler view
//        rvKelas.adapter = KelasAdapter(listData)

        return view
    }

}