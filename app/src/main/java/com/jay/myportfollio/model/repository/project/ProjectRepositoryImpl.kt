package com.jay.myportfollio.model.repository.project

import com.google.firebase.firestore.FirebaseFirestore
import com.jay.myportfollio.model.datamodel.Project
import com.jay.myportfollio.model.datamodel.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProjectRepositoryImpl : ProjectRepository {
    private val firestore = FirebaseFirestore.getInstance()
    override suspend fun getProjectList(): Result<List<Project>> {
        return withContext(Dispatchers.IO) {
            try {
                val snapShot =
                    firestore
                        .collection("projects")
                        .get()
                        .await()

                val projects = snapShot.toObjects(Project::class.java)

                if (projects.isNotEmpty()) {
                    Result.Success(projects)
                } else {
                    Result.Error(Exception("Projects not found"))
                }
            } catch (e: Exception) {
                Result.Error(e)

            }
        }
    }
}