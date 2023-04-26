package com.example.hirehub.model

import androidx.lifecycle.*
import com.example.hirehub.model.entities.Experience
import com.example.hirehub.model.entities.Position
import com.example.hirehub.model.repository.ExperienceRepository
import com.example.hirehub.model.repository.PositionRepository
import kotlinx.coroutines.launch

class ExperienceViewModel(private val experienceRepository: ExperienceRepository) : ViewModel() {

    val allPositions: LiveData<List<Experience>> = experienceRepository.allExperience.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(experience: Experience) = viewModelScope.launch {
        experienceRepository.insert(experience)
    }

    fun deleteAll() = viewModelScope.launch {
        experienceRepository.deleteAll()
    }
}

class ExperienceViewModelFactory(private val experienceRepository: ExperienceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExperienceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExperienceViewModel(experienceRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}