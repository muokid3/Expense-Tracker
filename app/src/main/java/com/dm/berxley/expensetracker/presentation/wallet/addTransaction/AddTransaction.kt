package com.dm.berxley.expensetracker.presentation.wallet.addTransaction

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.dm.berxley.expensetracker.domain.models.Merchant
import com.dm.berxley.expensetracker.presentation.common.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransaction(navController: NavController) {

    val viewModel = hiltViewModel<AddTransactionViewModel>()
    val addTransactionState = viewModel.addTransactionState.collectAsState().value

    var selectedMerchant by remember {
        mutableStateOf<Merchant?>(null)
    }

    var isDropdownExpanded by remember {
        mutableStateOf(false)
    }

    var amount by rememberSaveable {
        mutableStateOf("")
    }

    var date by rememberSaveable {
        mutableStateOf("")
    }

    var time by rememberSaveable {
        mutableStateOf("")
    }

    var fees by rememberSaveable {
        mutableStateOf("")
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "ADD ${addTransactionState.expenseType}") },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "Notifications",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
            )
        )
    }) { paddingValues ->

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
                .verticalScroll(rememberScrollState())
                .padding(
                    top = paddingValues.calculateTopPadding() + 50.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
                .clip(RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp))
                .background(MaterialTheme.colorScheme.background),
        ) {
            Spacer(modifier = Modifier.height(Constants.SPACER_16))

            Column(modifier = Modifier.padding(Constants.PADDING_START_END)) {

                ExposedDropdownMenuBox(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = isDropdownExpanded,
                    onExpandedChange = { isDropdownExpanded = !isDropdownExpanded }) {

                    OutlinedTextField(
                        // The `menuAnchor` modifier must be passed to the text field to handle
                        // expanding/collapsing the menu on click. A read-only text field has
                        // the anchor type `PrimaryNotEditable`.
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        value = selectedMerchant?.name ?: "",
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true,
                        label = { Text("Select Merchant") },
                        leadingIcon = {
                            selectedMerchant?.let {
                                AsyncImage(
                                    modifier = Modifier.size(Constants.SPACER_24),
                                    model = it.icon_url,
                                    contentDescription = "Icon"
                                )
                            }
                        },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded) },
                    )

                    ExposedDropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false },
                    ) {
                        addTransactionState.merchantList.forEach { merchant ->
                            DropdownMenuItem(
                                text = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        AsyncImage(
                                            modifier = Modifier.size(Constants.SPACER_24),
                                            model = merchant.icon_url,
                                            contentDescription = "Icon"
                                        )
                                        Spacer(modifier = Modifier.width(Constants.SPACER_8))
                                        Text(
                                            merchant.name,
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                    }

                                },
                                onClick = {
                                    selectedMerchant = merchant
                                    isDropdownExpanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }

                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Constants.SPACER_8),
                    label = { Text(text = "Amount") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Next
                    ),
                    value = amount,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AttachMoney, contentDescription = null
                        )
                    },
                    onValueChange = {
                        amount = it
                    })

                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Constants.SPACER_8),
                    label = { Text(text = "Fees") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Next
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AttachMoney, contentDescription = null
                        )
                    },
                    value = fees,
                    onValueChange = {
                        fees = it
                    })

                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Constants.SPACER_8),
                    label = { Text(text = "Date") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Next
                    ),
                    value = date,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.CalendarToday, contentDescription = null
                        )
                    },
                    onValueChange = {
                        date = it
                    })

                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Constants.SPACER_8),
                    label = { Text(text = "Time") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Next
                    ),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.AccessTime, contentDescription = null
                        )
                    },
                    value = time,
                    onValueChange = {
                        time = it
                    })

                Spacer(modifier = Modifier.height(Constants.SPACER_24))

                Button(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
                    Text(text = "Save")
                }
            }


        }

    }
}

@Preview
@Composable
fun AddTransactionPrev() {
    AddTransaction(rememberNavController())
}