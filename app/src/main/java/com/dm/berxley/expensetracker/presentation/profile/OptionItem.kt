package com.dm.berxley.expensetracker.presentation.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.dm.berxley.expensetracker.presentation.common.Constants

@Composable
fun OptionItem(icon: ImageVector, name: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = Constants.PADDING_START_END,
                end = Constants.PADDING_START_END
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(imageVector = icon, contentDescription = name)
        Spacer(modifier = Modifier.width(Constants.PADDING_START_END))
        Text(
            text = name,
            fontSize = 20.sp
        )

    }

    Spacer(modifier = Modifier.height(Constants.SPACER_24))
}


@Preview(showBackground = true)
@Composable
fun OptionItemPrev() {
    OptionItem(
        Icons.Default.Home, "Home"
    )
}