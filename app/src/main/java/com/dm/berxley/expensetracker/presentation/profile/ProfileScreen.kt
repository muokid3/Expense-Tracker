package com.dm.berxley.expensetracker.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dm.berxley.expensetracker.R
import com.dm.berxley.expensetracker.presentation.common.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Profile") },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = paddingValues.calculateTopPadding() - 1.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Constants.SPACER_150)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                        .background(MaterialTheme.colorScheme.primary)
                )

                Image(
                    modifier = Modifier
                        .size(Constants.SPACER_100)
                        .align(Alignment.BottomCenter)
                        .clip(
                            CircleShape
                        ),
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Pic"
                )

            }


            Spacer(modifier = Modifier.height(Constants.SPACER_24))

            Text(
                text = "John Doe",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Text(
                text = "john@doe.com",
                fontWeight = FontWeight.Light,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(Constants.SPACER_50))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = Constants.PADDING_START_END,
                        end = Constants.PADDING_START_END
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(imageVector = Icons.Default.Share, contentDescription = "Invite")
                Spacer(modifier = Modifier.width(Constants.PADDING_START_END))
                Text(
                    text = "Invite Friends",
                    fontSize = 20.sp
                )

            }

            Divider(modifier = Modifier.padding(Constants.PADDING_START_END))

            OptionItem(icon = Icons.Filled.Person, name = "Account Info" )
            OptionItem(icon = Icons.Filled.Group, name = "Personal Profile" )
            OptionItem(icon = Icons.Filled.Mail, name = "Message Center" )
            OptionItem(icon = Icons.Filled.Security, name = "Login and Security" )
            OptionItem(icon = Icons.Filled.PrivacyTip, name = "Data and Privacy" )


        }
    }

}

@Preview(showBackground = true)
@Composable
fun PrevProfile() {
    ProfileScreen(navController = rememberNavController())
}