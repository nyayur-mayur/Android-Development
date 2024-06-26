package com.dicoding.rasagram.ui.pages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.rasagram.R
import com.dicoding.rasagram.data.repository.DishRepository
import com.dicoding.rasagram.presentation.login_screen.SignInViewModel
import com.dicoding.rasagram.ui.service.Screens
import com.dicoding.rasagram.ui.theme.Orange
import com.dicoding.rasagram.ui.theme.White
import com.dicoding.rasagram.ui.theme.poppinsFamily
import com.dicoding.rasagram.ui.widget.CustomIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomepageScreen(navController: NavHostController,viewModel : SignInViewModel= hiltViewModel()) {
    val dishRepository = DishRepository()
    val getAllData = dishRepository.getAllData()
    var searchText by remember{ mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screens.ScanImageScreen.route)
                },
                contentColor = Color.White,
                modifier = Modifier
                    .padding(16.dp),
                containerColor = Orange
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = "Profile",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
//        isFloatingActionButtonDocked = true,
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(innerPadding)) {
            Surface(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
                color = Color.Black
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    // Your Box content goes here

                    Image(
                        painter = painterResource(id = R.drawable.background), // Replace with your drawable resource name
                        contentDescription = "Example Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(200.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.6f)), // Lapisan gelap dengan transparansi
                    ) {
                        Column (Modifier.padding(20.dp)){
                            Image(
                                painter = painterResource(id = R.drawable.logout),
                                contentDescription = "Logo",
                                modifier = Modifier
                                    .size(30.dp)
                                    .align(Alignment.End)
                                    .clickable {
                                        viewModel.logout(navController)
                                        Toast.makeText(context, "Logout success", Toast.LENGTH_LONG).show()
                                    },
                                colorFilter = ColorFilter.tint(Color.White)
                            )
                            Text(
                                text = "Welcome",
                                style = TextStyle(
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = White,
                                    fontFamily = poppinsFamily
                                )
                            )
//                            Spacer(modifier = Modifier.height(45.dp))
                            OutlinedTextField(
                                value = searchText,
                                onValueChange = { searchText = it },
                                label = {
                                    Text(
                                        text = "Search", // Specify your label text here
                                        style = TextStyle(
                                            fontFamily = poppinsFamily,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White, // Set text color to white
                                            fontSize = 15.sp,
                                            textAlign = TextAlign.Center
                                        )
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp), // Set desired height here
                                shape = RoundedCornerShape(25.dp),
                                textStyle = TextStyle(color = Color.White), // Set text color in TextStyle
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.White,
                                    unfocusedBorderColor = Color.White,
                                    cursorColor = Color.White // Set cursor color to white
                                ),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search Icon",
                                        tint = Color.White // Set icon tint color to white
                                    )
                                }
                            )
                        }
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxHeight().padding(top=5.dp),
                contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items = getAllData.filter { it.dish_name.contains(searchText, ignoreCase = true) }) { dish ->
                    CustomIcon(dish = dish, navController = navController)
                }
            }
        }
    }
}


@Preview
@Composable
fun HomepageScreenPreview(){
    HomepageScreen(navController = rememberNavController())
}
