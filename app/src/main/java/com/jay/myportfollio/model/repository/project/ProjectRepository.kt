package com.jay.myportfollio.model.repository.project

import com.jay.myportfollio.model.datamodel.DataExperience
import com.jay.myportfollio.model.datamodel.Project
import com.jay.myportfollio.model.datamodel.Result

interface ProjectRepository {
    suspend fun getProjectList(): Result<List<Project>>
}