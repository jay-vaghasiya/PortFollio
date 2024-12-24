package com.jay.myportfollio.model.datamodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Dataset
import androidx.compose.material.icons.rounded.DeveloperBoard
import androidx.compose.material.icons.rounded.Domain
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.WorkspacePremium
import androidx.compose.ui.graphics.vector.ImageVector
import com.jay.myportfollio.utils.Constant

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Profile :
        Screen(
            route = Constant.PROFILE_SCREEN,
            label = Constant.PROFILE,
            icon = Icons.Rounded.Person
        )

    object Skills :
        Screen(
            route = Constant.SKILL_SCREEN,
            label = Constant.SKILL,
            icon = Icons.Rounded.Dataset
        )

//    object Certification : Screen(
//        route = Constant.CERTIFICATE_SCREEN,
//        label = Constant.CERTIFICATE,
//        icon = Icons.Rounded.WorkspacePremium
//    )

    object Experience :
        Screen(
            route = Constant.EXPERIENCE_SCREEN,
            label = Constant.EXPERIENCE,
            icon = Icons.Rounded.Domain
        )

    object Project :
        Screen(
            route = Constant.PROJECTS_SCREEN,
            label = Constant.PROJECTS,
            icon = Icons.Rounded.DeveloperBoard
        )
}
