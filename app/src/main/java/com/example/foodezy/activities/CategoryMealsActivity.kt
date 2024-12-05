package com.example.foodezy.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodezy.R
import com.example.foodezy.adapters.CategoryMealsAdapter
import com.example.foodezy.databinding.ActivityCategoryMealsBinding
import com.example.foodezy.fragments.HomeFragment
import com.example.foodezy.viewmodels.CategoryMealsViewModel

class CategoryMealsActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoryMealsBinding
    lateinit var categoryMealsViewModel : CategoryMealsViewModel
    lateinit var categoryMealsAdapter: CategoryMealsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
        categoryMealsViewModel = ViewModelProvider(this)[CategoryMealsViewModel::class.java]
        categoryMealsViewModel.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)
        categoryMealsViewModel.observeMealsLiveData().observe(this) { mealList ->
            binding.tvCategoryCount.text=mealList.size.toString()
            categoryMealsAdapter.setMealsList(mealList)
        }
    }

    private fun prepareRecyclerView() {
        categoryMealsAdapter = CategoryMealsAdapter()
        binding.mealRecyclerview.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter=categoryMealsAdapter
        }
    }
}