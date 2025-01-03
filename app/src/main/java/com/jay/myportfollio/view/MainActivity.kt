package com.jay.myportfollio.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jay.myportfollio.model.datamodel.Screen
import com.jay.myportfollio.observer.ConnectivityObserver
import com.jay.myportfollio.observer.NetworkConnectivityObserver
import com.jay.myportfollio.ui.theme.MyPortFollioTheme
import com.jay.myportfollio.ui.theme.skyBlue
import com.jay.myportfollio.ui.theme.skyLightBlue
import com.jay.myportfollio.view.screen.experience.ExperienceScreen
import com.jay.myportfollio.view.screen.profile.ProfileScreen
import com.jay.myportfollio.view.screen.project.ProjectScreen
import com.jay.myportfollio.view.screen.skills.SkillsScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyPortFollioTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                connectivityObserver = NetworkConnectivityObserver(applicationContext)
                Scaffold(modifier = Modifier
                    .fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },

                    bottomBar = {
                        HomeScreen(navController)
                    }
                ) {
                    LaunchedEffect(Unit) {
                        connectivityObserver.observe()
                            .onEach { status ->
                                snackbarHostState.showSnackbar(message = "Internet is $status")
                            }
                            .launchIn(this)
                    }
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Profile.route,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    listOf(skyBlue, skyLightBlue, Color.White)
                                )
                            )
                            .padding(it)
                    ) {
                        composable(Screen.Profile.route) {
                            ProfileScreen(navController)
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
//    NavigationBar(containerColor = Blue,
//        contentColor = OrangeRed,
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight(),
//    ) {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
//
//        val items = listOf(
//            Screen.Profile,
//            Screen.Experience,
//            Screen.Skills,
//            Screen.Project
//        )
//
//        items.forEach { screen ->
//            NavigationBarItem(
//                selected = currentRoute == screen.route,
//                onClick = {
//                    if (currentRoute != screen.route) {
//                        navController.navigate(screen.route) {
//                            launchSingleTop = true
//                            restoreState = true
//                            popUpTo(navController.graph.startDestinationId) {
//                                saveState = true
//                            }
//                        }
//                    }
//                },
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = OrangeRed,
//                    selectedTextColor = OrangeRed,
//                    unselectedIconColor = LightOrangeRed,
//                    unselectedTextColor = LightOrangeRed,
//                    indicatorColor = BlackBlue
//                ),
//                icon = {
//                    Icon(
//                        imageVector = screen.icon,
//                        contentDescription = null
//                    )
//                },
//                label = { Text(text = screen.label) },
//            )
//        }
//    }
}


