package ru.tictac.tictactoe_matryoshka.models

import android.content.Context
import android.content.SharedPreferences

class ThemeStorage(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("game_settings", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_DARK = "is_dark_theme"
    }

    var isDarkTheme: Boolean
        get() = prefs.getBoolean(KEY_IS_DARK, false)  // По умолчанию светлая
        set(value) = prefs.edit().putBoolean(KEY_IS_DARK, value).apply()
}