package com.example.internet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navControllerNew: NavHostController){
    val recipeViewModel:MainViewModel = viewModel()
    val viewStateNew by recipeViewModel.categoriesState

    NavHost(navController=navControllerNew,startDestination=Screen.RecipeScreen.route){
        composable(route=Screen.RecipeScreen.route){
            RecipeScreen(viewState = viewStateNew, navigateToDetail = {
//this part is responsible for passing the data from current screen to detail screen.It uses the
// savedStateHandle,which is a component of compose navigation framework
                navControllerNew.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navControllerNew.navigate(Screen.DetailScreen.route)
            })
        }
        composable(route=Screen.DetailScreen.route){
            val receivedDetail=navControllerNew.previousBackStackEntry?.savedStateHandle?.
            get<Category>("cat")?: Category("","","","")
//            we are passing the Details to Detail screen
            CategoryDetailScreen(category = receivedDetail)
        }
    }

}