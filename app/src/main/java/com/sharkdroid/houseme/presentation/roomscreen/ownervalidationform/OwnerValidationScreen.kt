package com.sharkdroid.houseme.presentation.roomscreen.ownervalidationform

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import com.sharkdroid.houseme.R
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sharkdroid.houseme.presentation.navigation.Routes

@Preview(showSystemUi = true)
@Composable
fun Routes.OwnerValidationScreen() {
    val robotoFamily = FontFamily(
        Font(R.font.roboto_medium)
    )
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var address by remember {
        mutableStateOf("")
    }
    var availableRoom by remember {
        mutableStateOf("")
    }

    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf<String?>(null) }
    val options = listOf("Passport", "National ID", "Driver's License")

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val horizontalGradient = Brush.linearGradient(
        colors = listOf(colorResource(id = R.color.Vivid_Sky_Blue), colorResource(id = R.color.Sea_Green))
    )
    val gradient = Brush.linearGradient(
        colors = listOf(colorResource(id = R.color.Sea_Green), colorResource(id = R.color.Art_Blue))
    )
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(brush = gradient)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Box (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ){
            Column(
                modifier = Modifier.padding(2.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Personal Information",
                        fontFamily = robotoFamily,
                        fontSize = 23.sp
                    )
                }
                OutlinedTextField(
                    value = name,
                    onValueChange = { name= it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Name :", color = Color.Black) },
                    enabled = false
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email= it},
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Email :", color = Color.Black) },
                    enabled = false
                )
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber= it},
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Phone Number :") }
                )
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Property Details",
                        fontFamily = robotoFamily,
                        fontSize = 23.sp
                    )
                }

                OutlinedTextField(
                    value = address,
                    onValueChange = { address= it},
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Property Address :") }
                )
                OutlinedTextField(
                    value = availableRoom,
                    onValueChange = { availableRoom= it},
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Number of Rooms Available :") }
                )
                Spacer(modifier = Modifier.height(32.dp))
                HorizontalDivider(color = Color.Gray)
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Verification Documents",
                        fontFamily = robotoFamily,
                        fontSize = 23.sp
                    )
                }

                // for document verification option
                Button(
                    onClick = { expanded = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color.White),
                ) {
                    Text( text=selectedOption ?: "Select Verification Document", color = Color.Black)
                    Icon(painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24), contentDescription = null, tint = Color.Black)
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedOption = option
                                expanded = false
                            }
                        )
                    }
                }



                val imagePickerLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent(),
                    onResult = { uri: Uri? ->
                        // Handle the image URI (set it to state)
                        selectedImageUri = uri
                    }
                )


                if (selectedImageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(selectedImageUri),
                        contentDescription = null,
                        modifier = Modifier
                            .size(350.dp,200.dp),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.placeholder),
                        contentDescription = null,
                        modifier = Modifier
                            .size(350.dp, 200.dp)
                            .clickable { imagePickerLauncher.launch("image/*") }, // Trigger image picker when clicking the placeholder
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                        .height(50.dp)
                        .background(brush = horizontalGradient, shape = RoundedCornerShape(8.dp)),
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(
                        text = "Update ",
                        fontSize = 16.sp,
                        fontFamily = robotoFamily
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))


    }
}