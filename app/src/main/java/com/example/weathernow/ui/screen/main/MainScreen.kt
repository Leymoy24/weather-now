package com.example.weathernow.ui.screen.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weathernow.R
import com.example.weathernow.data.model.CityModel
import com.example.weathernow.ui.screen.ScreenUiState

@Composable
fun MainScreen(navigateToInfoScreen: () -> Unit, viewModel: MainViewModel = hiltViewModel()) {

    val cities by viewModel.listOfCities.collectAsState()
    val uiState by viewModel.screenUiState.collectAsState()

    when (uiState) {
        is ScreenUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(48.dp))
            }
        }

        is ScreenUiState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = stringResource(id = R.string.error_message),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 42.dp)
                )
                Button(
                    onClick = { viewModel.getAllCities() }
                ) {
                    Text(
                        text = stringResource(R.string.refresh),
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        else -> {
            Box(
                Modifier
                    .fillMaxSize()
            ) {
                val groupedNames = remember(cities) {
                    cities.groupBy { it.city.first() }
                }

                val startIndexes = remember(cities) {
                    getStartIndexes(groupedNames.entries)
                }

                val endIndexes = remember(cities) {
                    getEndIndexes(groupedNames.entries)
                }

                val commonModifier = Modifier.width(50.dp)
                val listState = rememberLazyListState()

                val moveStickyHeader by remember {
                    derivedStateOf {
                        endIndexes.contains(listState.firstVisibleItemIndex + 1)
                    }
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = listState,
                ) {
                    itemsIndexed(cities) { index, city ->
                        NameItem(
                            city.city,
                            showCharHeader = startIndexes.contains(index) && listState.firstVisibleItemIndex != index,
                            commonModifier,
                            navigateToInfoScreen = navigateToInfoScreen,
                            setUpCurrentCityModel = { viewModel.setCurrentCityModel(city) }
                        )
                    }
                }
                LetterHeader(
                    char = cities[listState.firstVisibleItemIndex].city.first().toString(),
                    modifier = commonModifier.then(
                        if (moveStickyHeader) {
                            Modifier.offset {
                                IntOffset(0, -listState.firstVisibleItemScrollOffset)
                            }
                        } else {
                            Modifier
                        }
                    )
                )
            }
        }
    }
}

@Composable
fun LetterHeader(char: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(vertical = 8.dp)
            .size(40.dp),
        contentAlignment = Alignment.Center
    )
    {
        Text(
            text = char,
            color = Color(0xFF000000),
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun NameItem(
    name: String,
    showCharHeader: Boolean,
    modifier: Modifier,
    navigateToInfoScreen: () -> Unit,
    setUpCurrentCityModel: () -> Unit
) {
    Row(
        Modifier
            .height(56.dp)
            .fillMaxWidth(),
    ) {
        if (showCharHeader) {
            LetterHeader(
                char = name.first().toString(),
                modifier = modifier
            )
        } else {
            Spacer(modifier = modifier)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .align(Alignment.CenterVertically)
                .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                .clickable {
                    setUpCurrentCityModel()
                    navigateToInfoScreen()
                }
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = name,
                    color = Color(0xFF000000),
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

private fun getStartIndexes(entries: Set<Map.Entry<Char, List<CityModel>>>): List<Int> {
    var acc = 0
    val list = mutableListOf<Int>()
    entries.forEach { entry ->
        list.add(acc)
        acc += entry.value.size
    }
    return list
}

private fun getEndIndexes(entries: Set<Map.Entry<Char, List<CityModel>>>): List<Int> {
    var acc = 0
    val list = mutableListOf<Int>()
    entries.forEach { entry ->
        acc += entry.value.size
        list.add(acc)
    }
    return list
}