package org.sopt.at.core.component.bottombar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun TvingBottomBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val screen = listOf<BottomNavItem>(
        BottomNavItem.Home,
        BottomNavItem.Shorts,
        BottomNavItem.Live,
        BottomNavItem.Search,
        BottomNavItem.History
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomAppBar(
        containerColor = Color.Black
    ) {
        screen.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconResId),
                        contentDescription = item.route,
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
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White
                )
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