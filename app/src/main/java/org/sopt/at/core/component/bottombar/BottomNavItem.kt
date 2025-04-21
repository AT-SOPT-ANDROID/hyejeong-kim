package org.sopt.at.core.component.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.at.R

sealed class BottomNavItem(
    val route: String,
    @StringRes val titleResId: Int,
    @DrawableRes val iconResId: Int,
) {
    data object Home: BottomNavItem (
        route = "home",
        iconResId = R.drawable.ic_home,
        titleResId = R.string.bottom_nav_home,
    )
    data object Shorts: BottomNavItem (
        route = "shorts",
        iconResId = R.drawable.ic_shorts,
        titleResId = R.string.bottom_nav_shorts,
    )
    data object Live: BottomNavItem (
        route = "live",
        iconResId = R.drawable.ic_live,
        titleResId = R.string.bottom_nav_live,
    )
    data object Search: BottomNavItem (
        route = "search",
        iconResId = R.drawable.ic_search,
        titleResId = R.string.bottom_nav_search,
    )
    data object History: BottomNavItem (
        route = "history",
        iconResId = R.drawable.ic_history,
        titleResId = R.string.bottom_nav_history,
    )
}

