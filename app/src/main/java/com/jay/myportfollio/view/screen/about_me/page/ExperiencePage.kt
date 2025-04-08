package com.jay.myportfollio.view.screen.about_me.page

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jay.myportfollio.model.datamodel.DataExperience
import com.jay.myportfollio.model.datamodel.Details
import com.jay.myportfollio.model.datamodel.Result
import com.jay.myportfollio.ui.theme.NavyBlue
import com.jay.myportfollio.utils.StrawFordFont
import com.jay.myportfollio.utils.TripleOrbitLoading
import com.jay.myportfollio.viewmodel.ExperienceViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExperiencePage(modifier: Modifier = Modifier) {
    val viewmodel: ExperienceViewModel = koinViewModel()
    val userState by viewmodel.experienceState.collectAsStateWithLifecycle()

    // Trigger data fetching
    LaunchedEffect(Unit) {
        viewmodel.fetchExperienceData()
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
                    TripleOrbitLoading(modifier = Modifier.size(120.dp))
                }
            }

            is Result.Success -> {
                val user = targetState.data
                ExperienceListContent(user)
            }

            is Result.Error -> {
                val error = targetState.exception.message
                Text(
                    text = "Error: $error",
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color.Red),
                    color = Color.White
                )
            }
        }
    }


}

@Composable
fun ExperienceListContent(
    user: List<DataExperience>,
) {
    val expandedStates = remember {
        mutableStateMapOf<Int, Boolean>().apply {
            user.forEach { put(it.id, false) }
        }
    }


    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(24.dp)) {
        items(items = user, key = { it.id }) { item: DataExperience ->
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)) {
                Text(
                    text = item.employer_name,
                    fontFamily = StrawFordFont.FontFamily,
                    color = NavyBlue,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium,
                )

                Text(
                    text = item.start_timeline + " TO " + item.end_timeline,
                    fontFamily = StrawFordFont.FontFamily,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = item.role,
                    fontFamily = StrawFordFont.FontFamily,
                    color = NavyBlue,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = item.location,
                    fontFamily = StrawFordFont.FontFamily,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                )
                if (item.details.isNotEmpty()) {
                    BulletPointText(
                        detailsList = item.details,
                        isExpanded = expandedStates[item.id] ?: false,
                        onToggleExpand = {
                            expandedStates[item.id] = !(expandedStates[item.id] ?: false)
                        }
                    )
                }

            }

        }
    }
}

@Composable
fun BulletPointText(
    detailsList: List<Details>?,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit
) {
    val allPoints = remember(detailsList) {
        detailsList?.flatMap { it.points } ?: emptyList()
    }

    val displayedPoints by remember(isExpanded, allPoints) {
        derivedStateOf { if (isExpanded) allPoints else allPoints.take(3) }
    }

    Column(
        modifier = Modifier
            .padding(start = 16.dp)
            .animateContentSize()
    ) {
        displayedPoints.forEach { point ->
            Text(
                text = "• $point",
                fontFamily = StrawFordFont.FontFamily,
                color = NavyBlue,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }

        if (allPoints.size > 3) {
            Text(
                text = if (isExpanded) "Show less" else "Show more",
                fontFamily = StrawFordFont.FontFamily,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { onToggleExpand() }
                    .padding(top = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}