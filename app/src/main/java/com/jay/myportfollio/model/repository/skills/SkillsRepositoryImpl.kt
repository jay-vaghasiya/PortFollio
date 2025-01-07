package com.jay.myportfollio.model.repository.skills

import com.google.firebase.firestore.FirebaseFirestore
import com.jay.myportfollio.model.datamodel.DataSkills
import com.jay.myportfollio.model.datamodel.Project
import com.jay.myportfollio.model.datamodel.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SkillsRepositoryImpl : SkillsRepository {
    private val firestore = FirebaseFirestore.getInstance()
    override suspend fun getSkillsList(): Result<List<DataSkills>> {
            return withContext(Dispatchers.IO) {
                try {
                    val snapShot =
                        firestore
                            .collection("skills")
                            .get()
                            .await()

                    val skills = snapShot.toObjects(DataSkills::class.java)

                    if (skills.isNotEmpty()) {
                        Result.Success(skills)
                    } else {
                        Result.Error(Exception("User not found"))
                    }
                } catch (e: Exception) {
                    Result.Error(e)

                }
            }
        }
    }
