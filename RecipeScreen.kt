package com.example.internet

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(modifier: Modifier=Modifier,viewState:MainViewModel.RecipeState,
                 navigateToDetail: (Category) -> Unit){
//    MainViewModel is the name of the class that we've created
    val RecipeViewModel :MainViewModel= viewModel()
//    It delegates the categoriesState property of RecipeViewModel to viewstate. It means that any access to
//    the viewState property will actually be redirected to the categoriesState property of RecipeViewModel.
//    val viewState by RecipeViewModel.categoriesState
//    the above line has been passed as argument in fun RecipeScreen
    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewState.loading->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewState.error!=null->{
                Text(text = "Error Occured")
            }
            else->{
                CategoryScreen(categoriesNew = viewState.list,navigateToDetail)
            }
        }

    }

}
@Composable
fun CategoryScreen(categoriesNew: List<Category>,
                   navigateToDetail: (Category) -> Unit){
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()){
        items(categoriesNew){
            Category->  //here the Category is the element of items(categoriesNew) i.e, it
            CategoryItem(IndividualCategory = Category, navigateToDetail )
        }
    }
}
//how each item should look like
@Composable
fun CategoryItem(IndividualCategory: Category,
                 navigateToDetail: (Category) -> Unit){
    Column(modifier= Modifier
        .padding(8.dp)
        .fillMaxSize()
        .clickable { navigateToDetail(IndividualCategory) },
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(painter = rememberAsyncImagePainter(IndividualCategory.strCategoryThumb),
            contentDescription = null,
            modifier= Modifier
                .fillMaxSize()
                .aspectRatio(1f) //1f means it is as long as it is wide
            )


        Text(text =IndividualCategory.strCategory,
            color= Color.Black,
            style= TextStyle(fontWeight = FontWeight.Bold),
            modifier=Modifier.padding(top=4.dp)
            )
    }
}