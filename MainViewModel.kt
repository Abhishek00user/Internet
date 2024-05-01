package com.example.internet

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {

    private val _categorieState=mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categorieState //what's happening here
//    _categorieState: It's a mutable state variable that holds the state of the recipe categories. It's
//    initialized with an initial state using mutableStateOf(RecipeState()).
//    categoriesState: It's a read-only property that exposes the _categorieState as a State object.
//    The State object ensures that Compose UI components recompose when the state changes.

    init {
        fetchCategories()
    }
    private fun fetchCategories(){
        viewModelScope.launch {
            try{
//                we are getting response from the instance of Api service
                val response= recipeService.getCategories()
                _categorieState.value=_categorieState.value.copy(
                    list=response.categories, //didn't understand
                    loading=false,
                    error = null
                )
            }
            catch (e:Exception){
                _categorieState.value=_categorieState.value.copy(
                    loading = false,
                    error="Error fetching categories ${e.message}"
                )
            }
        }
    }


    data class RecipeState(
        val loading: Boolean=true,
        val list:List<Category> = emptyList(),
        val error: String? = null
    )
}

