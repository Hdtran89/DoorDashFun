package com.example.doordashfun.ui.restaurant.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doordashfun.R
import com.example.doordashfun.data.remote.model.Restaurant
import com.example.doordashfun.factory.ViewModelFactory
import com.example.doordashfun.ui.base.BaseActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class RestaurantActivity : BaseActivity() {
    private lateinit var restaurantAdapter: RestaurantAdapter
    private lateinit var restaurantViewModel: RestaurantViewModel
    private lateinit var recyclerView: RecyclerView
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        AndroidInjection.inject(this)
        restaurantViewModel = ViewModelProviders.of(this, this.viewModelFactory).get(
            RestaurantViewModel::class.java
        )
        initRecyclerView()
        loadData()
    }

    private fun initRecyclerView() {
        val emptyArrayList = arrayListOf<Restaurant>()
        restaurantAdapter = RestaurantAdapter(emptyArrayList)
        val gridLayoutManager = GridLayoutManager(this, 1)
        recyclerView = findViewById(R.id.restaurantRecycleView)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.adapter = restaurantAdapter
        recyclerView.apply {
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
        }
        restaurantViewModel.getRestaurantLiveData().observe(this, Observer {
            restaurantAdapter.restaurantList.addAll(it)
        })
    }

    private fun loadData() {
        restaurantViewModel.fetchRestaurants()
    }

    override fun onDestroy() {
        restaurantViewModel.onClear()
        super.onDestroy()
    }
}
