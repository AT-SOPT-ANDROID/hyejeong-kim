package org.sopt.at.feature.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import org.sopt.at.R
import org.sopt.at.core.navigation.MainTabRoute
import org.sopt.at.core.navigation.Route

enum class MainTab(
    @StringRes val titleResId: Int,
    @DrawableRes val iconResId: Int,
    val route: MainTabRoute,
) {
    HOME(
        iconResId = R.drawable.ic_home,
        titleResId = R.string.nav_home,
        route = MainTabRoute.Home
    ),
    SHORTS(
        iconResId = R.drawable.ic_shorts,
        titleResId = R.string.nav_shorts,
        route = MainTabRoute.Shorts
    ),
    LIVE(
        iconResId = R.drawable.ic_live,
        titleResId = R.string.nav_live,
        route = MainTabRoute.Live
    ),
    SEARCH(
        iconResId = R.drawable.ic_search,
        titleResId = R.string.nav_search,
        route = MainTabRoute.Search
    ),
    HISTORY(
        iconResId = R.drawable.ic_history,
        titleResId = R.string.nav_history,
        route = MainTabRoute.History
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}