package com.jay.myportfollio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jay.myportfollio.model.datamodel.DataExperience
import com.jay.myportfollio.model.datamodel.Result
import com.jay.myportfollio.model.repository.experience.ExperienceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExperienceViewModel(private val repository: ExperienceRepository) : ViewModel() {
    private val _experienceState =
        MutableStateFlow<Result<List<DataExperience>>>(Result.Loading)
    val experienceState: StateFlow<Result<List<DataExperience>>> = _experienceState

    fun fetchUser() {
        viewModelScope.launch {
            _experienceState.value = Result.Loading
            val result = repository.getExperienceList()
            _experienceState.value = result
        }
    }
}