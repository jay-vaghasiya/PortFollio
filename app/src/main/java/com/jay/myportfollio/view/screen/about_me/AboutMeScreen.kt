package com.jay.myportfollio.view.screen.about_me

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Cases
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jay.myportfollio.ui.theme.BlueLight
import com.jay.myportfollio.utils.StrawFordFont
import com.jay.myportfollio.view.components.GlassMorphicBox
import com.jay.myportfollio.view.screen.about_me.page.AcademicPage
import com.jay.myportfollio.view.screen.about_me.page.CertificationPage
import com.jay.myportfollio.view.screen.about_me.page.ExperiencePage
import com.jay.myportfollio.view.screen.about_me.page.ProfileSummary
import kotlinx.coroutines.launch

data class AboutItems(val title: String, val icon: ImageVector)
@Composable
fun AboutMeScreen(navController: NavHostController) {
    val pagerState = rememberPagerState(pageCount = { 4 })
    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlueLight)
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
            text = "About me",
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = StrawFordFont.FontFamily,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White
        )
        val list = listOf(
            AboutItems(title = "Hello", icon = Icons.Rounded.Person),
            AboutItems(title = "Experiences", icon = Icons.Rounded.Cases),
            AboutItems(title = "Academic", icon = Icons.Rounded.Book),
            AboutItems(title = "Certification", icon = Icons.Rounded.StarBorder),
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            itemsIndexed(list) { index, item ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val iconTintColor by animateColorAsState(
                        targetValue = if (pagerState.currentPage == index) Color.White else Color.DarkGray
                    )
                    val textColor by animateColorAsState(
                        targetValue = if (pagerState.currentPage == index) Color.White else Color.DarkGray
                    )
                    GlassMorphicBox(onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }) {

                        Icon(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(24.dp),
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = iconTintColor
                        )
                    }
                    Text(
                        modifier = Modifier,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        text = item.title,
                        color = textColor
                    )
                }

            }
        }

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

            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
                when (page) {
                    0 -> {
                        ProfileSummary(modifier = Modifier.fillMaxSize())
                    }
                    1 -> {
                        ExperiencePage(modifier = Modifier.fillMaxSize())
                    }
                    2 -> {
                        AcademicPage(modifier = Modifier.fillMaxSize())
                    }
                    else -> {
                        CertificationPage(modifier = Modifier.fillMaxSize())
                    }
                }

            }


        }
    }
}

