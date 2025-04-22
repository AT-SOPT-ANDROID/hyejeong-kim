package org.sopt.at.core.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.at.R

sealed class BottomNavRoute(
    val route: String,
    @StringRes val titleResId: Int,
    @DrawableRes val iconResId: Int,
) {
    data object Home: BottomNavRoute(
        route = "home",
        iconResId = R.drawable.ic_home,
        titleResId = R.string.nav_home,
    )
    data object Shorts: BottomNavRoute(
        route = "shorts",
        iconResId = R.drawable.ic_shorts,
        titleResId = R.string.nav_shorts,
    )
    data object Live: BottomNavRoute(
        route = "live",
        iconResId = R.drawable.ic_live,
        titleResId = R.string.nav_live,
    )
    data object Search: BottomNavRoute(
        route = "search",
        iconResId = R.drawable.ic_search,
        titleResId = R.string.nav_search,
    )
    data object History: BottomNavRoute(
        route = "history",
        iconResId = R.drawable.ic_history,
        titleResId = R.string.nav_history,
    )
}

