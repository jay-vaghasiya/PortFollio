package com.jay.myportfollio.view.screen.landing

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.jay.myportfollio.R
import com.jay.myportfollio.model.datamodel.AboutMe
import com.jay.myportfollio.model.datamodel.ContactMe
import com.jay.myportfollio.model.datamodel.DataProfile
import com.jay.myportfollio.model.datamodel.KnowMyWork
import com.jay.myportfollio.model.datamodel.Result
import com.jay.myportfollio.model.datamodel.WhereIAm
import com.jay.myportfollio.ui.theme.BBlue
import com.jay.myportfollio.ui.theme.GrayBlue
import com.jay.myportfollio.ui.theme.NavyBlue
import com.jay.myportfollio.ui.theme.Pink
import com.jay.myportfollio.ui.theme.skyBlue
import com.jay.myportfollio.ui.theme.skyLightBlue
import com.jay.myportfollio.utils.Constant.list
import com.jay.myportfollio.utils.StrawFordFont
import com.jay.myportfollio.view.screen.component.ErrorState
import com.jay.myportfollio.view.screen.component.LoadingState
import com.jay.myportfollio.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LandingScreen(navController: NavHostController) {
    val viewmodel: ProfileViewModel = koinViewModel()
    val userState by viewmodel.profileState.collectAsState()


    // Trigger data fetching
    LaunchedEffect(Unit) {
        viewmodel.fetchUser()
    }

    AnimatedContent(
        targetState = userState,
        transitionSpec = {
            fadeIn(animationSpec = tween(300)) togetherWith fadeOut(animationSpec = tween(300))
        }, label = "contentAnimation"
    ) { targetState ->
        when (targetState) {
            is Result.Loading -> {
                LoadingState(modifier = Modifier.fillMaxSize())
            }

            is Result.Success -> {
                val user = targetState.data

                FirebaseCrashlytics.getInstance().setUserId(user.email ?: "unknown")
                FirebaseCrashlytics.getInstance().setCustomKey("username", user.name ?: "unknown")
                FirebaseCrashlytics.getInstance().setCustomKey("role", user.role ?: "unknown")

                ProfileContent(user, navController)
            }

            is Result.Error -> {
                val error = targetState.exception.message
                error?.let {
                    FirebaseCrashlytics.getInstance().log("LandingScreen ErrorState: $it")
                    FirebaseCrashlytics.getInstance().recordException(targetState.exception)
                    ErrorState(modifier = Modifier.fillMaxSize(), it)
                }
            }
        }
    }


}

@Composable
fun ProfileContent(user: DataProfile, navController: NavHostController) {
    val backgroundBrush = remember {
        Brush.verticalGradient(listOf(skyBlue, skyLightBlue, Color.White))
    }
    var isNavigating by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundBrush)
                .padding(top = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.Start,
            ) {

                Text(
                    text = user.name.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    fontFamily = StrawFordFont.FontFamily,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge,
                    color = NavyBlue
                )
                Text(
                    text = user.role.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    fontFamily = StrawFordFont.FontFamily,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    color = GrayBlue
                )
                Image(
                    painter = painterResource(R.drawable.image),
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(
                            RoundedCornerShape(24.dp)
                        ),
                    alignment = Alignment.Center,
                    contentDescription = "Profile Photo",
                    contentScale = ContentScale.Crop
                )
            }
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.elevatedCardElevation(24.dp),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            Text(
                                text = "Can i help you?",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                fontFamily = StrawFordFont.FontFamily,
                                style = MaterialTheme.typography.titleSmall,
                                color = BBlue
                            )
                            Text(
                                text = "Let's work?",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = StrawFordFont.FontFamily,
                                style = MaterialTheme.typography.titleMedium,
                                color = BBlue
                            )
                        }
                        ElevatedButton(
                            onClick = {
                                if (!isNavigating) {
                                    isNavigating = true
                                    FirebaseCrashlytics.getInstance()
                                        .log("Contact button clicked by ${user.email}")
                                    navController.navigate(
                                        ContactMe(
                                            user.email.orEmpty(),
                                            user.phone.orEmpty()
                                        )
                                    )
                                }
                            }, colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = Pink, contentColor = Color.White
                            ), shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                text = "Contact me",
                                modifier = Modifier.padding(vertical = 4.dp),
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = StrawFordFont.FontFamily,
                                style = MaterialTheme.typography.titleMedium
                            )


                        }
                    }

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                    ) {
                        itemsIndexed(list) { index, item ->
                            Column(
                                modifier = Modifier
                                    .size(120.dp)
                                    .padding(end = 8.dp)
                                    .background(
                                        color = item.color,
                                        shape = RoundedCornerShape(24.dp)
                                    )
                                    .clip(
                                        RoundedCornerShape(24.dp)
                                    )
                                    .clickable(enabled = !isNavigating) {
                                        isNavigating = true
                                        FirebaseCrashlytics
                                            .getInstance()
                                            .log("Profile option clicked - index $index")

                                        when (index) {
                                            0 -> {
                                                navController.navigate(
                                                    KnowMyWork
                                                )
                                            }

                                            1 -> {
                                                navController.navigate(AboutMe)
                                            }

                                            else -> {
                                                navController.navigate(
                                                    WhereIAm(
                                                        user.linkedin.toString(),
                                                        user.github.toString(),
                                                        user.email.toString()
                                                    )
                                                )
                                            }
                                        }
                                    }
                            ) {
                                Icon(
                                    modifier = Modifier.padding(16.dp),
                                    imageVector = item.icon,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                                Text(
                                    text = item.label,
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp, vertical = 4.dp),
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = StrawFordFont.FontFamily,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = Color.White
                                )

                            }
                        }

                    }


                }
            }
        }
}
