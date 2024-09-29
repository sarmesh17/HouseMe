package com.sharkdroid.houseme.presentation.houselist

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sharkdroid.houseme.domain.model.RoomData
import com.sharkdroid.houseme.presentation.viewmodel.HomeScreenViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.sharkdroid.houseme.presentation.houselist.components.card.HouseListCardRowInfo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Preview(showSystemUi = true)
@Composable
fun HouseListScreen() {
    val dummyRooms = listOf(
        RoomData(
            checkIn = "14:00",
            checkOut = "12:00",
            description = "A cozy room with a beautiful view of the mountains.",
            discount = "10%",
            foodImageUrl = "https://example.com/food1.jpg",
            price = "$120/night",
            roomImageUrl = "https://example.com/room1.jpg",
            roomName = "Mountain View Suite",
            propertyLocation = "Aspen, Colorado"
        ),
        RoomData(
            checkIn = "15:00",
            checkOut = "11:00",
            description = "Luxurious room with access to a private pool.",
            discount = "15%",
            foodImageUrl = "https://example.com/food2.jpg",
            price = "$250/night",
            roomImageUrl = "https://example.com/room2.jpg",
            roomName = "Poolside Deluxe",
            propertyLocation = "Miami, Florida"
        ),
        RoomData(
            checkIn = "13:00",
            checkOut = "12:00",
            description = "Spacious room with modern amenities and city views.",
            discount = "5%",
            foodImageUrl = "https://example.com/food3.jpg",
            price = "$180/night",
            roomImageUrl = "https://example.com/room3.jpg",
            roomName = "City Lights Room",
            propertyLocation = "New York City, New York"
        ),
        RoomData(
            checkIn = "14:30",
            checkOut = "11:30",
            description = "Quiet room with garden access and complimentary breakfast.",
            discount = "20%",
            foodImageUrl = "https://example.com/food4.jpg",
            price = "$90/night",
            roomImageUrl = "https://example.com/room4.jpg",
            roomName = "Garden Escape",
            propertyLocation = "Portland, Oregon"
        ),
        RoomData(
            checkIn = "16:00",
            checkOut = "10:00",
            description = "Modern suite with sea views and private balcony.",
            discount = "12%",
            foodImageUrl = "https://example.com/food5.jpg",
            price = "$300/night",
            roomImageUrl = "https://example.com/room5.jpg",
            roomName = "Oceanfront Suite",
            propertyLocation = "Malibu, California"
        )
    )


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(dummyRooms){
                HouseListCardRowInfo(roomData = it)
            }
        }
    }
}

