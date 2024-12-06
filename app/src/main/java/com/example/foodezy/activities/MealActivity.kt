package com.example.foodezy.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foodezy.R
import com.example.foodezy.databinding.ActivityMealBinding
import com.example.foodezy.fragments.HomeFragment
import com.example.foodezy.models.Meal
import com.example.foodezy.viewmodels.MealViewModel

class MealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealBinding
    private lateinit var mealId:String
    private lateinit var mealName:String
    private lateinit var mealThumb:String
    private lateinit var mealMvvm:MealViewModel
    private lateinit var youtubeLink:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealMvvm= ViewModelProvider(this)[MealViewModel::class.java]

        getMealInformationFromIntent()

        setInformationInViews()
        loadingCase()
        mealMvvm.getmealDetail(mealId)
        observerMealDetailsLiveData()

        onYoutubeImageClick()
    }

    private fun onYoutubeImageClick() {
        binding.imgVideo.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }

    private fun observerMealDetailsLiveData() {
        mealMvvm.observerMealDetailsLiveData().observe(this,object : Observer<Meal>{
            override fun onChanged(t: Meal) {
                onResponse()
                val meal = t
                binding.tvCategory.text = "Category : ${meal!!.strCategory}"
                binding.tvArea.text = "Area : ${meal.strArea}"
                binding.tvInstructionsSteps.text=meal.strInstructions
                youtubeLink= meal.strYoutube.toString()
            }
        })
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)

        binding.collapsingToolbar.title=mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent(){
        val intent = intent
        mealId=intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName=intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb=intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun loadingCase(){
        binding.progressBar.visibility=View.VISIBLE
        binding.btnAddToFav.visibility= View.INVISIBLE
        binding.tvInstructions.visibility=View.INVISIBLE
        binding.tvCategory.visibility=View.INVISIBLE
        binding.tvArea.visibility=View.INVISIBLE
        binding.imgVideo.visibility=View.INVISIBLE
    }

    private fun onResponse(){
        binding.progressBar.visibility=View.INVISIBLE
        binding.btnAddToFav.visibility= View.VISIBLE
        binding.tvInstructions.visibility=View.VISIBLE
        binding.tvCategory.visibility=View.VISIBLE
        binding.tvArea.visibility=View.VISIBLE
        binding.imgVideo.visibility=View.VISIBLE
    }
}