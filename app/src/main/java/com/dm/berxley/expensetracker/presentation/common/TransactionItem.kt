package com.dm.berxley.expensetracker.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dm.berxley.expensetracker.R
import com.dm.berxley.expensetracker.domain.models.Transaction

@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth().
            padding(start = Constants.PADDING_START_END, end = Constants.PADDING_START_END, top = Constants.SPACER_4, bottom = Constants.SPACER_4),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(Constants.SPACER_50)
        ) {
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.Center),
                contentScale = ContentScale.FillBounds,
                model = transaction.merchant_icon_url,
                contentDescription = transaction.merchant_name,
                placeholder = painterResource(id = R.drawable.placeholder)
            )
        }

        Column(
            modifier = Modifier
                .padding(start = Constants.PADDING_START_END)
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = transaction.merchant_name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(Constants.SPACER_8))
            Text(text = transaction.date, fontWeight = FontWeight.Light, fontSize = 14.sp)

        }

        Text(
            modifier = Modifier.padding(end = Constants.PADDING_START_END),
            text = if (transaction.type == "CR") "+ $${transaction.total_amount}" else "- $${transaction.total_amount}",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = if (transaction.type == "CR") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
        )


    }

}


@Preview(showBackground = true)
@Composable
fun TransactionPrev() {
    val transaction = Transaction(
        id = 1,
        type = "DR",
        merchant_name = "YouTube",
        merchant_icon_url = "https://e7.pngegg.com/pngimages/125/937/png-clipart-youtube-logo-youtube-angle-logo-thumbnail.png",
        amount = 15.45,
        fee = 1.50,
        total_amount = 16.95,
        date = "19th Sep 2024",
        time = "17:09"
    )
    TransactionItem(transaction = transaction)

}