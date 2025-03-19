package com.arcee.parkit.presentation.parking_space_locator

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.arcee.parkit.R
import com.arcee.parkit.common.Resource
import com.arcee.parkit.domain.model.Provider
import com.arcee.parkit.presentation.parking_space_locator.components.ProviderItem
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParkingSpaceLocatorScreen(
    viewModel: ParkingSpaceLocatorViewModel = hiltViewModel<ParkingSpaceLocatorViewModel>(),
    onStartNavClicked: (lat: Double, lon: Double) -> Unit,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current;

    val providersState by viewModel.providers.collectAsState()

    val fusedLocationProviderClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    var targetLocation by remember { mutableStateOf<Location?>(null) }

    fun updateCurrLocation() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { loc ->
            viewModel.setLocation(loc)
            targetLocation = loc
        }
    }

    val cameraPositionState = rememberCameraPositionState {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { loc ->
                    targetLocation = loc
                }
                .addOnFailureListener { e ->
                    println(e)
                }
        }
    }

    LaunchedEffect(targetLocation) {
        targetLocation?.let { location ->
            with(cameraPositionState) {
                animate(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(location.latitude, location.longitude),
                        18f
                    ), // Zoom to the new location
                    durationMs = 1000 // Animation duration
                )
            }

            viewModel.setLocation(location)
        }
    }

    var selectedLocation by remember { mutableStateOf<Provider?>(null) }

    /**
     * Initiate a phone call.
     */
    fun handleDialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    /**
     * Add to favourites.
     */
    fun handleSaveToFavourites() {}

    Scaffold(containerColor = MaterialTheme.colorScheme.background) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // References
                val (mapRef, btnBackRef, btnCurrPositionRef) = createRefs()
                GoogleMap(
                    modifier = Modifier.constrainAs(mapRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    cameraPositionState = cameraPositionState,
                    properties = MapProperties(
                        isMyLocationEnabled = true
                    ),
                    uiSettings = MapUiSettings(
                        myLocationButtonEnabled = false,
                        zoomControlsEnabled = false,
                        compassEnabled = true,
                    ),
                ) {
                    when (providersState) {
                        is Resource.Error -> TODO()
                        is Resource.Loading -> TODO()
                        is Resource.Success -> {
                            val providerFlow = (providersState as Resource.Success).data
                            val providers = providerFlow?.collectAsLazyPagingItems()

                            providers?.itemSnapshotList?.forEach { provider ->
                                provider?.let { it ->
                                    val singapore = LatLng(it.latitude, it.longitude)

                                    Marker(
                                        state = rememberMarkerState(position = singapore),
                                        title = "Singapore",
                                        snippet = "Marker",
                                        onClick = {
                                            selectedLocation = provider
                                            true
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                FilledTonalIconButton(
                    onClick = { onNavigateBack() },
                    modifier = Modifier
                        .padding(12.dp)
                        .constrainAs(btnBackRef) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        },
                    colors = IconButtonDefaults.filledTonalIconButtonColors()
                        .copy(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)
                ) {
                    Icon(
                        Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Navigate back",
                    )
                }

                FilledTonalIconButton(
                    onClick = { updateCurrLocation() },
                    modifier = Modifier
                        .padding(12.dp)
                        .constrainAs(btnCurrPositionRef) {
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        },
                    colors = IconButtonDefaults.filledTonalIconButtonColors()
                        .copy(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.gps_fixed_24),
                        contentDescription = "Update current location"
                    )
                }
            }

            if (selectedLocation != null) {
                ModalBottomSheet(onDismissRequest = {
                    selectedLocation = null
                }) {
                    ProviderItem(
                        data = selectedLocation!!,
                        onBookmarkClicked = { handleSaveToFavourites() },
                        onPhoneClicked = { phoneNumber -> handleDialPhoneNumber(phoneNumber) },
                        onStartNavClicked = onStartNavClicked
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParkingSpaceLocatorScreenPreview() {
    ParkingSpaceLocatorScreen(
        onNavigateBack = {},
        viewModel = TODO(),
        onStartNavClicked = TODO(),
    )
}