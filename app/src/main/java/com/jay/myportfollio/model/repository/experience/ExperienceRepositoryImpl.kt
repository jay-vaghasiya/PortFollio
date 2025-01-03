package com.jay.myportfollio.model.repository.experience

import com.google.firebase.firestore.FirebaseFirestore
import com.jay.myportfollio.model.datamodel.Experience
import com.jay.myportfollio.model.datamodel.FireStoreResult
import com.jay.myportfollio.model.datamodel.Profile
import com.jay.myportfollio.model.repository.profile.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ExperienceRepositoryImpl : ExperienceRepository {
    private val firestore = FirebaseFirestore.getInstance()
    override suspend fun getExperienceList(): FireStoreResult<List<Experience>> {
        return withContext(Dispatchers.IO) {
            try {
                val snapShot =
                    firestore
                        .collection("experience")
                        .get()
                        .await()

                val experience = snapShot.toObjects(Experience::class.java)

                if (experience.isNotEmpty()) {
                    FireStoreResult.Success(experience)
                } else {
                    FireStoreResult.Error(Exception("User not found"))
                }
            } catch (e: Exception) {
                FireStoreResult.Error(e)

            }
        }
    }
}