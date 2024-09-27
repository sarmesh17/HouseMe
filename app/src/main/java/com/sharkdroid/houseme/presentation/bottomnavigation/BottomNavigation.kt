package com.sharkdroid.houseme.presentation.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sharkdroid.houseme.R

@Composable
fun BottomNavigation(onClick:(index:Int) -> Unit, selectedItem: Int){
    val horizontalGradient = Brush.linearGradient(
        colors = listOf(colorResource(id = R.color.Vivid_Sky_Blue), colorResource(id = R.color.Sea_Green))
    )

    val items = listOf(
        NavigationItem("Home", R.drawable.home_icon, R.drawable.home_icon),
        NavigationItem("Places", R.drawable.place_icon, R.drawable.place_icon),
        NavigationItem("AddRoom", R.drawable.outline_add_room24, R.drawable.outline_add_room24),
        NavigationItem("Booking", R.drawable.history_icon, R.drawable.history_icon),
        NavigationItem("MyProfile", R.drawable.profile_icon, R.drawable.profile_icon)
    )

    NavigationBar (modifier = Modifier
        .background(brush = horizontalGradient)
        .padding(top = 8.dp)
        .height(80.dp), containerColor = Color.Transparent
        )
    {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { onClick(index) },
                label = {
                    if (index == selectedItem){
                        Text(text = item.name, color = Color.White)
                    }else{
                        Text(text = item.name)
                    }
                    //Text(text = item.name)
                }, colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                icon = {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Icon(
                            painter =
                            if (index == selectedItem){
                                painterResource(id = item.selectedIcon)
                            }else painterResource(id = item.unselectedIcon),
                            contentDescription = item.name,
                            tint = if (index== selectedItem){
                                Color.White
                            }else{
                                Color.Black
                            },
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                    }
                },
            )
        }
    }
}

data class NavigationItem(
    val name:String,
    @DrawableRes val selectedIcon: Int ,
    @DrawableRes val unselectedIcon: Int
)