package com.jay.myportfollio.model.repository.profile

import com.google.firebase.firestore.FirebaseFirestore
import com.jay.myportfollio.model.datamodel.FireStoreResult
import com.jay.myportfollio.model.datamodel.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

interface ProfileRepository {

    suspend fun getProfileDataFromRemote(): FireStoreResult<Profile>

}

