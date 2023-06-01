package com.harry.petronuschallenge.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.harry.petronuschallenge.R
import com.harry.petronuschallenge.components.*
import com.harry.petronuschallenge.data.model.Customer
import com.harry.petronuschallenge.data.model.DeviceHolder
import com.harry.petronuschallenge.navigation.Screens
import com.harry.petronuschallenge.ui.theme.PetronusChallengeTheme
import com.harry.petronuschallenge.ui.theme.Typography
import com.harry.petronuschallenge.utils.Resource
import com.harry.petronuschallenge.viewModel.DeviceHolderListViewModel

@Composable
fun DeviceHolderListScreen(
    navController: NavController,
    viewModel: DeviceHolderListViewModel? = hiltViewModel()
) {
    Scaffold(
        topBar = {
            AppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                title = stringResource(id = R.string.device_holders)
            )
        }
    ) {
        val resultFlow = viewModel?.resultFlow?.collectAsState(initial = Resource.Loading)

        resultFlow?.value?.let {
            when (it) {
                is Resource.Loading -> {
                    LoadingIndicator()
                }
                is Resource.Success -> {
                    val response = it.result
                    SuccessScreen(
                        list = response,
                        navController
                    )
                }
                is Resource.Failure -> {
                    ErrorScreen(errorMessage = stringResource(id = R.string.error_message))
                }
            }
        }
    }
}

@Composable
fun SuccessScreen(
    list: DeviceHolder,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp, top = 16.dp)
    ) {
        itemsIndexed(list.customers) { index, item: Customer ->
            Box(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("${Screens.DeviceHolderDetailsScreen.route}/Id=${item.id}") {
                        popUpTo(Screens.DeviceHolderDetailsScreen.route) { inclusive = true }
                    }
                }) {
                Row(horizontalArrangement = Arrangement.Start) {
                    LoadImage(
                        url = item.imageUrl,
                        firstLetter = item.firstName.first(),
                        lastLetter = item.lastName.first(),
                        padding = 24.dp
                    )
                    HorizontalSpacer(width = 12.dp)

                    Column {
                        Row {
                            Text(
                                text = "${item.firstName} ${item.lastName}",
                                style = Typography.body2
                            )
                            HorizontalSpacer(width = 8.dp)
                            Text(
                                text = buildString {
                                    append(item.gender.substring(0, 1).uppercase())
                                    append(item.gender.substring(1).lowercase())
                                },
                                style = Typography.body1
                            )
                        }
                        VerticalSpacer(height = 8.dp)
                        Row {
                            Text(
                                text = item.phoneNumber,
                                style = Typography.h3
                            )
                            HorizontalSpacer(width = 8.dp)
                            DisplayStickers(
                                item = item.stickers,
                                padding = 3.dp,
                                startPadding = 3.dp,
                                endPadding = 3.dp
                            )
                        }
                    }
                }
            }
            if (index < list.customers.size - 1) {
                VerticalSpacer(height = 16.dp)
                Divider(Modifier.padding(start = 24.dp, end = 0.dp))
                VerticalSpacer(height = 16.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeviceHolderListScreenPreview() {
    PetronusChallengeTheme {
        DeviceHolderListScreen(rememberNavController(), null)
    }
}