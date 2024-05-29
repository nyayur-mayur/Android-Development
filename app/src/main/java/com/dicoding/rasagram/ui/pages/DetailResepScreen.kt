package com.dicoding.rasagram.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.rasagram.ui.theme.Black
import com.dicoding.rasagram.ui.theme.Orange
import com.dicoding.rasagram.ui.theme.poppinsFamily

@Composable
fun DetailResepScreen(){
    Surface {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(300.dp)
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
                    .background(color = Black)
                    )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Sayur Sop",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Orange,
                    fontFamily = poppinsFamily
                ),
                modifier = Modifier.align(Alignment.Start)
            )
            
        }
    }
}



@Preview
@Composable
fun DetailResepScreenPreview(){
    DetailResepScreen()
}