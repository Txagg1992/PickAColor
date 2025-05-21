package com.curiousapps.pickacolor.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiousapps.pickacolor.ui.theme.PickAColorTheme

@Composable
fun ColorPickScreen(){

    val red = rememberSaveable{ mutableIntStateOf(128)}
    val green = rememberSaveable{ mutableIntStateOf(128)}
    val blue = rememberSaveable{ mutableIntStateOf(128)}
    val alpha = rememberSaveable{ mutableIntStateOf(255)}
    val color = remember {
        derivedStateOf {
            Color(
                red = red.intValue,
                green = green.intValue,
                blue = blue.intValue,
                alpha = alpha.intValue
            )
        }
    }
    val contentColor = remember(color.value) {
        // Use the W3C algorithm for calculating contrast (thanks Claude.ai)
        val luminance = 0.299f * color.value.red +
                0.587f * color.value.green +
                0.114f * color.value.blue

        if (luminance < 0.5f) Color.White else Color.Black
    }

    val hexcode = remember(red.intValue, green.intValue, blue.intValue){
        String.format("0x%02X%02X%02X%02X", alpha.intValue, red.intValue, green.intValue, blue.intValue)
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
            },
            color = Color.Red
        )
        ColorSlider(
            label = "GREEN",
            value = green.intValue,
            onChanged = {
                green.intValue = it
            },
            color = Color.Green
        )
        ColorSlider(
            label = "BLUE",
            value = blue.intValue,
            onChanged = {
                blue.intValue = it
            },
            color = Color.Blue
        )
        ColorSlider(
            label = "ALPHA",
            value = alpha.intValue,
            onChanged = { alpha.intValue = it },
            color = Color.White
        )

        Box(
            modifier = Modifier
                .size(150.dp)
                .background(color = color.value)
        )
        Text(
            text = hexcode,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = color.value,
                contentColor = contentColor
            ),
            onClick = {clipboardManager.setText(androidx.compose.ui.text.AnnotatedString(hexcode))},
            shape = RoundedCornerShape(6.dp)
        ){
            Text(
                text = "Copy to clipboard"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ColorPickScreenPreview() {
    PickAColorTheme {
        ColorPickScreen()
    }
}