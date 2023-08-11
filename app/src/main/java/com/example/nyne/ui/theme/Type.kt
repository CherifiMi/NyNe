package com.example.nyne.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.nyne.R


val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

val figeronaFont = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.figerona_black,
            weight = FontWeight.Black
        ),
        Font(
            resId = R.font.figerona_bold,
            weight = FontWeight.Bold
        ),
        Font(
            resId = R.font.figerona_extrabold,
            weight = FontWeight.ExtraBold
        ),
        Font(
            resId = R.font.figerona_extralight,
            weight = FontWeight.ExtraLight
        ),
        Font(
            resId = R.font.figerona_light,
            weight = FontWeight.Light
        ),
        Font(
            resId = R.font.figerona_medium,
            weight = FontWeight.Medium
        ),
        Font(
            resId = R.font.figerona_regular,
            weight = FontWeight.Normal
        ),
        Font(
            resId = R.font.figerona_semibold,
            weight = FontWeight.SemiBold
        ),
        Font(
            resId = R.font.figerona_thin,
            weight = FontWeight.Thin
        ),
    )
)

val pacificoFont = FontFamily(Font(R.font.pacifico_regular))

