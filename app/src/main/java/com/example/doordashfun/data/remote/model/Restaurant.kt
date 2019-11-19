package com.example.doordashfun.data.remote.model

data class Restaurant(val id: String,
                      val name: String,
                      val description: String,
                      val cover_img_url: String,
                      val status: String,
                      val delivery_fee: Int)