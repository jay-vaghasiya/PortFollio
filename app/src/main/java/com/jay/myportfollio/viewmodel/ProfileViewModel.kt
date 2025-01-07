package com.jay.myportfollio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jay.myportfollio.model.datamodel.Result
import com.jay.myportfollio.model.datamodel.DataProfile
import com.jay.myportfollio.model.repository.profile.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    private val _profileState = MutableStateFlow<Result<DataProfile>>(Result.Loading)
    val profileState: StateFlow<Result<DataProfile>> = _profileState

    fun fetchUser() {
        viewModelScope.launch {
            _profileState.value = Result.Loading
            val result = repository.getProfileDataFromRemote()
            _profileState.value = result
        }
    }
}