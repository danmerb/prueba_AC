package com.example.animalcare.fragments

import retrofit2.converter.gson.GsonConverterFactory
import android.content.Context
import android.content.res.Configuration
import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalcare.R
import com.example.animalcare.adapter.LeyAdapter
import com.example.animalcare.database.entities.ley_entity
import com.example.animalcare.service.retrofit.ApiService
import com.example.animalcare.viewModels.ViewModelRoom
import kotlinx.android.synthetic.main.fragment_laws.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.RuntimeException


private lateinit var leyesViewModel: ViewModelRoom
private lateinit var leyesAdapter: LeyAdapter



class fragment_leyes : Fragment() {
    private lateinit var leyesViewModel: ViewModelRoom
    lateinit var leyesAdapter: LeyAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_laws, container, false)
        leyesViewModel = ViewModelProviders.of(this).get(ViewModelRoom::class.java)



        //initRecyclerView(view,)
        leyesViewModel.allLaws.observe(this, Observer {
            leyesAdapter.updateList(it)
        })

        //leyesViewModel.getAllLeyes().observe

        return view
    }


    fun initRecyclerView(container: View, ley: List<ley_entity>) {
        val linearLayoutManager = LinearLayoutManager(this.context)
        val recyclerview = container.rv_leyes
        recyclerview.setHasFixedSize(true)
        leyesViewModel = ViewModelProviders.of(this).get(ViewModelRoom::class.java)
        leyesAdapter = LeyAdapter(ley)
        recyclerview.apply {

            adapter=this@fragment_leyes.leyesAdapter
            layoutManager = LinearLayoutManager(context)


        }


    }
}
