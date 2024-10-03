package com.dm.berxley.expensetracker.presentation.common

import java.text.NumberFormat
import java.util.Locale

class Common {
    companion object {
        fun formatDouble(number: Double, locale: Locale = Locale.getDefault()): String {
            val formatter = NumberFormat.getInstance(locale)
            formatter.isGroupingUsed = true
            formatter.minimumFractionDigits = 2

            return formatter.format(number)
        }
    }

}