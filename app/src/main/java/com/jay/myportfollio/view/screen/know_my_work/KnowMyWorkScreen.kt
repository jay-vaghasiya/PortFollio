package com.jay.myportfollio.view.screen.know_my_work

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.RemoveRedEye
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jay.myportfollio.model.datamodel.DataExperience
import com.jay.myportfollio.model.datamodel.Result
import com.jay.myportfollio.ui.theme.Blue
import com.jay.myportfollio.ui.theme.BlueLight
import com.jay.myportfollio.ui.theme.OrangeRed
import com.jay.myportfollio.ui.theme.Pink50
import com.jay.myportfollio.utils.PulseLoading
import com.jay.myportfollio.utils.StrawFordFont
import com.jay.myportfollio.viewmodel.ExperienceViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel


@Composable
fun KnowMyWorkScreen(navController: NavHostController) {
    val viewmodel: ExperienceViewModel = koinViewModel()
    val userState by viewmodel.experienceState.collectAsState()


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
            is Result.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    PulseLoading(color = OrangeRed, modifier = Modifier.size(120.dp))
                }
            }

            is Result.Success -> {
                val user = targetState.data
                Log.wtf("Experience", user.toString())
                ExperienceContent(navController,user)
            }

            is Result.Error -> {
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
fun ExperienceContent(navController: NavHostController,experiences: List<DataExperience>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Pink50)
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
            text = "Know my work",
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BlueLight)
            ) {

                IconButton(onClick = {}, modifier = Modifier.padding(8.dp)) {
                    Icon(
                        Icons.AutoMirrored.Rounded.ArrowBack,
                        null,
                        modifier = Modifier.size(32.dp),
                        tint = Color.White
                    )
                }

                Text(
                    text = "Where I am",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
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
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        itemsIndexed(items = experiences) { index, experience ->
                            ExperienceCard(experience, index)
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun ExperienceCard(experience: DataExperience, index: Int) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(
                color = Blue, shape = RoundedCornerShape(32.dp)
            )
            .clip(
                RoundedCornerShape(32.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 12.dp)
            ) {
                Text(
                    text = experience.employer_name,
                    modifier = Modifier,
                    fontFamily = StrawFordFont.FontFamily,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = experience.location,
                    modifier = Modifier.align(Alignment.Bottom),
                    fontFamily = StrawFordFont.FontFamily,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White
                )
            }

            Text(
                text = experience.role,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 12.dp),
                fontFamily = StrawFordFont.FontFamily,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )

            Text(
                text = experience.start_timeline + " To " + experience.end_timeline,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 12.dp),
                fontFamily = StrawFordFont.FontFamily,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )


            Text(
                text = workFromHome(experience.wfh),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 12.dp),
                fontFamily = StrawFordFont.FontFamily,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                color = OrangeRed,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Show More",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    fontFamily = StrawFordFont.FontFamily,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White
                )
                Icon(
                    contentDescription = null,
                    imageVector = Icons.Rounded.RemoveRedEye,
                    tint = Color.White
                )
            }


        }
    }
}


fun workFromHome(wfh: Int): String {
    return when (wfh) {
        0 -> "On-Site"
        else -> "Remote"
    }
}
