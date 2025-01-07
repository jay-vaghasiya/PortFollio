package com.jay.myportfollio.model.datamodel

data class Project(
    val name :String? = "",
    val description :String? = "",
    val skill :List<String> = emptyList(),
)
