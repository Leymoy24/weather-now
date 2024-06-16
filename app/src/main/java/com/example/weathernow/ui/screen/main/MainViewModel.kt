package com.example.weathernow.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathernow.data.api.ApiResult
import com.example.weathernow.data.model.CityModel
import com.example.weathernow.data.repository.MainRepository
import com.example.weathernow.ui.screen.ScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _screenUiState = MutableStateFlow<ScreenUiState>(ScreenUiState.Initial)
    val screenUiState: StateFlow<ScreenUiState> = _screenUiState

    private val _listOfCities = MutableStateFlow<List<CityModel>>(listOf())

    val listOfCities: StateFlow<List<CityModel>> = _listOfCities

    init { getAllCities() }

    fun getAllCities() {
        viewModelScope.launch {
            _screenUiState.value = ScreenUiState.Loading

            when (val result = repository.getCities()) {
                is ApiResult.Success -> {
                    _screenUiState.value = ScreenUiState.Success(result.data)
                    _listOfCities.value = result.data.sortedBy { it.city }
                }

                is ApiResult.Error -> {
                    _screenUiState.value = ScreenUiState.Error(result.error)
                }
            }
        }
    }

    fun setCurrentCityModel(cityModel: CityModel){
        repository.setCurrentCity(cityModel = cityModel)
    }
}