package com.dm.berxley.expensetracker.presentation.wallet

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.SendTimeExtension
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dm.berxley.expensetracker.domain.models.Bill
import com.dm.berxley.expensetracker.domain.models.Transaction
import com.dm.berxley.expensetracker.presentation.common.BillItem
import com.dm.berxley.expensetracker.presentation.common.Constants
import com.dm.berxley.expensetracker.presentation.common.TransactionItem
import com.dm.berxley.expensetracker.presentation.navgraph.Screen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun WalletScreen(navController: NavController) {

    val viewModel = hiltViewModel<WalletViewModel>()
    val selectedIndex = viewModel.selectedIndex.collectAsState().value

    val bottomSheetState = rememberModalBottomSheetState()
    var isBottomSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    val tabs = listOf(
        "Transactions",
        "Upcoming Bills"
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Wallet") },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notifications",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
    ) { paddingValues ->

        Box(
            Modifier
                .fillMaxWidth()
                .padding(
                    top = paddingValues.calculateTopPadding() - 1.dp,
                )
                .height(Constants.SPACER_150)
                .background(MaterialTheme.colorScheme.primary)
        )

        if (isBottomSheetOpen) {
            ModalBottomSheet(
                sheetState = bottomSheetState,
                onDismissRequest = { isBottomSheetOpen = false }) {
                Column(
                    modifier = Modifier.padding(
                        start = Constants.PADDING_START_END,
                        end = Constants.PADDING_START_END,
                        bottom = Constants.PADDING_START_END
                    )
                ) {
                    Text(text = "Which type of transactions would you like to add?")
                    Spacer(modifier = Modifier.height(Constants.SPACER_24))

                    TextButton(onClick = {
                        isBottomSheetOpen = false
                        navController.navigate(
                            Screen.AddTransactionScreen.route.replace(
                                "{transactionType}",
                                "EXPENSE"
                            )
                        )
                    }) {
                        Icon(imageVector = Icons.Default.ArrowCircleDown, contentDescription = null)
                        Spacer(modifier = Modifier.width(Constants.SPACER_16))
                        Text(text = "Add Expense")
                    }

                    TextButton(onClick = {
                        isBottomSheetOpen = false
                        navController.navigate(
                            Screen.AddTransactionScreen.route.replace(
                                "{transactionType}",
                                "INCOME"
                            )
                        )
                    }) {
                        Icon(imageVector = Icons.Default.ArrowCircleUp, contentDescription = null)
                        Spacer(modifier = Modifier.width(Constants.SPACER_16))
                        Text(text = "Add Income")
                    }

                }

            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding() + 50.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
                .clip(RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp))
                //.verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(Constants.SPACER_24))
            }

            item {
                Text(text = "Total Balance", fontWeight = FontWeight.Light, fontSize = 18.sp)
                Text(text = "$ 2,546.89", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            }

            item {
                Spacer(modifier = Modifier.height(Constants.SPACER_24))
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column {

                        IconButton(
                            modifier = Modifier.size(55.dp),
                            onClick = { isBottomSheetOpen = true },
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = Color.White,
                                containerColor = MaterialTheme.colorScheme.primary,
                            )
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.onPrimary)
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(Constants.SPACER_8),
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "add",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }

                        }

                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = Constants.SPACER_8),
                            text = "Add",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }


                    Column {
                        IconButton(
                            modifier = Modifier.size(55.dp),
                            onClick = { /*TODO*/ },
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = MaterialTheme.colorScheme.onPrimary,
                                containerColor = MaterialTheme.colorScheme.primary,
                            )
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.onPrimary)
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(Constants.SPACER_8),
                                    imageVector = Icons.Default.QrCode,
                                    contentDescription = "pay",
                                    tint = MaterialTheme.colorScheme.primary,
                                )
                            }

                        }

                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = Constants.SPACER_8),
                            text = "Pay",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Column {
                        IconButton(
                            modifier = Modifier.size(55.dp),
                            onClick = { /*TODO*/ },
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = MaterialTheme.colorScheme.onPrimary,
                                containerColor = MaterialTheme.colorScheme.primary,
                            )
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.onPrimary)
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(Constants.SPACER_8),
                                    imageVector = Icons.Default.SendTimeExtension,
                                    contentDescription = "send",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = Constants.SPACER_8),
                            text = "Send",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                }
            }

            item {
                Spacer(modifier = Modifier.height(Constants.SPACER_24))
            }

            stickyHeader {
                TabRow(
                    modifier = Modifier.padding(
                        start = Constants.PADDING_START_END,
                        end = Constants.PADDING_START_END
                    ), selectedTabIndex = selectedIndex
                ) {
                    tabs.forEachIndexed { index, tabName ->
                        Tab(
                            text = {
                                Text(text = tabName)
                            },
                            selected = index == selectedIndex,
                            onClick = { viewModel.setSelectedIndex(index) },
                        )
                    }
                }
            }

            when (selectedIndex) {
                0 -> transactionsScreen()
                1 -> billsScreen()
            }
        }

    }

}


