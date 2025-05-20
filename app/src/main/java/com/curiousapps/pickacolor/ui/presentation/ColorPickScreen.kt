package com.curiousapps.pickacolor.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ColorPickScreen(
    viewModel: ColorPickViewModel = ColorPickViewModel()
){
    val state by viewModel.state.collectAsState(ColorPickViewModel.ColorState())

    val red = rememberSaveable{ mutableIntStateOf(state.red)}
    val green = rememberSaveable{ mutableIntStateOf(state.green)}
    val blue = rememberSaveable{ mutableIntStateOf(state.blue)}

    val hexcode = remember(red.intValue, green.intValue, blue.intValue){
        String.format("#%02X%02X%02X", red.intValue, green.intValue, blue.intValue)
    }

    val scrollState = rememberScrollState()
    val clipboardManager = LocalClipboardManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Color Picker App",
            fontSize = 36.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Use sliders to choose a color:",
            fontSize = 18.sp,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(8.dp))
        ColorSlider(
            label = "RED",
            value = red.intValue,
            onChanged = {
                red.intValue = it
                viewModel.setRed(it)
            },
            color = Color.Red
        )
        ColorSlider(
            label = "GREEN",
            value = green.intValue,
            onChanged = {
                green.intValue = it
                viewModel.setGreen(it)
            },
            color = Color.Green
        )
        ColorSlider(
            label = "BLUE",
            value = blue.intValue,
            onChanged = {
                blue.intValue = it
                viewModel.setBlue(it)
            },
            color = Color.Blue
        )
    }
}