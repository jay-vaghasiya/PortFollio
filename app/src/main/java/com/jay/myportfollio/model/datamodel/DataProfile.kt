package com.jay.myportfollio.model.datamodel

import kotlinx.serialization.Serializable

@Serializable
data class DataProfile(
    val name: String? = "",
    val email: String? = "",
    val github: String? = "",
    val cgpa: String? = "",
    val stream: String? = "",
    val institute: String? = "",
    val summary: String? = "",
    val linkedin: String? = "",
    val education: String? = "",
    val phone: String? = "",
    val current_location: String? = "",
    val role: String? = "",
    val total_experience: String? = ""
)
