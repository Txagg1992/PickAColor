package com.curiousapps.pickacolor.ui.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ColorPickViewModel: ViewModel() {

    private val _state = MutableStateFlow(ColorState())
    val state: StateFlow<ColorState> = _state

    fun setRed(value: Int){
        _state.update { it.copy(
            red = value
        ) }
    }

    fun setGreen(value: Int){
        _state.update { it.copy(
            green = value
        ) }
    }

    fun setBlue(value: Int){
        _state.update { it.copy(
            blue = value
        ) }
    }

    data class ColorState(
        val red: Int = 128,
        val green: Int = 128,
        val blue: Int = 128,
    )
}