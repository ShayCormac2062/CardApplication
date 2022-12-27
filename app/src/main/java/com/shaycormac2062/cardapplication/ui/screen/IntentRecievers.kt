package com.shaycormac2062.cardapplication.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

//Не работает без API_KEY, а он платный ._.
@Composable
fun OpenMap(
    latitude: Double?,
    longitude: Double?,
    cityName: String
) {
    val coordinates = LatLng(latitude ?: 0.0, longitude ?: 0.0)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(coordinates, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = coordinates),
            title = cityName,
            snippet = "Marker in $cityName"
        )
    }
}

@Composable
fun OpenDialer(
    phoneNumber: String
) {
    val ctx = LocalContext.current
    val u = Uri.parse("tel:$phoneNumber")
    val i = Intent(Intent.ACTION_DIAL, u)
    ctx.startActivity(i)
}
