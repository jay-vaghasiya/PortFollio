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
                        .collection(PROJECTS)
                        .get()
                        .await()

                val projects = snapShot.toObjects(Project::class.java)

                if (projects.isNotEmpty()) {
                    Result.Success(projects)
                } else {
                    Result.Error(Exception(ERROR_FETCHING_DATA))
                }
            } catch (e: Exception) {
                Result.Error(e)

            }
        }
    }
    companion object{

        const val ERROR_FETCHING_DATA = "No Projects Found"
        const val PROJECTS = "projects"

    }
}