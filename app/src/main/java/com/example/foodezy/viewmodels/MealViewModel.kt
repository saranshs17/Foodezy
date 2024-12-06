package com.example.foodezy.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodezy.db.MealDatabase
import com.example.foodezy.models.Meal
import com.example.foodezy.models.MealList
import com.example.foodezy.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel(
    val mealDatabase: MealDatabase
):ViewModel() {
    private var mealDetailLiveData=MutableLiveData<Meal>()

    fun getmealDetail(id : String){
        RetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.body()!=null){
                    mealDetailLiveData.value=response.body()!!.meals[0]
                }else  return
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("MealActivity",t.message.toString())
            }
        })
    }

    fun observerMealDetailsLiveData():LiveData<Meal>{
        return mealDetailLiveData
    }

    fun insertMeal(meal: Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().upsert(meal)
        }
    }

    fun deleteMeal(meal:Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().delete(meal)
        }
    }
}