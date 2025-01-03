package com.jay.myportfollio.model.repository.experience

import com.jay.myportfollio.model.datamodel.Experience
import com.jay.myportfollio.model.datamodel.FireStoreResult

interface ExperienceRepository {
    suspend fun getExperienceList(): FireStoreResult<List<Experience>>
}