package com.harry.petronuschallenge.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.harry.petronuschallenge.R
import com.harry.petronuschallenge.components.*
import com.harry.petronuschallenge.data.model.DeviceHolderDetails
import com.harry.petronuschallenge.ui.theme.Typography
import com.harry.petronuschallenge.utils.Resource
import com.harry.petronuschallenge.viewModel.DeviceHolderDetailsViewModel

@Composable
fun DeviceHolderDetailsScreen(
    navController: NavController,
    id: String?,
    viewModel: DeviceHolderDetailsViewModel? = hiltViewModel()
) {
    Scaffold(
        topBar = {
            AppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                showBackArrow = true,
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        val detailsFlow = viewModel?.detailsFlow?.collectAsState()

        detailsFlow?.value?.let {
            when (it) {
                is Resource.Loading -> {
                    LoadingIndicator()
                }
                is Resource.Success -> {
                    val response = it.result
                    DetailsScreen(
                        details = response
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
fun DetailsScreen(
    details: DeviceHolderDetails
) {
    Column(
        modifier = Modifier
            .padding(start = 24.dp)
            .fillMaxWidth()
    ) {

        LoadMap(latitude = details.currentLatitude, longitude = details.currentLongitude)

        VerticalSpacer(height = 14.dp)
        Column()
        {
            LoadImage(
                url = details.imageUrl,
                firstLetter = details.firstName.first(),
                lastLetter = details.lastName.first()
            )

            VerticalSpacer(height = 24.dp)

            Text(
                text = "${details.firstName} ${details.lastName}",
                style = Typography.body2
            )

            VerticalSpacer(height = 8.dp)

            Row {
                DisplayStickers(
                    item = details.stickers,
                    padding = 3.dp,
                    startPadding = 3.dp,
                    endPadding = 3.dp
                )
            }
            VerticalSpacer(height = 8.dp)

            Row {
                Text(
                    text = buildString {
                        append(details.gender.substring(0, 1).uppercase())
                        append(details.gender.substring(1).lowercase())
                    },
                    style = Typography.h3
                )

                HorizontalSpacer(width = 10.dp)
                VerticalDivider()
                HorizontalSpacer(width = 10.dp)
                Text(
                    text = details.phoneNumber,
                    style = Typography.h3
                )
            }

            VerticalSpacer(height = 24.dp)
            Text(
                text = "ADDRESS",
                style = Typography.h4
            )

            VerticalSpacer(height = 8.dp)
            Text(
                text = "${details.address.street}, ${details.address.zip}",
                style = Typography.h3
            )
            Text(
                text = details.address.city,
                style = Typography.h3
            )
        }
    }

}