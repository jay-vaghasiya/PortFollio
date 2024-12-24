package com.jay.myportfollio.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jay.myportfollio.model.datamodel.Screen
import com.jay.myportfollio.ui.theme.BlackBlue
import com.jay.myportfollio.ui.theme.Blue
import com.jay.myportfollio.ui.theme.GrayBlue
import com.jay.myportfollio.ui.theme.LightOrangeRed
import com.jay.myportfollio.ui.theme.MyPortFollioTheme
import com.jay.myportfollio.ui.theme.NavyBlue
import com.jay.myportfollio.ui.theme.OrangeRed
import com.jay.myportfollio.view.screen.CertificationScreen
import com.jay.myportfollio.view.screen.ExperienceScreen
import com.jay.myportfollio.view.screen.ProfileScreen
import com.jay.myportfollio.view.screen.ProjectScreen
import com.jay.myportfollio.view.screen.SkillsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyPortFollioTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            0f to OrangeRed,
                            0.2f to Blue,
                            1f to NavyBlue,
                        )
                    ),
                    bottomBar = {
                        HomeScreen(navController)
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Profile.route,
                        modifier = Modifier.padding(it)
                    ) {
                        composable(Screen.Profile.route) {
                            ProfileScreen()
                        }
                        composable(Screen.Experience.route) {
                            ExperienceScreen()
                        }
                        composable(Screen.Skills.route) {
                            SkillsScreen()
                        }
                        composable(Screen.Project.route) {
                            ProjectScreen()
                        }
//                        composable(Screen.Certification.route) {
//                            CertificationScreen()
//                        }
                    }

                }
            }
        }
    }


}





@Composable
fun HomeScreen(navController: NavHostController) {
    NavigationBar(containerColor = Blue,
        contentColor = OrangeRed,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val items = listOf(
            Screen.Profile,
            Screen.Experience,
            Screen.Skills,
            Screen.Project
        )

        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = OrangeRed,
                    selectedTextColor = OrangeRed,
                    unselectedIconColor = LightOrangeRed,
                    unselectedTextColor = LightOrangeRed,
                    indicatorColor = BlackBlue
                ),
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null
                    )
                },
                label = { Text(text = screen.label) },
            )
        }
    }
}


