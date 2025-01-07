package com.jay.myportfollio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jay.myportfollio.model.datamodel.DataSkills
import com.jay.myportfollio.model.datamodel.Result
import com.jay.myportfollio.model.repository.skills.SkillsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SkillsViewModel(val repository: SkillsRepository) : ViewModel() {
    private val _skillsState =
        MutableStateFlow<Result<List<DataSkills>>>(Result.Loading)
    val skillsState: StateFlow<Result<List<DataSkills>>> = _skillsState

    fun fetchSkills() {
        viewModelScope.launch {
            _skillsState.value = Result.Loading
            val result = repository.getSkillsList()
            _skillsState.value = result
        }
    }
}