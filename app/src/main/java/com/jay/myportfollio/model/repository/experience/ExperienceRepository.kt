package com.jay.myportfollio.model.repository.experience

import com.jay.myportfollio.model.datamodel.DataExperience
import com.jay.myportfollio.model.datamodel.Details
import com.jay.myportfollio.model.datamodel.Result

interface ExperienceRepository {
    suspend fun getExperienceList(): Result<List<DataExperience>>
}