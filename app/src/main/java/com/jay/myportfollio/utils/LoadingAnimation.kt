package com.jay.myportfollio.utils

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jay.myportfollio.ui.theme.MyPortFollioTheme


// Triple Orbit
@Composable
fun TripleOrbitLoading(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000)
        ),
        label = "rotationTransition"
    )
    var width by remember {
        mutableIntStateOf(0)
    }
    Box(
        modifier = modifier
            .size(40.dp)
            .onSizeChanged { width = it.width },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            strokeWidth = 1.dp,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    rotationZ = rotation
                })

        CircularProgressIndicator(
            strokeWidth = 1.dp,
            modifier = Modifier
                .fillMaxSize()
                .padding(with(LocalDensity.current) {
                    (width * Constant.PADDING_INNER_CIRCLE).toDp()
                })
                .graphicsLayer {
                    rotationZ = rotation + Constant.POSITION_OFFSET_INNER_CIRCLE
                })

        CircularProgressIndicator(
            strokeWidth = 1.dp,
            modifier = Modifier
                .fillMaxSize()
                .padding(with(LocalDensity.current) {
                    (width * Constant.PADDING_OUTER_CIRCLE).toDp()
                })
                .graphicsLayer {
                    rotationZ = rotation + Constant.POSITION_OFFSET_OUTER_CIRCLE
                })
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun TripleOrbitLoadingPrev() {
    MyPortFollioTheme {
        TripleOrbitLoading()
    }

}


@Composable
fun PulseLoading(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000)
        ),
        label = "progressTransition"
    )
    var width by remember {
        mutableIntStateOf(0)
    }
    Box(modifier = modifier
        .size(60.dp)
        .onSizeChanged { width = it.width }
        .graphicsLayer {
            scaleX = progress
            scaleY = progress
            alpha = 1f - progress
        }
        .border(width = 5.dp, color = color, shape = CircleShape))

}

@Preview()
@Composable
private fun PulseLoadingPrev() {
    MyPortFollioTheme {
        PulseLoading(color = Color.Red)
    }

}