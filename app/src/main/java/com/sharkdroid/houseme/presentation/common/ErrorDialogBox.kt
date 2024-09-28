package com.sharkdroid.houseme.presentation.common

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sharkdroid.houseme.R

@Composable
@Preview(showBackground = true)
fun ErrorDialogBox() {
    val horizontalGradient = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.Vivid_Sky_Blue),
            colorResource(id = R.color.Sea_Green)
        )
    )
    val robotoFamily = FontFamily(
        Font(R.font.roboto_medium)
    )

    Dialog(
        onDismissRequest = { /*TODO*/ }, properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {


        Card(modifier = Modifier.size(250.dp,200.dp)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp), horizontalArrangement = Arrangement.Center
                )
                {

                    Image(
                        painter = painterResource(id = R.drawable.exclamation),
                        contentDescription = null, modifier = Modifier.size(36.dp)
                    )

                }

                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier.fillMaxWidth().height(40.dp), contentAlignment = Alignment.Center
                )
                {
                    Text(text = "error ")
                }
                Spacer(modifier = Modifier.height(16.dp))

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {


                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(
                                brush = horizontalGradient,
                                shape = RoundedCornerShape(16.dp)
                            ),
                        colors = ButtonDefaults.buttonColors(
                            Color.Transparent
                        )
                    ) {
                        Text(
                            text = "Ok",
                            fontFamily = robotoFamily
                        )
                    }
                }


            }

        }
    }
}