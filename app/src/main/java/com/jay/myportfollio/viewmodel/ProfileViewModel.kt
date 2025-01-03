package com.jay.myportfollio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jay.myportfollio.model.datamodel.FireStoreResult
import com.jay.myportfollio.model.datamodel.Profile
import com.jay.myportfollio.model.repository.profile.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    private val _profileState = MutableStateFlow<FireStoreResult<Profile>>(FireStoreResult.Loading)
    val profileState: StateFlow<FireStoreResult<Profile>> = _profileState

    fun fetchUser() {
        viewModelScope.launch {
            _profileState.value = FireStoreResult.Loading
            val result = repository.getProfileDataFromRemote()
            _profileState.value = result
        }
    }
}