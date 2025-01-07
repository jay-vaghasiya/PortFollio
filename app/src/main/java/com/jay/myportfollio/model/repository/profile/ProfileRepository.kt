package com.jay.myportfollio.model.repository.profile

import com.jay.myportfollio.model.datamodel.Result
import com.jay.myportfollio.model.datamodel.DataProfile

interface ProfileRepository {

    suspend fun getProfileDataFromRemote(): Result<DataProfile>

}

