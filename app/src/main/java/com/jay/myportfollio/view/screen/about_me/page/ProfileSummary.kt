package com.jay.myportfollio.view.screen.about_me.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jay.myportfollio.model.datamodel.DataProfile
import com.jay.myportfollio.ui.theme.GrayBlue
import com.jay.myportfollio.ui.theme.NavyBlue
import com.jay.myportfollio.utils.StrawFordFont

@Composable
fun ProfileSummary(modifier: Modifier = Modifier, user: DataProfile) {
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
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Current Location",
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = StrawFordFont.FontFamily,
            color = NavyBlue,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = user.current_location.toString(),
            fontFamily = StrawFordFont.FontFamily,
            color = GrayBlue,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}


