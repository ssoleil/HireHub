package com.example.hirehub.model

import androidx.lifecycle.*
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.Position
import kotlinx.coroutines.launch

class PositionViewModel(private val positionRepository: PositionRepository) : ViewModel() {

    val allPositions: LiveData<List<Position>> = positionRepository.allPositions.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(position: Position) = viewModelScope.launch {
        positionRepository.insert(position)
    }

    fun deleteAll() = viewModelScope.launch {
        positionRepository.deleteAll()
    }
}

class PositionViewModelFactory(private val positionRepository: PositionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PositionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PositionViewModel(positionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}