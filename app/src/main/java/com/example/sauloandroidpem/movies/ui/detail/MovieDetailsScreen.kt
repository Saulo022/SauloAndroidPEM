package com.example.sauloandroidpem.movies.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MovieDetailScreen(){
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(text = "DetailScreen")
    }
}