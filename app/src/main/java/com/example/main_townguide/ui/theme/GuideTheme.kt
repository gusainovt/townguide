package com.example.main_townguide.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Background = Color(0xFFF5F1EA)
val PrimaryGold = Color(0xFFC8A96A)
val GoldDark = Color(0xFFB7924E)
val TextPrimary = Color(0xFF2B2B2B)
val TextSecondary = Color(0xFF7A7A7A)
val Border = Color(0xFFE5E0D8)
val CardBackground = Color(0xFFFFFFFF)

val HeadingFont = FontFamily.Serif
val BodyFont = FontFamily.SansSerif

private val GuideColorScheme = lightColorScheme(
    primary = PrimaryGold,
    background = Background,
    surface = CardBackground,
    onPrimary = Color.White,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun GuideTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = GuideColorScheme,
        typography = MaterialTheme.typography.copy(
            headlineLarge = TextStyle(
                fontFamily = HeadingFont,
                fontWeight = FontWeight.Normal,
                fontSize = 32.sp,
                color = TextPrimary
            ),
            headlineMedium = TextStyle(
                fontFamily = HeadingFont,
                fontWeight = FontWeight.Normal,
                fontSize = 28.sp,
                color = TextPrimary
            ),
            titleLarge = TextStyle(
                fontFamily = HeadingFont,
                fontSize = 20.sp,
                color = TextPrimary
            ),
            bodyLarge = TextStyle(
                fontFamily = BodyFont,
                fontSize = 16.sp,
                color = TextPrimary
            ),
            bodyMedium = TextStyle(
                fontFamily = BodyFont,
                fontSize = 14.sp,
                color = TextPrimary
            )
        ),
        content = content
    )
}

