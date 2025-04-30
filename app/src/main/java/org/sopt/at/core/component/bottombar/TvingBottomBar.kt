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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import org.sopt.at.core.navigation.BottomNavRoute
import org.sopt.at.core.navigation.LocalNavController

@Composable
fun TvingBottomBar(
    modifier: Modifier = Modifier
) {
    val navController = LocalNavController.current

    val screen = listOf<BottomNavRoute>(
        BottomNavRoute.Home,
        BottomNavRoute.Shorts,
        BottomNavRoute.Live,
        BottomNavRoute.Search,
        BottomNavRoute.History
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
                        // 그래프의 시작 위치로 이동
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true // 같은 화면 중복 방지
                        restoreState = true // 상태 복원
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
    TvingBottomBar()
}