package com.dm.berxley.expensetracker.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dm.berxley.expensetracker.domain.models.Transaction
import com.dm.berxley.expensetracker.presentation.common.Constants
import com.dm.berxley.expensetracker.presentation.common.TransactionItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    val transactions = listOf(
        Transaction(
            id = 1,
            type = "DR",
            merchant_name = "YouTube",
            merchant_icon_url = "https://www.youtube.com/s/desktop/7e8b1d69/img/favicon_144x144.png",
            amount = 15.45,
            fee = 1.50,
            total_amount = 16.95,
            date = "19th Sep 2024",
            time = "17:09"
        ),
        Transaction(
            id = 1,
            type = "CR",
            merchant_name = "Upwork",
            merchant_icon_url = "https://e7.pngegg.com/pngimages/257/806/png-clipart-upwork-freelancer-android-android-text-trademark-thumbnail.png",
            amount = 55.75,
            fee = 0.00,
            total_amount = 55.75,
            date = "18th Sep 2024",
            time = "17:09"
        ),
        Transaction(
            id = 1,
            type = "CR",
            merchant_name = "PayPal",
            merchant_icon_url = "https://e7.pngegg.com/pngimages/665/281/png-clipart-logo-computer-icons-paypal-paypal-blue-angle-thumbnail.png",
            amount = 100.00,
            fee = 1.50,
            total_amount = 100.50,
            date = "17th Sep 2024",
            time = "17:09"
        ),
        Transaction(
            id = 1,
            type = "DR",
            merchant_name = "Spotify",
            merchant_icon_url = "https://e7.pngegg.com/pngimages/158/639/png-clipart-spotify-streaming-media-logo-playlist-spotify-app-icon-logo-music-download-thumbnail.png",
            amount = 4.99,
            fee = 0.01,
            total_amount = 5.00,
            date = "16th Sep 2024",
            time = "17:09"
        )
    )

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            LargeTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                scrollBehavior = scrollBehavior,
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            contentDescription = "Notifications"
                        )
                    }
                },
                title = {
                    Column {
                        Text(text = "Good afternoon,", fontSize = 16.sp)
                        Text(text = "Dennis Muoki")
                    }
                })
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding() - 1.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState())
        ) {

            Box(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                        .background(MaterialTheme.colorScheme.primary)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = Constants.PADDING_START_END,
                            end = Constants.PADDING_START_END,
                        )
                        .height(170.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = Constants.SPACER_8
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f)
                            .padding(Constants.PADDING_START_END)
                    ) {

                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Total Balance", fontSize = 18.sp)
                            Text(
                                text = "$ 2,546.00",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )

                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.MoreHoriz, contentDescription = "more")
                        }

                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f)
                            .padding(Constants.PADDING_START_END)
                    ) {

                        Column(modifier = Modifier.weight(0.5f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.primary)
                                        .padding(3.dp)
                                        .size(Constants.SPACER_16),
                                    imageVector = Icons.Default.ArrowUpward,
                                    contentDescription = null
                                )
                                Text(
                                    text = "Income",
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 5.dp)
                                )
                            }
                            Text(
                                text = "$ 3,565.00",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                        }

                        Column(modifier = Modifier.weight(0.5f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.primary)
                                        .padding(3.dp)
                                        .size(Constants.SPACER_16),
                                    imageVector = Icons.Default.ArrowDownward,
                                    contentDescription = null
                                )
                                Text(
                                    text = "Expenses",
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 5.dp)
                                )
                            }
                            Text(
                                text = "$ 1,546.00",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                        }


                    }

                }
            }

            Row(
                modifier = Modifier
                    .padding(Constants.PADDING_START_END)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Transactions History", fontWeight = FontWeight.Bold)
                Text(text = "See All")
            }

            for (transaction in transactions){
                TransactionItem(transaction = transaction)
            }

//            LazyColumn(modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = Constants.PADDING_START_END, end = Constants.PADDING_START_END,)) {
//
//                items(6){
//                    Text(text = "Test")
//                }
//
//            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun PrevHome() {
    HomeScreen(navController = rememberNavController())
}