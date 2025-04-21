package org.sopt.at.core.component.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.at.R

data class NavigationData(
    val title: String,
    @DrawableRes val iconResId: Int,
    @StringRes val titleResId: Int,
    val route: String
)

@Composable
fun TvingBottomBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    // 바텀 네비게이션 메뉴
    val navMenus = listOf(
        NavigationData(
            title = "HOME",
            iconResId = R.drawable.ic_home,
            titleResId = R.string.bottom_nav_home,
            route = "home"
        ),
        NavigationData(
            title = "SHORTS",
            iconResId = R.drawable.ic_shorts,
            titleResId = R.string.bottom_nav_shorts,
            route = "shorts"
        ),
        NavigationData(
            title = "LIVE",
            iconResId = R.drawable.ic_live,
            titleResId = R.string.bottom_nav_live,
            route = "live"
        ),
        NavigationData(
            title = "SEARCH",
            iconResId = R.drawable.ic_search,
            titleResId = R.string.bottom_nav_search,
            route = "search"
        ),
        NavigationData(
            title = "HISTORY",
            iconResId = R.drawable.ic_history,
            titleResId = R.string.bottom_nav_history,
            route = "history"
        )
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomAppBar {
        navMenus.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconResId),
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                selected = currentRoute == item.route,
                label = {
                    Text(text = stringResource(id = item.titleResId))
                },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTvingBottomBar() {
    val navController = rememberNavController()
    TvingBottomBar(navController = navController)
}