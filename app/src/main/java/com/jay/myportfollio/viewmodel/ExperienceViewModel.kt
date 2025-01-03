package com.jay.myportfollio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jay.myportfollio.model.datamodel.Experience
import com.jay.myportfollio.model.datamodel.FireStoreResult
import com.jay.myportfollio.model.repository.experience.ExperienceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExperienceViewModel(private val repository: ExperienceRepository) : ViewModel() {
    private val _experienceState =
        MutableStateFlow<FireStoreResult<List<Experience>>>(FireStoreResult.Loading)
    val experienceState: StateFlow<FireStoreResult<List<Experience>>> = _experienceState

    fun fetchUser() {
        viewModelScope.launch {
            _experienceState.value = FireStoreResult.Loading
            val result = repository.getExperienceList()
            _experienceState.value = result
        }
    }
}