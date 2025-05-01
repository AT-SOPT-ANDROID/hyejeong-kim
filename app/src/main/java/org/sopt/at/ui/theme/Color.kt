package org.sopt.at.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Gray
val Gray1 = Color(0xFFAAAAAA)
val Gray2 = Color(0xFF888888)
val Gray3 = Color(0xFF666666)
val Gray4 = Color(0xFF444444)
val Gray5 = Color(0xFF222222)

// Black & White
val BasicBlack = Color(0xFF000000)
val BasicWhite = Color(0xFFFFFFFF)

// Brand
val BrandRed = Color(0xFFFF1F45)

@Immutable
data class TvingColors(
    val Gray1: Color,
    val Gray2: Color,
    val Gray3: Color,
    val Gray4: Color,
    val Gray5: Color,
    val BasicBlack: Color,
    val BasicWhite: Color,
    val BrandRed: Color
)

val defaultTvingColors = TvingColors(
    Gray1 = Gray1,
    Gray2 = Gray2,
    Gray3 = Gray3,
    Gray4 = Gray4,
    Gray5 = Gray5,
    BasicBlack = BasicBlack,
    BasicWhite = BasicWhite,
    BrandRed = BrandRed
)

val LocalTvingColorsProvider = staticCompositionLocalOf { defaultTvingColors }