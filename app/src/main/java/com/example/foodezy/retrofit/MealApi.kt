package com.example.foodezy.retrofit

import com.example.foodezy.models.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>
}