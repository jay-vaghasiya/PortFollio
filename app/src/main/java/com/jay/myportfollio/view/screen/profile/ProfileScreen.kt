package com.jay.myportfollio.view.screen.profile

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContactMail
import androidx.compose.material.icons.outlined.Fitbit
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Link
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.jay.myportfollio.R
import com.jay.myportfollio.model.datamodel.FireStoreResult
import com.jay.myportfollio.model.datamodel.Profile
import com.jay.myportfollio.model.datamodel.Screen
import com.jay.myportfollio.ui.theme.BBlue
import com.jay.myportfollio.ui.theme.BlackGray
import com.jay.myportfollio.ui.theme.BlueGray
import com.jay.myportfollio.ui.theme.BlueLight
import com.jay.myportfollio.ui.theme.DarkGray
import com.jay.myportfollio.ui.theme.GrayBlue
import com.jay.myportfollio.ui.theme.LightBlue
import com.jay.myportfollio.ui.theme.NavyBlue
import com.jay.myportfollio.ui.theme.OrangeRed
import com.jay.myportfollio.ui.theme.Pink
import com.jay.myportfollio.ui.theme.Pink50
import com.jay.myportfollio.utils.PulseLoading
import com.jay.myportfollio.utils.StrawFordFont
import com.jay.myportfollio.utils.sendMail
import com.jay.myportfollio.viewmodel.ProfileViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(navController: NavHostController) {
    val viewmodel: ProfileViewModel = koinViewModel()   
    val userState by viewmodel.profileState.collectAsState()


    // Trigger data fetching
    LaunchedEffect(Unit) {
        delay(1000)
        viewmodel.fetchUser()
    }

    AnimatedContent(
        targetState = userState,
        transitionSpec = {
            fadeIn(animationSpec = tween(300)) togetherWith fadeOut(animationSpec = tween(300))
        }, label = "contentAnimation"
    ) { targetState ->
        when (targetState) {
            is FireStoreResult.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        ,
                    contentAlignment = Alignment.Center
                ) {
                    PulseLoading(color = OrangeRed, modifier = Modifier.size(120.dp))
                }
            }

            is FireStoreResult.Success -> {
                val user = targetState.data
                ProfileContent(user,navController)
            }

            is FireStoreResult.Error -> {
                val error = targetState.exception.message
                Text(
                    text = "Error: $error",
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red),
                    color = Color.White
                )
            }
        }
    }


}

data class ContactItem(val icon: ImageVector, val label: String, val color: Color)

@Composable
fun ProfileContent(user: Profile,navController: NavHostController) {


    val list = listOf(
        ContactItem(Icons.Outlined.Work, "know my\n work", Pink50),
        ContactItem(Icons.Outlined.ContactMail, "about\n me", BlueLight),
        ContactItem(Icons.Outlined.Fitbit, "where\n i am", BlueGray),
    )

        Box(
            modifier = Modifier
                .fillMaxSize()
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
                    painter = painterResource(R.drawable.iv_profile_photo),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .clip(
                            RoundedCornerShape(32.dp)
                        ),
                    alignment = Alignment.TopCenter,
                    contentDescription = "Profile Photo",
                    contentScale = ContentScale.Fit
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
                            color = GrayBlue
                        )
                            Text(
                                text = "Let's work?",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = StrawFordFont.FontFamily,
                                style = MaterialTheme.typography.titleMedium,
                                color = GrayBlue
                            )
                    }
                        ElevatedButton(
                            onClick = {

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
                        modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                    ) {
                        itemsIndexed(list) { index, item ->
                            Column(
                                modifier = Modifier.size(120.dp)
                                    .padding(end = 8.dp)
                                    .background(
                                        color = item.color,
                                        shape = RoundedCornerShape(24.dp)
                                    )
                                    .clip(
                                        RoundedCornerShape(24.dp)
                                    )
                                    .clickable {
                                        when (index) {
                                            0 -> {
                                                navController.navigate(Screen.Experience.route)
                                            }
                                            1 -> {
                                                navController.navigate(Screen.Skills.route)
                                            }
                                            else -> {
                                                navController.navigate(Screen.Project.route)
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


@Composable
fun MyWork(user: Profile) {
    val context = LocalContext.current

    val linkedInIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(user.linkedin)) }
    val githubIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(user.github)) }

    //Email
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(
                color = BBlue
            )
            .clickable {
                context.sendMail(user.email.toString())
            }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, top = 12.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Email,
                            tint = Color.White,
                            modifier = Modifier
                                .size(42.dp)
                                .shadow(elevation = 32.dp, RoundedCornerShape(12.dp)),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.padding(top = 8.dp))
                        Text(
                            text = "Email",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            fontFamily = StrawFordFont.FontFamily,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                        Text(
                            text = user.email.toString(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            fontFamily = StrawFordFont.FontFamily,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White
                        )

                    }

                    Icon(
                        imageVector = Icons.Rounded.Link,
                        tint = Color.White,
                        modifier = Modifier.shadow(elevation = 32.dp, RoundedCornerShape(12.dp)),
                        contentDescription = null
                    )
                }

            }
        }
    }

    //LinkedIn

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .clip(RoundedCornerShape(32.dp))
        .background(
            color = LightBlue,
        )
        .clickable {
            context.startActivity(linkedInIntent)
        }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 12.dp, bottom = 16.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_linkdedin),
                        tint = Color.White,
                        modifier = Modifier.size(42.dp),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    Text(
                        text = "LinkedIn",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        fontFamily = StrawFordFont.FontFamily,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )


                }
                Icon(
                    imageVector = Icons.Rounded.Link,
                    tint = Color.White,
                    modifier = Modifier.shadow(elevation = 32.dp, RoundedCornerShape(12.dp)),
                    contentDescription = null
                )
            }

        }
    }

    //Github
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .clip(RoundedCornerShape(32.dp))
        .background(
            brush = Brush.verticalGradient(
                listOf(DarkGray, BlackGray),
            )
        )
        .clickable {
            context.startActivity(githubIntent)
        }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 12.dp, bottom = 16.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_github),
                        tint = Color.White,
                        modifier = Modifier.size(42.dp),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    Text(
                        text = "Github",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        fontFamily = StrawFordFont.FontFamily,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Text(
                        text = "@jay-vaghasiya",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        fontFamily = StrawFordFont.FontFamily,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White
                    )


                }
                Icon(
                    imageVector = Icons.Rounded.Link,
                    tint = Color.White,
                    modifier = Modifier.shadow(elevation = 32.dp, RoundedCornerShape(12.dp)),
                    contentDescription = null
                )
            }

        }
    }
}