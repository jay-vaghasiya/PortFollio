package com.jay.myportfollio.model.datamodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Dataset
import androidx.compose.material.icons.rounded.DeveloperBoard
import androidx.compose.material.icons.rounded.Domain
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.WorkspacePremium
import androidx.compose.ui.graphics.vector.ImageVector
import com.jay.myportfollio.utils.Constant

sealed class Screen(val route: String) {
    object Profile :
        Screen(
            route = Constant.PROFILE_SCREEN,

        )

    object Skills :
        Screen(
            route = Constant.SKILL_SCREEN,

        )

    object Experience :
        Screen(
            route = Constant.EXPERIENCE_SCREEN,

        )

    object Project :
        Screen(
            route = Constant.PROJECTS_SCREEN,
        )
}
