package com.jay.myportfollio.model.datamodel

import kotlinx.serialization.Serializable

@Serializable
data class DataSkills(
    val list: List<String> = emptyList()
)
