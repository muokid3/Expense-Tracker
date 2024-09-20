package com.dm.berxley.expensetracker.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dm.berxley.expensetracker.R
import com.dm.berxley.expensetracker.domain.models.Bill

@Composable
fun BillItem(bill: Bill) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = Constants.PADDING_START_END,
                end = Constants.PADDING_START_END,
                top = Constants.SPACER_4,
                bottom = Constants.SPACER_4
            ),
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
                model = bill.merchant_icon_url,
                contentDescription = bill.merchant_name,
                placeholder = painterResource(id = R.drawable.placeholder)
            )
        }

        Column(
            modifier = Modifier
                .padding(start = Constants.PADDING_START_END)
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${bill.merchant_name} - ${bill.status}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(Constants.SPACER_8))
            Text(text = bill.due_date, fontWeight = FontWeight.Light, fontSize = 14.sp)

        }

        ElevatedButton(
            onClick = { /*TODO*/ }) {
            Text(text = "Pay")
        }

    }

}


@Preview(showBackground = true)
@Composable
fun BillPrev() {
    val bill = Bill(
        id = 1,
        merchant_name = "Spotify",
        merchant_icon_url = "https://e7.pngegg.com/pngimages/158/639/png-clipart-spotify-streaming-media-logo-playlist-spotify-app-icon-logo-music-download-thumbnail.png",
        amount = 5.50,
        due_date = "1st Oct 2024",
        status = "Pending"
    )
    BillItem(bill = bill)

}