package com.example.foodezy.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodezy.models.Category
import com.example.foodezy.models.CategoryList
import com.example.foodezy.models.MealsByCategoryList
import com.example.foodezy.models.MealsByCategory
import com.example.foodezy.models.Meal
import com.example.foodezy.models.MealList
import com.example.foodezy.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(): ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<MealsByCategory>>()
    private var categoriesLiveData = MutableLiveData<List<Category>>()
    fun getRandomMeal(){
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.body()!= null){
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value=randomMeal
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t : Throwable) {
                Log.d("HomeTest",t.message.toString())
            }
        })
    }

    fun getPopularItems(){
        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : Callback<MealsByCategoryList>{
            override fun onResponse(call: Call<MealsByCategoryList>, response: Response<MealsByCategoryList>) {
                if(response.body()!=null){
                    popularItemsLiveData.value=response.body()!!.meals
                }else return
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.d("HomeFragment",t.message.toString())
            }

        })
    }

    fun getCategories(){
        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if(response.body()!=null){
                    categoriesLiveData.value = response.body()!!.categories
                }else return
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("HomeViewModel",t.message.toString())
            }

        })
    }

    fun observeRandomMealLiveData(): LiveData<Meal> {
        return randomMealLiveData
    }

    fun observePopularItemsLiveData():LiveData<List<MealsByCategory>>{
        return popularItemsLiveData
    }

    fun observeCategoriesLiveData():LiveData<List<Category>>{
        return categoriesLiveData
    }
}