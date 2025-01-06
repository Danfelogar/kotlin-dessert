package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel: ViewModel() {
    //Dessert UI State
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    /**
     * Determine which dessert to show.
     */
    private fun determineDessertToShow(
    dessertsSold: Int
    ): Dessert {
        var dessertToShow = dessertList.first()
        for (dessert in dessertList) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }

    fun onDessertClicked() {
        // Update the revenue
        _uiState.update { currentState ->
            val dessertsSold = currentState.dessertsSold + 1
            val nextShowDessert = determineDessertToShow(dessertsSold)
            currentState.copy(
                revenue = currentState.revenue + currentState.currentDessertPrice,
                dessertsSold= currentState.dessertsSold + 1,
                currentDessertImageId = nextShowDessert.imageId,
                currentDessertPrice = nextShowDessert.price
            )
        }
    }

    }