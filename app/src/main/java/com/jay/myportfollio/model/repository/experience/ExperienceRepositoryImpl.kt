package com.jay.myportfollio.model.repository.experience

import com.google.firebase.firestore.FirebaseFirestore
import com.jay.myportfollio.model.datamodel.DataExperience
import com.jay.myportfollio.model.datamodel.Details
import com.jay.myportfollio.model.datamodel.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ExperienceRepositoryImpl : ExperienceRepository {
    private val firestore = FirebaseFirestore.getInstance()
    override suspend fun getExperienceList(): Result<List<DataExperience>> {
        return withContext(Dispatchers.IO) {
            try {
                val snapShot =
                    firestore
                        .collection("experience")
                        .get()
                        .await()

                val experience = snapShot.toObjects(DataExperience::class.java)

                if (experience.isNotEmpty()) {
                    Result.Success(experience)
                } else {
                    Result.Error(Exception("User not found"))
                }
            } catch (e: Exception) {
                Result.Error(e)

            }
        }
    }

    override suspend fun getExperienceBulletPoints(): Result<Details> {
        return withContext(Dispatchers.IO) {
            try {
                val experienceSnapshot = firestore
                    .collection("experience")
                    .get()
                    .await()

                val details = experienceSnapshot.documents
                    .mapNotNull { document ->
                        val detailsSnapshot = document.reference
                            .collection("details")
                            .document("1")
                            .get()
                            .await()

                        detailsSnapshot.toObject(Details::class.java)
                    }
                    .firstOrNull()

                return@withContext if (details != null) {
                    Result.Success(details)
                } else {
                    Result.Error(Exception("Details document '1' not found"))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }


}