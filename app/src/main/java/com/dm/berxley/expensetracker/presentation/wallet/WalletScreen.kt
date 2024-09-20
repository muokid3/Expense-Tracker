package com.dm.berxley.expensetracker.presentation.wallet

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.SendTimeExtension
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.dm.berxley.expensetracker.presentation.common.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreen(navController: NavController) {

    var tabIndex by remember {
        mutableIntStateOf(0)
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding() + 50.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
                .clip(RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp))
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(Constants.SPACER_24))
            Text(text = "Total Balance", fontWeight = FontWeight.Light, fontSize = 18.sp)
            Text(text = "$ 2,546.89", fontWeight = FontWeight.Bold, fontSize = 24.sp)

            Spacer(modifier = Modifier.height(Constants.SPACER_24))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                IconButton(
                    modifier = Modifier.size(55.dp),
                    onClick = { /*TODO*/ },
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

            }

            Spacer(modifier = Modifier.height(Constants.SPACER_24))

            TabRow(
                modifier = Modifier.padding(
                    start = Constants.PADDING_START_END,
                    end = Constants.PADDING_START_END
                ), selectedTabIndex = tabIndex
            ) {
                tabs.forEachIndexed { index, tabName ->
                    Tab(
                        text = {
                            Text(text = tabName)
                        },
                        selected = index == tabIndex,
                        onClick = { tabIndex = index },
                    )
                }
            }

            when (tabIndex) {
                0 -> TransactionsScreen(navController)
                1 -> BillsScreen(navController)
            }

        }



    }

}

@Preview(showBackground = true)
@Composable
fun PrevWallet() {
    WalletScreen(navController = rememberNavController())
}