package com.jay.myportfollio.model.datamodel

enum class OPW {
    HAVE_JOB,
    FINDING_JOB
}

fun opwEnumToString(opw: Int):String {
    return when(opw){
        0 -> "Working At"
        else -> "Open to work"
    }
}
