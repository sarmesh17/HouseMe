package com.sharkdroid.houseme.presentation.homescreen

import android.app.DatePickerDialog
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sharkdroid.houseme.presentation.bottomnavigation.BottomNavigation
import com.sharkdroid.houseme.presentation.navigation.Routes
import com.sharkdroid.houseme.R
import com.sharkdroid.houseme.presentation.viewmodel.HomeScreenViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController,homeScreenViewModel: HomeScreenViewModel) {
    val robotoFamily = FontFamily(
        Font(R.font.roboto_medium)
    )
    val horizontalGradient = Brush.linearGradient(
        colors = listOf(colorResource(id = R.color.Vivid_Sky_Blue), colorResource(id = R.color.Sea_Green))
    )
    var expanded by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    // States to hold selected dates
    val checkInDate = remember { mutableStateOf("") }
    val checkOutDate = remember { mutableStateOf("") }

    // Calendar instance
    val calendar = Calendar.getInstance()

    // Date Formatter
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    // Context
    val context = LocalContext.current

    var selectedOption by remember { mutableStateOf("Air conditioned") }

    var adults by remember { mutableStateOf(0) }
    var children by remember { mutableStateOf(0) }
    var rooms by remember { mutableStateOf(0)}

    val rowItem = listOf(
        RowItem("Ivory Coast",R.drawable.view),
        RowItem("Ivory Coast",R.drawable.view),
        RowItem("Ivory Coast",R.drawable.view),
        RowItem("Ivory Coast",R.drawable.view)
    )
    val locations = listOf(
        LocationItem("Kathmandu", "Côte d'Ivoire", R.drawable.subtract),
        LocationItem("Bhaktapur", "Hyderabad, Telangana, India", R.drawable.subtract),
        LocationItem("Abidos Hotel Apartment Dubai Land", "Dubai, United Arab Emirates", R.drawable.subtract),
        LocationItem("Hotel Abi d'Oru", "Porto Rotondo, Sardinia, Italy",R.drawable.subtract),
        LocationItem("Abidos Hotel Apartment Al Barsha", "Dubai, United Arab Emirates", R.drawable.subtract)
    )
    
    Scaffold (
        topBar = @androidx.compose.runtime.Composable{
            TopAppBar(
                title = { Text(text = "Find room", fontSize = 24.sp, fontFamily = robotoFamily) },
                navigationIcon = {

                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(painter = painterResource(id = R.drawable.icon), contentDescription = "icon", modifier = Modifier.size(22.dp,20.dp))
                    }
                }
            )
        },
        bottomBar = {
            
            BottomNavigation(selectedItem = 0, onClick = {index ->
                when(index){
                    0-> { navController.navigate(Routes.HomeScreen); }
                    1-> navController.navigate(Routes.PlacesScreen)
                    2-> navController.navigate(Routes.RoomScreen)
                    3-> navController.navigate(Routes.BookingHistory)
                    4-> navController.navigate(Routes.ProfileScreen)
                }
            } )
        }
    ){it ->
        Modifier.padding(it)

        // Function to show date picker
        fun showDatePicker(onDateSelected: (String) -> Unit) {
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    // Update calendar with selected date
                    calendar.set(year, month, dayOfMonth)
                    // Format and return the selected date
                    onDateSelected(dateFormatter.format(calendar.time))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,

        ){
            Spacer(modifier = Modifier.height(5.dp))
            Image(painter = painterResource(id = R.drawable.house2), contentDescription =null, modifier = Modifier.size(350.dp,70.dp) )

            Spacer(modifier = Modifier.height(4.dp))

            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = Color.LightGray)

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                // Search TextField inside the ExposedDropdownMenuBox
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = {
                        Text(
                            text = "Where do you want",
                            fontSize =18.sp,
                            fontFamily = robotoFamily,
                            color = Color.Black
                        )
                    },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.subtract),
                            contentDescription = null,
                            modifier = Modifier.size(14.dp,20.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                        .menuAnchor(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.White,
                        focusedBorderColor = Color.White
                    )
                )

                // Dropdown suggestions
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    val filteredLocations = locations.filter {
                        it.name.contains(searchText, ignoreCase = true)
                    }

                    filteredLocations.forEach { location ->
                        androidx.compose.material.DropdownMenuItem(onClick = {
                            searchText = location.name
                            expanded = false
                        }) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.subtract),
                                    contentDescription = null,
                                    modifier = Modifier.size(14.dp,20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(text = location.name, fontWeight = FontWeight.Bold)
                                    Text(text = location.subTitle)
                                }
                            }
                        }
                    }
                }
            }

            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))



            //check in date
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                showDatePicker { selectedDate ->
                    checkInDate.value = selectedDate
                }
            },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Image(
                        painter = painterResource(id = R.drawable.checkin),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp,20.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = if (checkInDate.value.isEmpty()) "Select Check-In Date" else checkInDate.value, color = Color.Black, fontFamily = robotoFamily, fontSize = 18.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(painter = painterResource(id = R.drawable.outline_keyboard_arrow_down_24), contentDescription = null, tint = Color.Black)

                }
            }
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

            //check out date
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                showDatePicker { selectedDate ->
                    Log.d("checkin", selectedDate)
                    checkOutDate.value = selectedDate
                }
            },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Image(
                        painter = painterResource(id = R.drawable.checkin),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp,20.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = if (checkOutDate.value.isEmpty()) "Select Check-Out Date" else checkOutDate.value, color = Color.Black, fontFamily = robotoFamily, fontSize = 18.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(painter = painterResource(id = R.drawable.outline_keyboard_arrow_down_24), contentDescription = null, tint = Color.Black)

                }
            }
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

            // room and people selection
            Spacer(modifier = Modifier.height(16.dp))
            SelectionDropdown(
                adults = adults,
                children = children,
                rooms = rooms,
                onAdultsSelected = { adults = it },
                onChildrenSelected = { children = it },
                onRoomsSelected = { rooms = it }
            )
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

            Spacer(modifier = Modifier.height(8.dp))
            // for radio selection
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Fan Option
                RadioButton(
                    selected = selectedOption == "Fan",
                    onClick = { selectedOption = "Fan" }
                )
                Text("Fan")

                Spacer(modifier = Modifier.width(60.dp))

                // Air conditioned Option
                RadioButton(
                    selected = selectedOption == "Air conditioned",
                    onClick = { selectedOption = "Air conditioned" }
                )
                Text("Air conditioned")
            }


            //search button
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { homeScreenViewModel.searchRoomsByLocation(searchText);
                          navController.navigate(Routes.HotelListScreen)
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(43.dp)
                    .background(brush = horizontalGradient, shape = RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(
                    Color.Transparent
                )
            ) {
                Text(
                    text = "Search",
                    fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.height(32.dp))




            Box {
                Column {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "BEST PLACES",
                            fontSize = 14.sp,
                            fontFamily = robotoFamily
                        )
                        Text(
                            text = "VIEW ALL",
                            fontSize = 14.sp,
                            fontFamily = robotoFamily,
                            color = colorResource(id = R.color.Azure)
                        )
                    }

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                    ) {
                        items(rowItem) { rowItem ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .size(91.dp, 60.dp)
                                ) {
                                    // The image inside the Box
                                    Image(
                                        painter = painterResource(id = rowItem.icon),
                                        contentDescription = "img",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(12.dp)) // Round the image corners
                                    )
                                }
                                Text(
                                    text = rowItem.name,
                                    fontSize = 12.sp,
                                    fontFamily = robotoFamily
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "HOME STAY",
                            fontSize = 14.sp,
                            fontFamily = robotoFamily
                        )
                        Text(
                            text = "VIEW ALL",
                            fontSize = 14.sp,
                            fontFamily = robotoFamily,
                            color = colorResource(id = R.color.Azure)
                        )
                    }

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                    ) {
                        items(rowItem) { rowItem ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .size(91.dp, 60.dp)
                                ) {
                                    // The image inside the Box
                                    Image(
                                        painter = painterResource(id = rowItem.icon),
                                        contentDescription = "img",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(12.dp)) // Round the image corners
                                    )
                                }
                                Text(
                                    text = rowItem.name,
                                    fontSize = 12.sp,
                                    fontFamily = robotoFamily
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(75.dp))
                }
            }


        }
        
    }
}

