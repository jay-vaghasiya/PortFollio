package com.jay.myportfollio.model.datamodel

import kotlinx.serialization.Serializable

@Serializable
data class Details(
    val points: List<String> = emptyList()
)
