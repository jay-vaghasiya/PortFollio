package com.jay.myportfollio.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContactMail
import androidx.compose.material.icons.outlined.Fitbit
import androidx.compose.material.icons.outlined.Work
import com.jay.myportfollio.model.datamodel.ContactItem
import com.jay.myportfollio.ui.theme.BlueGray
import com.jay.myportfollio.ui.theme.BlueLight
import com.jay.myportfollio.ui.theme.Pink50

object Constant {
    // Label
    const val PROFILE = "Profile"
    const val SKILL = "Skill"
    const val CERTIFICATE = "Certificate"
    const val EXPERIENCE = "Experience"
    const val PROJECTS = "Project"

    // Screen
    const val PROFILE_SCREEN = "profile_screen"
    const val SKILL_SCREEN = "skill_screen"
    const val CERTIFICATE_SCREEN = "certificate_screen"
    const val EXPERIENCE_SCREEN = "experience_screen"
    const val PROJECTS_SCREEN = "project_screen"

    //Padding
    const val PADDING_OUTER_CIRCLE = 0.15f
    const val PADDING_INNER_CIRCLE = 0.3f
    const val POSITION_OFFSET_OUTER_CIRCLE = 90f
    const val POSITION_OFFSET_INNER_CIRCLE = 135f


    val list = listOf(
        ContactItem(Icons.Outlined.Work, "know my\n work", Pink50),
        ContactItem(Icons.Outlined.ContactMail, "about\n me", BlueLight),
        ContactItem(Icons.Outlined.Fitbit, "where\n i am", BlueGray)
    )
}