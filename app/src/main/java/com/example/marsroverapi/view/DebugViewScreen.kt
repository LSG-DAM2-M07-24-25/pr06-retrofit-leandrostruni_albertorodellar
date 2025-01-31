package com.example.marsroverapi.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.marsroverapi.viewmodel.APIViewModel

@Composable
fun DebugViewScreen(apiViewModel: APIViewModel){
    val marsPhotos by apiViewModel.marsPhotos.observeAsState()
    val loading by apiViewModel.loading.observeAsState(initial = true)

    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ){
        Button(
            onClick = {
                apiViewModel.fetchMarsPhotos(sol = 1000, apiKey = "0lIpSDWycEJvswsJFYvOgeotVX5pNSBkqD4cluZp")
            }
        )
        {
            Text("Cargar Datos de la API")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if(loading) {
            CircularProgressIndicator()
        } else {
            Text(
                text = marsPhotos?.photos?.joinToString("\n\n"){ photo->
                    "ID: ${photo.id}\n" +
                            "Fecha en Tierra: ${photo.earth_date}\n" +
                            "Rover: ${photo.rover.name}\n" +
                            "Cámara: ${photo.camera.full_name}\n" +
                            "URL Imagen: ${photo.img_src}"
                } ?: "No hay datos disponibles",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}