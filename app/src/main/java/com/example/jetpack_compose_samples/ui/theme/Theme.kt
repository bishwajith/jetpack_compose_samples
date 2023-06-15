package com.example.jetpack_compose_samples.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF006688),
    onPrimary = Color(0xffffffff),
    primaryContainer = Color(0xffbee8ff),
    onPrimaryContainer = Color(0xff001e2b),
    secondary = Color(0xFF4e616c),
    onSecondary = Color(0xFFffffff),
    secondaryContainer = Color(0xFFd1e6f3),
    onSecondaryContainer = Color(0xFF091e28),
    tertiary = Color(0xFF5e5a7c),
    onTertiary = Color(0xffffffff),
    tertiaryContainer = Color(0xffe5dfff),
    onTertiaryContainer = Color(0xff1b1736),
    error = Color(0xffba1b1b),
    background = Color(0xfffbfcfe),
    onBackground = Color(0xff191c1e),
    surfaceVariant = Color(0xffdce3e9)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF006688),
    onPrimary = Color(0xffffffff),
    primaryContainer = Color(0xffbee8ff),
    onPrimaryContainer = Color(0xff001e2b),
    secondary = Color(0xFF4e616c),
    onSecondary = Color(0xFFffffff),
    secondaryContainer = Color(0xFFd1e6f3),
    onSecondaryContainer = Color(0xFF091e28),
    tertiary = Color(0xFF5e5a7c),
    onTertiary = Color(0xffffffff),
    tertiaryContainer = Color(0xffe5dfff),
    onTertiaryContainer = Color(0xff1b1736),
    error = Color(0xffba1b1b),
    background = Color(0xfffbfcfe),
    onBackground = Color(0xff191c1e),
    surfaceVariant = Color(0xffdce3e9)
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun Jetpack_compose_samplesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}