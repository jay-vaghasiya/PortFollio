package com.jay.myportfollio.view

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
import com.jay.myportfollio.ui.theme.DarkGray
import com.jay.myportfollio.ui.theme.LightBlue
import com.jay.myportfollio.ui.theme.Pink50
import com.jay.myportfollio.utils.StrawFordFont
import com.jay.myportfollio.utils.sendMail

@Composable
fun MyWorkScreen(
    navController: NavHostController
) {
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

        }
    }
}
