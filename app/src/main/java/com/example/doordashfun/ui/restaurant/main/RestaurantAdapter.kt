package com.example.doordashfun.ui.restaurant.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.doordashfun.R
import com.example.doordashfun.data.remote.model.Restaurant
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlin.collections.ArrayList

class RestaurantAdapter(val restaurantList: ArrayList<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantAdapterViewHolder>() {
    private val onClickSubject = PublishSubject.create<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantAdapterViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
        return RestaurantAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RestaurantAdapterViewHolder, position: Int) {
        val item = restaurantList[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            onClickSubject.onNext(item.id)
        }
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    fun getPositionClicks(): Observable<String> {
        return onClickSubject
    }

    inner class RestaurantAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var restaurantImage = itemView.findViewById<ImageView>(R.id.restaurantImage)
        private var restaurantName = itemView.findViewById<TextView>(R.id.restaurantNameText)
        private var restaurantDescription =
            itemView.findViewById<TextView>(R.id.restaurantDescriptionText)
        private var restaurantStatus = itemView.findViewById<TextView>(R.id.restaurantStatusText)

        fun bind(restaurant: Restaurant) {
            Glide.with(itemView)
                .load(restaurant.cover_img_url)
                .into(restaurantImage)
            restaurantName.text = restaurant.name
            restaurantDescription.text = restaurant.description
            restaurantStatus.text = restaurant.status
        }
    }
}
