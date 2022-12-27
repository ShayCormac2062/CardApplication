package com.shaycormac2062.cardapplication.utils

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shaycormac2062.cardapplication.R

@Composable
fun AppText(
    modifier: Modifier? = Modifier,
    text: String,
    size: TextUnit? = null,
    color: Color? = null,
    textDecoration: TextDecoration? = null
) {
    Text(
        modifier = modifier ?: Modifier,
        color = color ?: if (isSystemInDarkTheme()) Color.White else Color.Black,
        text = text,
        fontFamily = FontFamily(Font(R.font.railway)),
        fontSize = size ?: 16.sp,
        textDecoration = textDecoration,
        overflow = TextOverflow.Ellipsis,
        maxLines = 7
    )
}

@Composable
fun ProgressBar() {
    val infiniteTransition = rememberInfiniteTransition()
    val color = if (isSystemInDarkTheme()) Color.White else Color.Black
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(1500, easing = FastOutSlowInEasing)
        )
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.rotate(angle),
            progress = 0.5f,
            color = color,
            strokeWidth = 2.dp,
        )
        AppText(text = stringResource(id = R.string.loading))
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ErrorMessage(
    exception: ApplicationException,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberVectorPainter(
                image = ImageVector.vectorResource(
                    id = if (isSystemInDarkTheme()) {
                        R.drawable.ic_error_night
                    } else R.drawable.ic_error_day
                ),
            ),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        AppText(
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 56.dp
            ),
            text = exception.message,
            size = 24.sp
        )
        AppButton(
            modifier = Modifier
                .width(140.dp)
                .height(40.dp)
                .background(appGradient()),
            text = stringResource(id = R.string.try_again),
            onClick = {
                onClick()
            }
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun AppButton(
    modifier: Modifier? = Modifier,
    text: String = "",
    textColor: Color? = null,
    onClick: (() -> Unit)
) {
    Button(
        contentPadding = PaddingValues(),
        onClick = { onClick.invoke() })
    {
        Box(
            modifier = modifier ?: Modifier,
            contentAlignment = Alignment.Center
        ) {
            AppText(text = text, color = textColor)
        }
    }
}

@Composable
fun appGradient() = if (isSystemInDarkTheme()) {
    Brush.horizontalGradient(
        listOf(
            Color.DarkGray,
            Color.Black
        )
    )
} else Brush.horizontalGradient(
    listOf(
        Color.LightGray,
        Color.White
    )
)
