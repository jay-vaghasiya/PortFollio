package com.jay.myportfollio.model.datamodel

import kotlinx.serialization.Serializable

@Serializable
data class DataExperience(
    val employer_name: String = "",
    val wfh: Int = 0,
    val id: Int = 0,
    val location: String = "",
    val role: String = "",
    val start_timeline: String = "",
    val end_timeline: String = "",
    val details: List<Details> = emptyList()
)