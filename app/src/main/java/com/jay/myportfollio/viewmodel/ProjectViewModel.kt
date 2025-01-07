package com.jay.myportfollio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jay.myportfollio.model.datamodel.DataExperience
import com.jay.myportfollio.model.datamodel.Project
import com.jay.myportfollio.model.datamodel.Result
import com.jay.myportfollio.model.repository.project.ProjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProjectViewModel(val repository: ProjectRepository) : ViewModel() {
    private val _projectState =
        MutableStateFlow<Result<List<Project>>>(Result.Loading)
    val projectState: StateFlow<Result<List<Project>>> = _projectState

    fun fetchProjects() {
        viewModelScope.launch {
            _projectState.value = Result.Loading
            val result = repository.getProjectList()
            _projectState.value = result
        }
    }
}