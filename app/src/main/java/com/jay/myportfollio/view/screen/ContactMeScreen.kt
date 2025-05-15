package com.jay.myportfollio.view.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Contacts
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.jay.myportfollio.ui.theme.BBlue
import com.jay.myportfollio.ui.theme.Pink
import com.jay.myportfollio.utils.StrawFordFont
import com.jay.myportfollio.utils.sendMail
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ContactMeScreen(email:String,phone:String,navController: NavHostController) {
    val context = LocalContext.current
    val isNavigating = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Pink)
            .padding(top = 24.dp)
    ) {
        IconButton(onClick = {
            isNavigating.value = true
            navController.popBackStack()
        }, modifier = Modifier.padding(8.dp)) {
            Icon(
                Icons.AutoMirrored.Rounded.ArrowBack,
                null,
                modifier = Modifier.size(32.dp),
                tint = Color.White
            )
        }

        Text(
            text = "Contact Me",
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = StrawFordFont.FontFamily,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White
        )

        ElevatedCard(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .weight(1f),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(24.dp),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 12.dp, end = 12.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(32.dp))
                    .background(
                        color = Pink
                    )
                    .clickable(enabled = !isNavigating.value) {
                        context.sendMail(email)
                        scope.launch {
                            delay(1000L)
                            isNavigating.value = false
                        }
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
                                    text = email,
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
                                modifier = Modifier.shadow(
                                    elevation = 32.dp,
                                    RoundedCornerShape(12.dp)
                                ),
                                contentDescription = null
                            )
                        }

                    }
                }
            }
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phone")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 12.dp, end = 12.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(32.dp))
                    .background(
                        color = Pink
                    )
                    .clickable(enabled = !isNavigating.value) {
                        isNavigating.value = true
                        context.startActivity(intent)
                        scope.launch {
                            delay(1000L)
                            isNavigating.value = false
                        }

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
                                    imageVector = Icons.Rounded.Contacts,
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(42.dp)
                                        .shadow(elevation = 32.dp, RoundedCornerShape(12.dp)),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.padding(top = 8.dp))
                                Text(
                                    text = "Contact no",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    fontFamily = StrawFordFont.FontFamily,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                                )
                                Text(
                                    text = phone,
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
                                modifier = Modifier.shadow(
                                    elevation = 32.dp,
                                    RoundedCornerShape(12.dp)
                                ),
                                contentDescription = null
                            )
                        }

                    }
                }
            }


        }
    }
}