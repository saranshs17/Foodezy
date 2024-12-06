package com.example.foodezy.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.foodezy.models.Meal

@Dao
interface MealDao {

    // for both update and insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(meal:Meal)

    @Delete
    suspend fun delete(meal:Meal)

    @Query("SELECT * FROM mealInformation")
    fun getAllMeals():LiveData<List<Meal>>
}