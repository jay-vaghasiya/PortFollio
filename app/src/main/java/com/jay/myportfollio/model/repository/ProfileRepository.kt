package com.jay.myportfollio.model.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.jay.myportfollio.model.datamodel.FireStoreResult
import com.jay.myportfollio.model.datamodel.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

interface ProfileRepository {

    suspend fun getProfileDataFromRemote(): FireStoreResult<Profile>

}

class ProfileRepositoryImpl : ProfileRepository {
    private val firestore = FirebaseFirestore.getInstance()
    override suspend fun getProfileDataFromRemote(): FireStoreResult<Profile> {
        return withContext(Dispatchers.IO) {
            try {
                val snapShot =
                    firestore
                        .collection("profile")
                        .document("jaykumar_vaghasiya")
                        .get()
                        .await()

                val profile = snapShot.toObject(Profile::class.java)

                if (profile != null) {
                    FireStoreResult.Success(profile)
                } else {
                    FireStoreResult.Error(Exception("User not found"))
                }
            } catch (e: Exception) {
                FireStoreResult.Error(e)

            }
        }
    }
}