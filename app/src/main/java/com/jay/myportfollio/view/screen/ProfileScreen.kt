package com.jay.myportfollio.view.screen

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cake
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jay.myportfollio.R
import com.jay.myportfollio.model.datamodel.FireStoreResult
import com.jay.myportfollio.model.datamodel.Profile
import com.jay.myportfollio.ui.theme.BBlue
import com.jay.myportfollio.ui.theme.BlackBlue
import com.jay.myportfollio.ui.theme.Blue
import com.jay.myportfollio.ui.theme.GrayBlue
import com.jay.myportfollio.ui.theme.LightBlue
import com.jay.myportfollio.ui.theme.NavyBlue
import com.jay.myportfollio.ui.theme.OrangeRed
import com.jay.myportfollio.utils.PulseLoading
import com.jay.myportfollio.utils.StrawFordFont
import com.jay.myportfollio.utils.convertTimestampToDate
import com.jay.myportfollio.viewmodel.ProfileViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen() {
    val viewmodel: ProfileViewModel = koinViewModel()
    val userState by viewmodel.profileState.collectAsState()

    // Trigger data fetching
    LaunchedEffect(Unit) {
        delay(3000)
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
                        .background(
                            brush = Brush.linearGradient(
                                0f to OrangeRed,
                                0.2f to Blue,
                                1f to NavyBlue,
                            )
                        )
                        ,
                    contentAlignment = Alignment.Center
                ) {
                    PulseLoading(color = OrangeRed, modifier = Modifier.size(120.dp))
                }
            }

            is FireStoreResult.Success -> {
                val user = targetState.data
                ProfileContent(user)
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

@Composable
fun ProfileContent(user: Profile) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    0f to OrangeRed,
                    0.2f to Blue,
                    1f to NavyBlue,
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.iv_profile_photo),
            modifier = Modifier
                .size(164.dp)
                .clip(
                    RoundedCornerShape(32.dp)
                ),
            alignment = Alignment.TopCenter,
            contentDescription = "Profile Photo",
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(GrayBlue, BlackBlue)
                    ),
                    shape = RoundedCornerShape(32.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = user.name.toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    fontFamily = StrawFordFont.FontFamily,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                Text(
                    text = "\uD83D\uDCCD ${user.role}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    fontFamily = StrawFordFont.FontFamily,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White
                )
                Text(
                    text = "\uD83D\uDC68\u200D\uD83D\uDCBB ${user.total_experience} years experience ",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    fontFamily = StrawFordFont.FontFamily,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White
                )
                Text(
                    text = "\uD83D\uDCBB ${user.education} ",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    fontFamily = StrawFordFont.FontFamily,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, top = 12.dp, bottom = 16.dp),

                    ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.6f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.LocationOn,
                            contentDescription = null,
                            tint = OrangeRed
                        )
                        Text(
                            text = user.current_location.toString(),
                            modifier = Modifier.padding(start = 8.dp),
                            fontFamily = StrawFordFont.FontFamily,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White
                        )

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Cake,
                            contentDescription = null,
                            tint = OrangeRed
                        )
                        Log.d("BIRTH_DATE", user.birth_date.toString())
                        Text(
                            text = convertTimestampToDate(user.birth_date ?: 0),
                            modifier = Modifier.padding(start = 8.dp),
                            fontFamily = StrawFordFont.FontFamily,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White
                        )

                    }

                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = BBlue,
                        shape = RoundedCornerShape(32.dp)
                    )
            ) {
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
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)) {
                            Icon(
                                imageVector = Icons.Rounded.Email,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(36.dp)
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .background(
                    color = LightBlue,
                    shape = RoundedCornerShape(32.dp)
                )
        ) {
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
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)) {
                        Icon(
                            painter = painterResource(R.drawable.ic_linkdedin),
                            tint = Color.White,
                            modifier = Modifier
                                .size(52.dp),
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


}
