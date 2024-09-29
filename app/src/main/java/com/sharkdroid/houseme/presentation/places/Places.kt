package com.sharkdroid.houseme.presentation.places

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.sharkdroid.houseme.R
import com.sharkdroid.houseme.presentation.bottomnavigation.BottomNavigation
import com.sharkdroid.houseme.presentation.navigation.Routes
import com.sharkdroid.houseme.presentation.places.components.card.PlacesCardDesign
import com.sharkdroid.houseme.presentation.places.components.model.PlaceCardData

@Composable

fun Places(navController: NavHostController) {

    Scaffold(
        bottomBar = {

            BottomNavigation(selectedItem = 1, onClick = {index ->
                when(index){
                    0->  navController.navigate(Routes.HomeScreen)
                    1-> {
                        navController.navigate(Routes.PlacesScreen);
                    }
                    2-> navController.navigate(Routes.RoomScreen)
                    3-> navController.navigate(Routes.BookingHistory)
                    4-> navController.navigate(Routes.ProfileScreen)
                }
            } )
        }
    ) { it ->
Modifier.padding(it)

        val placesList =
            listOf(
                PlaceCardData(
                    R.drawable.pokharaplace2,
                    "Pokhara",
                    "Pokhara, a scenic city in Nepal, is famous for its lakes, mountain views, and outdoor adventures. Nestled by Phewa Lake and the Annapurna range, it's a hub for trekking and paragliding, offering both tranquility and excitement."
                ),
                PlaceCardData(
                    R.drawable.raraplace,
                    "Rara",
                    "Rara, Nepal's largest lake, is a serene destination in the remote northwest, surrounded by forests and mountains. Its crystal-clear waters and peaceful setting make it a hidden gem for nature lovers."
                ),
                PlaceCardData(
                    R.drawable.mustangpalce,
                    "Mustang",
                    "Mustang is a remote region in northern Nepal, renowned for its stunning landscapes and rich Buddhist culture. Once a forbidden kingdom, it features dramatic mountains and ancient monasteries, making it a popular trekking destination."
                ),
                PlaceCardData(
                    R.drawable.jumlaplace,
                    "Jumla",
                    "Jumla is a remote district in Nepal's Karnali Province, known for its lush valleys and apple orchards. Surrounded by mountains, it offers access to trekking routes like the Rara Lake trek and showcases the unique local culture."
                ),
                PlaceCardData(
                    R.drawable.dolakhaplace,
                    "Dolakha",
                    "Dolakha is a scenic district in central Nepal, known for its beautiful landscapes and cultural heritage. It features the revered Dolakha Bhimeshwor Temple and offers trekking opportunities, reflecting the diverse traditions of its local communities."
                )

            )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),

            ) {
            items(placesList) { placeCardData ->
                PlacesCardDesign(placeCardData)

            }
        }

    }
}












