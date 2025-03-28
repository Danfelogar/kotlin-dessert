package com.example.dessertclicker

import androidx.annotation.DrawableRes
import com.example.dessertclicker.data.Datasource.dessertList

/**
 * Data class that represents the game UI state
 */
data class DessertUiState(
    val currentDessertIndex: Int = 0,
    val dessertsSold: Int = 0,
    val revenue: Int = 0,
    val currentDessertPrice: Int = dessertList[currentDessertIndex].price,
    @DrawableRes val currentDessertImageId: Int = dessertList[currentDessertIndex].imageId
    )
