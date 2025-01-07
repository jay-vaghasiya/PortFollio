package com.jay.myportfollio.view.screen.where_i_am

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
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jay.myportfollio.R
import com.jay.myportfollio.ui.theme.BBlue
import com.jay.myportfollio.ui.theme.BlackGray
import com.jay.myportfollio.ui.theme.BlueGray
import com.jay.myportfollio.ui.theme.BlueLight
import com.jay.myportfollio.ui.theme.DarkGray
import com.jay.myportfollio.ui.theme.LightBlue
import com.jay.myportfollio.utils.StrawFordFont
import com.jay.myportfollio.utils.sendMail

@Composable
fun WhereIAmScreen(
    linkedLn: String,
    github: String,
    email: String,
    navController: NavHostController) {

    val context = LocalContext.current
    val linkedInIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(linkedLn)) }
    val githubIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(github)) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlueGray)
            .padding(top = 24.dp)
    ) {

        IconButton(onClick = {
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
            text = "Where I am",
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

            //Email
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
                        color = BBlue
                    )
                    .clickable {
                        context.sendMail(email)
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

            //LinkedIn

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
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
                            modifier = Modifier.shadow(
                                elevation = 32.dp,
                                RoundedCornerShape(12.dp)
                            ),
                            contentDescription = null
                        )
                    }

                }
            }

            //Github
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
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