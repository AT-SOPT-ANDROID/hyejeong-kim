package org.sopt.at.core.component.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.sopt.at.R
import org.sopt.at.core.navigation.NavRoute

@Composable
fun TvingHomeTopBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black)
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.tving_logo),
            contentDescription = "TVING LOGO",
            modifier = Modifier
                .size(width = 80.dp, height = 32.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.tving_square_logo),
            contentDescription = "TVING SQUARE LOGO",
            modifier = Modifier
                .padding(0.dp)
                .size(24.dp)
                .clickable {
                    navController.navigate(route = NavRoute.My.route)
                }
        )
    }
}

@Preview
@Composable
private fun PreviewTvingHomeTopBar() {
    val navController = rememberNavController()
    TvingHomeTopBar(navController = navController)
}