package org.sopt.at.core.component.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import org.sopt.at.feature.main.MainTab
import org.sopt.at.ui.theme.TvingTheme
import androidx.compose.animation.slideInVertically as slideInVertically1

@Composable
fun TvingBottomBar(
    modifier: Modifier = Modifier,
    visible: Boolean,
    tabs: PersistentList<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
) {
//    val screen = listOf<BottomNavRoute>(
//        BottomNavRoute.Home,
//        BottomNavRoute.Shorts,
//        BottomNavRoute.Live,
//        BottomNavRoute.Search,
//        BottomNavRoute.History
//    )

//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically1 { it },
        exit = fadeOut() + slideOutVertically { it }
    ) {
        BottomAppBar(
            modifier = modifier,
            containerColor = TvingTheme.colors.BasicBlack
        ) {
            tabs.forEach { tab ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = tab.iconResId),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    selected = tab == currentTab,
                    label = {
                        Text(text = stringResource(id = tab.titleResId))
                    },
                    onClick = {
                        onTabSelected(tab)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                        unselectedIconColor = TvingTheme.colors.Gray2,
                        unselectedTextColor = TvingTheme.colors.Gray2,
                        selectedIconColor = TvingTheme.colors.BasicWhite,
                        selectedTextColor = TvingTheme.colors.BasicWhite
                    )
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewTvingBottomBar() {
    TvingBottomBar(
        visible = true,
        tabs = MainTab.entries.toPersistentList(),
        currentTab = MainTab.HOME,
        onTabSelected = {}
    )
}