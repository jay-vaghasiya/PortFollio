package com.jay.myportfollio.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.toRoute
import com.jay.myportfollio.model.datamodel.AboutMe
import com.jay.myportfollio.model.datamodel.ContactMe
import com.jay.myportfollio.model.datamodel.KnowMyWork
import com.jay.myportfollio.model.datamodel.Landing
import com.jay.myportfollio.model.datamodel.MyWork
import com.jay.myportfollio.model.datamodel.WhereIAm
import com.jay.myportfollio.observer.ConnectivityObserver
import com.jay.myportfollio.observer.NetworkConnectivityObserver
import com.jay.myportfollio.ui.theme.MyPortFollioTheme
import com.jay.myportfollio.ui.theme.skyBlue
import com.jay.myportfollio.ui.theme.skyLightBlue
import com.jay.myportfollio.view.screen.ContactMeScreen
import com.jay.myportfollio.view.screen.about_me.AboutMeScreen
import com.jay.myportfollio.view.screen.know_my_work.KnowMyWorkScreen
import com.jay.myportfollio.view.screen.landing.LandingScreen
import com.jay.myportfollio.view.screen.where_i_am.WhereIAmScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        connectivityObserver = NetworkConnectivityObserver(applicationContext)

        setContent {
            MyPortfolioApp(connectivityObserver)
        }
    }

    @Composable
    fun MyPortfolioApp(connectivityObserver: ConnectivityObserver) {
        val navController = rememberNavController()
        val snackbarHostState = remember { SnackbarHostState() }
        val backgroundBrush = remember {
            Brush.verticalGradient(listOf(skyBlue, skyLightBlue, Color.White))
        }

        MyPortFollioTheme {
            Scaffold(
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                modifier = Modifier.fillMaxSize()
            ) {  paddingValues ->
                ConnectivityHandler(connectivityObserver, snackbarHostState)
                AppNavigation(
                    navController = navController,
                    paddingValues = paddingValues,
                    backgroundBrush = backgroundBrush
                )
            }
        }
    }

    @Composable
    fun ConnectivityHandler(
        connectivityObserver: ConnectivityObserver,
        snackbarHostState: SnackbarHostState
    ) {
        // Observe connectivity changes once for the lifetime of the app
        LaunchedEffect(connectivityObserver) {
            connectivityObserver.observe()
                .onEach { status ->
                    snackbarHostState.showSnackbar(message = "Internet is $status")
                }
                .launchIn(this)
        }
    }

    @Composable
    fun AppNavigation(
        navController: NavHostController,
        paddingValues: PaddingValues,
        backgroundBrush: Brush
    ) {
        NavHost(
            navController = navController,
            startDestination = Landing,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(700)
                )
            },

            modifier = Modifier
                .fillMaxSize()
                .background(backgroundBrush)
        ) {
            composable<Landing> {
                LandingScreen(navController)
            }
            composable<AboutMe> {
                AboutMeScreen(navController)
            }
            composable<WhereIAm> {
                val args = it.toRoute<WhereIAm>()
                WhereIAmScreen(args.linkedin, args.github, args.email, navController)
            }
            composable<KnowMyWork> {
                KnowMyWorkScreen(navController)
            }
            composable<ContactMe> {
                val args = it.toRoute<ContactMe>()
                ContactMeScreen(args.email,args.phone,navController)
            }
            composable<MyWork> {

                MyWorkScreen( navController)
            }
        }
    }


}

