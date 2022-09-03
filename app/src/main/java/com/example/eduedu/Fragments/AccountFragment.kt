package com.example.eduedu.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eduedu.Adapter.DataKelasAdapter
import com.example.eduedu.Adapter.KelasAdapter
import com.example.eduedu.DatabaseHelper
import com.example.eduedu.R
import com.example.eduedu.admin.AddKelasActivity
import com.example.eduedu.dataView.ViewDataActivity
import com.example.eduedu.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {

    private var binding: FragmentAccountBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        // instance button add kelas
        val btnAddKelas = view.findViewById<ImageView>(R.id.iv_goto_add_kelas_ac)
        // event btnAddKelas
        btnAddKelas.setOnClickListener{
            requireActivity().run(){
                val intent = Intent(this, AddKelasActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // instance recyclerview Data Kelas
        val rvDataKelas: RecyclerView = view.findViewById(R.id.rv_data_kelas)
        val databaseHelper = DatabaseHelper(this.requireContext())
        // cal function show data kelas
        val listData = databaseHelper.showKelas()

        var lmdk = LinearLayoutManager(activity)
        lmdk.orientation = LinearLayoutManager.VERTICAL

        // set layout recycler view
        rvDataKelas.layoutManager = lmdk

        // set adapter recycler view
        rvDataKelas.adapter = DataKelasAdapter(listData)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}