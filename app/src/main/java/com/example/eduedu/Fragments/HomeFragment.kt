package com.example.eduedu.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.example.eduedu.Adapter.KelasAdapter
import com.example.eduedu.Adapter.VideoAdapter
import com.example.eduedu.CategoryAdapter
import com.example.eduedu.DatabaseHelper
import com.example.eduedu.R
import com.example.eduedu.databinding.FragmentHomeBinding
import com.example.eduedu.model.ModelCategory
import com.example.eduedu.model.ModelVideo

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    lateinit var rvCategory: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // for category
        var lmC = LinearLayoutManager(activity)
        lmC.orientation = LinearLayoutManager.HORIZONTAL

        rvCategory = view.findViewById<RecyclerView>(R.id.rv_category)

        val adapterCategory = CategoryAdapter(ArrayCategory, activity)
        rvCategory.setHasFixedSize(true)
        rvCategory.layoutManager = lmC
        rvCategory.adapter = adapterCategory

        // instance Recycler View Kelas
        val rvKelas: RecyclerView = view.findViewById(R.id.rv_kelas)
        val databaseHelper = DatabaseHelper(this.requireContext())
        // call function show data kelas
        val listData = databaseHelper.showKelas()

        var lmK = LinearLayoutManager(activity)
        lmK.orientation = LinearLayoutManager.HORIZONTAL

        // set layout recycler view
        rvKelas.layoutManager = lmK

        // set layout recycler view
//        rvKelas.layoutManager = LinearLayoutManager(activity)
        // set adapter recycler view
        rvKelas.adapter = KelasAdapter(listData)

        return view
    }
    val ArrayCategory: ArrayList<ModelCategory>get(){
        var arraycategory = ArrayList<ModelCategory>()

        // 1
        val itemCategory1 = ModelCategory()
        itemCategory1.category = "Semua Kelas"

        // 2
        val itemCategory2 = ModelCategory()
        itemCategory2.category = "Digital Marketing"

        // 3
        val itemCategory3 = ModelCategory()
        itemCategory3.category = "Pemrogramman"

        // 4
        val itemCategory4 = ModelCategory()
        itemCategory4.category = "Android"

        // 5
        val itemCategory5 = ModelCategory()
        itemCategory5.category = "Flutter"

        // 6
        val itemCategory6 = ModelCategory()
        itemCategory6.category = "Fullstack"

        // 7
        val itemCategory7 = ModelCategory()
        itemCategory7.category = "Data Science"

        // 8
        val itemCategory8 = ModelCategory()
        itemCategory8.category = "Kotlin"

        arraycategory.add(itemCategory1)
        arraycategory.add(itemCategory2)
        arraycategory.add(itemCategory3)
        arraycategory.add(itemCategory4)
        arraycategory.add(itemCategory5)
        arraycategory.add(itemCategory6)
        arraycategory.add(itemCategory7)
        arraycategory.add(itemCategory8)

        return arraycategory
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}