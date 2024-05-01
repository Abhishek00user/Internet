package com.example.internet
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit=Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
//creating an instance of an API service interface using Retrofit.
  val recipeService= retrofit.create(ApiService::class.java)

interface ApiService{
    //GET Define the endpoint path relative to the base URL
    @GET("categories.php")
//    this fun will return categoriesList which is in the category.kt file
    suspend fun getCategories():CategoriesList
}

