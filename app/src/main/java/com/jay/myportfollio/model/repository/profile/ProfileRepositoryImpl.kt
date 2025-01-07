package com.jay.myportfollio.model.repository.profile

import com.google.firebase.firestore.FirebaseFirestore
import com.jay.myportfollio.model.datamodel.Result
import com.jay.myportfollio.model.datamodel.DataProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProfileRepositoryImpl : ProfileRepository {
    private val firestore = FirebaseFirestore.getInstance()
    override suspend fun getProfileDataFromRemote(): Result<DataProfile> {
        return withContext(Dispatchers.IO) {
            try {
                val snapShot =
                    firestore
                        .collection("profile")
                        .document("jaykumar_vaghasiya")
                        .get()
                        .await()

                val profile = snapShot.toObject(DataProfile::class.java)

                if (profile != null) {
                    Result.Success(profile)
                } else {
                    Result.Error(Exception("User not found"))
                }
            } catch (e: Exception) {
                Result.Error(e)

            }
        }
    }
}