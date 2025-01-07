package com.jay.myportfollio.model.repository.skills

import com.jay.myportfollio.model.datamodel.DataSkills
import com.jay.myportfollio.model.datamodel.Project
import com.jay.myportfollio.model.datamodel.Result

interface SkillsRepository {
    suspend fun getSkillsList(): Result<List<DataSkills>>
}