fun LazyListScope.transactionsScreen() {
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
        ),
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
        ),
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
        ),
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
    itemsIndexed(transactions) { index, transaction ->
        TransactionItem(transaction = transaction)
    }
}

fun LazyListScope.billsScreen() {
    val bills = listOf(
        Bill(
            id = 1,
            merchant_name = "Spotify",
            merchant_icon_url = "https://e7.pngegg.com/pngimages/158/639/png-clipart-spotify-streaming-media-logo-playlist-spotify-app-icon-logo-music-download-thumbnail.png",
            amount = 5.50,
            due_date = "1st Oct 2024",
            status = "Pending"
        ),
        Bill(
            id = 1,
            merchant_name = "Youtube",
            merchant_icon_url = "https://www.youtube.com/s/desktop/7e8b1d69/img/favicon_144x144.png",
            amount = 3.00,
            due_date = "10th Sept 2024",
            status = "Due"
        ),
        Bill(
            id = 1,
            merchant_name = "Upwork",
            merchant_icon_url = "https://e7.pngegg.com/pngimages/257/806/png-clipart-upwork-freelancer-android-android-text-trademark-thumbnail.png",
            amount = 300.00,
            due_date = "21st Sept 2024",
            status = "Due"
        ),
        Bill(
            id = 1,
            merchant_name = "Spotify",
            merchant_icon_url = "https://e7.pngegg.com/pngimages/158/639/png-clipart-spotify-streaming-media-logo-playlist-spotify-app-icon-logo-music-download-thumbnail.png",
            amount = 5.50,
            due_date = "1st Oct 2024",
            status = "Pending"
        ),
        Bill(
            id = 1,
            merchant_name = "Youtube",
            merchant_icon_url = "https://www.youtube.com/s/desktop/7e8b1d69/img/favicon_144x144.png",
            amount = 3.00,
            due_date = "10th Sept 2024",
            status = "Due"
        ),
        Bill(
            id = 1,
            merchant_name = "Upwork",
            merchant_icon_url = "https://e7.pngegg.com/pngimages/257/806/png-clipart-upwork-freelancer-android-android-text-trademark-thumbnail.png",
            amount = 300.00,
            due_date = "21st Sept 2024",
            status = "Due"
        ),
        Bill(
            id = 1,
            merchant_name = "Spotify",
            merchant_icon_url = "https://e7.pngegg.com/pngimages/158/639/png-clipart-spotify-streaming-media-logo-playlist-spotify-app-icon-logo-music-download-thumbnail.png",
            amount = 5.50,
            due_date = "1st Oct 2024",
            status = "Pending"
        ),
        Bill(
            id = 1,
            merchant_name = "Youtube",
            merchant_icon_url = "https://www.youtube.com/s/desktop/7e8b1d69/img/favicon_144x144.png",
            amount = 3.00,
            due_date = "10th Sept 2024",
            status = "Due"
        ),
        Bill(
            id = 1,
            merchant_name = "Upwork",
            merchant_icon_url = "https://e7.pngegg.com/pngimages/257/806/png-clipart-upwork-freelancer-android-android-text-trademark-thumbnail.png",
            amount = 300.00,
            due_date = "21st Sept 2024",
            status = "Due"
        ),
        Bill(
            id = 1,
            merchant_name = "Spotify",
            merchant_icon_url = "https://e7.pngegg.com/pngimages/158/639/png-clipart-spotify-streaming-media-logo-playlist-spotify-app-icon-logo-music-download-thumbnail.png",
            amount = 5.50,
            due_date = "1st Oct 2024",
            status = "Pending"
        ),
        Bill(
            id = 1,
            merchant_name = "Youtube",
            merchant_icon_url = "https://www.youtube.com/s/desktop/7e8b1d69/img/favicon_144x144.png",
            amount = 3.00,
            due_date = "10th Sept 2024",
            status = "Due"
        ),
        Bill(
            id = 1,
            merchant_name = "Upwork",
            merchant_icon_url = "https://e7.pngegg.com/pngimages/257/806/png-clipart-upwork-freelancer-android-android-text-trademark-thumbnail.png",
            amount = 300.00,
            due_date = "21st Sept 2024",
            status = "Due"
        )
    )

    itemsIndexed(bills) { index, bill ->
        BillItem(bill = bill)
    }
}

@Preview(showBackground = true)
@Composable
fun PrevWallet() {
    WalletScreen(navController = rememberNavController())
}