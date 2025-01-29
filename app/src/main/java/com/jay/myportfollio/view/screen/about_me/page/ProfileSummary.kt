package com.jay.myportfollio.view.screen.about_me.page

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jay.myportfollio.model.datamodel.DataProfile
import com.jay.myportfollio.model.datamodel.Result
import com.jay.myportfollio.ui.theme.BlackGray
import com.jay.myportfollio.ui.theme.BlueLight
import com.jay.myportfollio.ui.theme.GrayBlue
import com.jay.myportfollio.ui.theme.NavyBlue
import com.jay.myportfollio.ui.theme.OrangeRed
import com.jay.myportfollio.utils.PulseLoading
import com.jay.myportfollio.utils.StrawFordFont
import com.jay.myportfollio.utils.TripleOrbitLoading
import com.jay.myportfollio.view.screen.landing.ProfileContent
import com.jay.myportfollio.viewmodel.ExperienceViewModel
import com.jay.myportfollio.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileSummary(modifier: Modifier = Modifier) {
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                    ,
                    contentAlignment = Alignment.Center
                ) {
                    TripleOrbitLoading( modifier = Modifier.size(120.dp))
                }
            }

            is Result.Success -> {
                val user = targetState.data
                ProfileSummaryContent(user,modifier)
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
fun ProfileSummaryContent(user: DataProfile, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        Text(
            text = "Hello!",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = StrawFordFont.FontFamily,
            color = NavyBlue,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = user.summary.toString(),
            fontFamily = StrawFordFont.FontFamily,
            color = GrayBlue,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}


@Composable
fun AcademicPage(modifier: Modifier = Modifier) {

}
@Composable
fun CertificationPage(modifier: Modifier = Modifier) {

}