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
                val snapShot = firestore.collection("experience").get().await()
                val experiences = mutableListOf<DataExperience>()

                for (doc in snapShot.documents) {
                    val experience = doc.toObject(DataExperience::class.java)
                    val detailsSnapshot = doc.reference.collection("details").get().await()
                    val detailsList = detailsSnapshot.toObjects(Details::class.java)

                    if (experience != null) {
                        experiences.add(experience.copy(details = detailsList))
                    }
                }

                return@withContext if (experiences.isNotEmpty()) {
                    Result.Success(experiences)
                } else {
                    Result.Error(Exception("No experiences found"))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }




}