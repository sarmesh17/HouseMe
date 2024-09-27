package com.sharkdroid.houseme.presentation.hotellist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharkdroid.houseme.R
import com.sharkdroid.houseme.presentation.hotellist.components.HotelListTab
import com.sharkdroid.houseme.presentation.hotellist.components.searchbar.SearchBar

@Composable
@Preview(showSystemUi = true)
fun HotelListScreen() {

    var selectedIndex by remember {
        mutableStateOf(HotelListTab.Amenities)
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 12.dp, end = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Hotels", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Icon(
                painter = painterResource(id = R.drawable.navigation_icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = colorResource(
                    id = R.color.Bright_Blue
                )
            )
        }

        Text(
            text = "Birgunj 100 hotels",
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.padding(horizontal = 16.dp)) {

            SearchBar(query = "", onQueryChanged = {}) {
            }

        }
        Spacer(modifier = Modifier.height(16.dp))

        TabRow(selectedTabIndex = selectedIndex.ordinal, containerColor = colorResource(
            id = R.color.white
        ), contentColor = Color.White,
            indicator = {}
        ) {
            // tab for chats
            Tab(
                selected = selectedIndex == HotelListTab.Amenities,
                onClick = { selectedIndex = HotelListTab.Amenities },
                modifier = Modifier.height(55.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(text = "Amenities", color = Color.Black)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.down_arrow),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }

            Tab(
                selected = selectedIndex == HotelListTab.Filter,
                onClick = { selectedIndex = HotelListTab.Filter }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(text = "Filter by", color = Color.Black)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.down_arrow),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }

            Tab(
                selected = selectedIndex == HotelListTab.Sort,
                onClick = { selectedIndex = HotelListTab.Sort }) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(text = "Sort by", color = Color.Black)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.down_arrow),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }

        }


    }
}
