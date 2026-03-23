package com.example.reply.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme // МОРА ДА ГО ИМА ОВА
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Темни бои (за Dark Mode)
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// Светли бои (за Light Mode)
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@Composable
fun ReplyTheme(
    // ПОПРАВЕНО: Сега користи 'isSystemInDarkTheme()' за автоматски да препознае Settings
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Тука Compose одлучува кои бои да ги земе
    val replyColorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = replyColorScheme,
        // Тука подоцна можеш да додадеш Typography и Shapes
        content = content
    )
}