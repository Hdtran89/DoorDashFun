package com.example.doordashfun.ui.restaurant.detail

import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.doordashfun.R
import com.example.doordashfun.data.remote.model.Restaurant
import com.example.doordashfun.factory.ViewModelFactory
import com.example.doordashfun.ui.base.BaseActivity
import com.example.doordashfun.utils.AppConstants
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail_restaurant.*
import javax.inject.Inject


class RestaurantDetailActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var restaurantDetailViewModel: RestaurantDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_restaurant)
        AndroidInjection.inject(this)
        restaurantDetailViewModel = ViewModelProviders.of(this, this.viewModelFactory).get(
            RestaurantDetailViewModel::class.java
        )
        val restaurantId = intent.getStringExtra(AppConstants.RESTAURANT_ID)
        loadData(restaurantId)
        restaurantDetailViewModel.getRestaurantDetailLiveData().observe(this, Observer {
            initView(it)
        })
    }

    private fun loadData(restaurantId: String) {
        restaurantDetailViewModel.fetchDetailsRestaurant(restaurantId)
    }

    private fun initView(restaurant: Restaurant) {
        Glide.with(this)
            .load(restaurant.cover_img_url)
            .into(restaurantDetailCoverImg)
        val descriptionText = findViewById<TextView>(R.id.restaurantDetails)
        val text = "Name: " + restaurant.name + "\n" + "Description: " + restaurant.description + "\n" + "Status: " + restaurant.status + "\n" + "Delivery Fee: " + restaurant.delivery_fee
        descriptionText.text = text
    }
    override fun onDestroy() {
        restaurantDetailViewModel.onClear()
        super.onDestroy()
    }
}
