package com.meetSuccess.FoodResturant.Network

import com.meetSuccess.FoodResturant.Model.Categories
import com.meetSuccess.FoodResturant.Model.Meals
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("categories.php")
   suspend fun getCategories():Categories

    @GET("categories.php")
    suspend fun getMeal(): Meals
}