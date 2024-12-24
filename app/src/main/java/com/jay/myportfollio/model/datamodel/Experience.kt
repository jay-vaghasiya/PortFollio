package com.jay.myportfollio.model.datamodel

data class Experience(
    val employer_name: String,
    val wfh: String,
    val location: String,
    val role: String,
    val start_timeline: String,
    val end_timeline: String,
    val duration: String,
    val details: List<Details>
)
