package com.jay.myportfollio.model.datamodel

data class Profile(
    val name: String? = "",
    val email: String? = "",
    val github: String? = "",
    val linkedin: String? = "",
    val open_to_work: Int? = 0,
    val education: String? = "",
    val phone: String? = "",
    val current_location: String? = "",
    val birth_date: Long? = 0L,
    val role: String? = "",
    val total_experience: String? = ""
)
