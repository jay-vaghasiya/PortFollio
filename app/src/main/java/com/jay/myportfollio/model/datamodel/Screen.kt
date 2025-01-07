package com.jay.myportfollio.model.datamodel

import kotlinx.serialization.Serializable

@Serializable
data class WhereIAm(
    val linkedin :String,
    val github :String,
    val email :String,
)

@Serializable
object AboutMe

@Serializable
object KnowMyWork

@Serializable
object MyWork

@Serializable
data class ContactMe(
    val email :String,
    val phone :String
)

@Serializable
object Landing
