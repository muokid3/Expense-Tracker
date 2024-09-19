package com.dm.berxley.expensetracker.presentation.onboarding.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dm.berxley.expensetracker.R
import com.dm.berxley.expensetracker.presentation.common.Constants
import com.dm.berxley.expensetracker.presentation.navgraph.Screen

@Composable
fun IntroScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Box(modifier = Modifier.weight(0.7f)) {

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.bg1),
                contentDescription = null
            )


            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = Constants.SPACER_100),
                painter = painterResource(id = R.drawable.man),
                contentDescription = null
            )

        }

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(Constants.SPACER_24),
            painter = painterResource(id = R.drawable.bg2), contentDescription = null
        )

        Column(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxWidth()
                .padding(start = Constants.PADDING_START_END, end = Constants.PADDING_START_END),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                text = "Spend Smarter Save More",
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = { navController.navigate(Screen.CoreNavigator.route) }) {
                Text(text = "Get Started")
            }

            TextButton(
                onClick = { /*TODO*/ }) {
                Text(text = "Already have an account? ", color = Color.Black)
                Text(text = "Log In", color = MaterialTheme.colorScheme.primary)
            }
        }


    }

}


@Preview(showBackground = true)
@Composable
fun PrevIntro() {
    IntroScreen(navController = rememberNavController())
}