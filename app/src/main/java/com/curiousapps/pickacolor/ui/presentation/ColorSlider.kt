package com.curiousapps.pickacolor.ui.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun ColorSlider(
    label: String,
    value: Int,
    onChanged: (Int) -> Unit,
    color: Color,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$label: $value",
            fontSize = 18.sp
        )
        Slider(
            value = value.toFloat(),
            onValueChange = {onChanged(it.roundToInt())},
            valueRange = 0f..255f,
            steps = 254,
            colors = SliderDefaults.colors(thumbColor = color, activeTrackColor = color)
        )
    }
}