data class RowItem(
    val name:String,
    @DrawableRes val icon: Int
)

data class LocationItem(
    val name: String,
    val subTitle: String,
    @DrawableRes val icon: Int
)


@Composable
fun SelectionDropdown(
    adults: Int,
    children: Int,
    rooms: Int,
    onAdultsSelected: (Int) -> Unit,
    onChildrenSelected: (Int) -> Unit,
    onRoomsSelected: (Int) -> Unit
) {
    val robotoFamily = FontFamily(
        Font(R.font.roboto_medium)
    )
    var expanded by remember { mutableStateOf(false) }

    Box {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.building_icon),
                contentDescription = null,
                modifier = Modifier.size(14.dp,20.dp),
                tint = colorResource(id = R.color.Vivid_Sky_Blue)
            )
            // Button to trigger dropdown
            TextButton(onClick = { expanded = true }) {
                Text("Adults: $adults     Children: $children     Rooms: $rooms", fontFamily = robotoFamily, fontSize = 18.sp, color = Color.Black)
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(painter = painterResource(id = R.drawable.outline_keyboard_arrow_down_24), contentDescription = null, tint = Color.Black)

        }


        // Simplified Dropdown Menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            // Title Row for categories
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text("Adults")
                Text("Children")
                Text("Rooms")
            }

            // Three Columns to handle selectors for Adults, Children, and Rooms
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                // Adults Selector Column
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    SimpleNumberSelector(
                        maxNumber = 5,
                        selectedNumber = adults,
                        onNumberSelected = onAdultsSelected
                    )
                }

                // Children Selector Column
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    SimpleNumberSelector(
                        maxNumber = 5,
                        selectedNumber = children,
                        onNumberSelected = onChildrenSelected
                    )
                }

                // Rooms Selector Column
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    SimpleNumberSelector(
                        maxNumber = 5,
                        selectedNumber = rooms,
                        onNumberSelected = onRoomsSelected
                    )
                }
            }
        }
    }
}

@Composable
fun SimpleNumberSelector(
    maxNumber: Int,
    selectedNumber: Int,
    onNumberSelected: (Int) -> Unit
) {
    // Display a vertical list of buttons with numbers
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        for (number in 0..maxNumber) {
            TextButton(onClick = { onNumberSelected(number) }) {
                Text(
                    text = number.toString(),
                    fontWeight = if (number == selectedNumber) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}
