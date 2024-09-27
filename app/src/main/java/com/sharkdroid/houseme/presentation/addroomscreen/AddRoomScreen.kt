package com.sharkdroid.houseme.presentation.addroomscreen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import com.sharkdroid.houseme.R

@Preview(showSystemUi = true)
@Composable
fun AddRoomScreen() {
    val robotoFamily = FontFamily(
        Font(R.font.roboto_medium)
    )
    val gradient = Brush.linearGradient(
        colors = listOf(colorResource(id = R.color.Sea_Green), colorResource(id = R.color.Art_Blue))
    )
    val horizontalGradient = Brush.linearGradient(
        colors = listOf(colorResource(id = R.color.Vivid_Sky_Blue), colorResource(id = R.color.Sea_Green))
    )

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var selectedImageUriSecond by remember { mutableStateOf<Uri?>(null) }

    var description by remember {
        mutableStateOf("")
    }
    var checkIn by remember {
        mutableStateOf("CheckIn :")
    }
    var checkOut by remember {
        mutableStateOf("CheckOut :")
    }

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
            ){

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

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Cover Image",
                        fontFamily = robotoFamily,
                        fontSize = 23.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = {
                        Text(text = "Description")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painter = painterResource(id = R.drawable.location_circle), contentDescription = null, modifier = Modifier.size(18.dp,24.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Address")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painter = painterResource(id = R.drawable.checkin), contentDescription = null, modifier = Modifier.size(18.dp,24.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    BasicTextField(value = checkIn, onValueChange ={checkIn = it}, modifier = Modifier.border(width = 1.dp, color = Color.Gray) )


                    Spacer(modifier = Modifier.width(32.dp))


                    Image(painter = painterResource(id = R.drawable.checkout), contentDescription = null, modifier = Modifier.size(18.dp,24.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    BasicTextField(value = checkOut, onValueChange ={checkOut = it}, modifier = Modifier.border(width = 1.dp, color = Color.Gray) )
                }

                Spacer(modifier = Modifier.height(32.dp))
                HorizontalDivider(color = Color.Gray)
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Food Image",
                        fontFamily = robotoFamily,
                        fontSize = 23.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                val imagePickerLauncherSecond = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent(),
                    onResult = { uri: Uri? ->
                        // Handle the image URI (set it to state)
                        selectedImageUriSecond = uri
                    }
                )

                if (selectedImageUriSecond != null) {
                    Image(
                        painter = rememberAsyncImagePainter(selectedImageUriSecond),
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
                        text = "Add Now",
                        fontSize = 16.sp,
                        fontFamily = robotoFamily
                    )
                }

            }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}