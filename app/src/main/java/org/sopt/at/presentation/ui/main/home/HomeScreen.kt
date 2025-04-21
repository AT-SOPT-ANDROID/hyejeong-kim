package org.sopt.at.presentation.ui.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.at.R
import org.sopt.at.core.component.list.HomeList
import org.sopt.at.core.component.list.HomeListCardItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val bannerList by viewModel.bannerList.collectAsState()
    val todayList by viewModel.todayList.collectAsState()
    val nowList by viewModel.nowList.collectAsState()

    // 스크롤
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .verticalScroll(scrollState)
    ) {
        // 배너
        val pagerState = rememberPagerState(pageCount = { bannerList.size })

        HorizontalPager(
            state = pagerState,
            pageSpacing = 8.dp,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) { page ->
            HomeListCardItem(
                item = bannerList[page],
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        // 오늘의 티빙 TOP 20
        HomeList(
            title = stringResource(R.string.home_today_20),
            itemList = todayList
        )

        // 지금 방영 중인 콘텐츠
        HomeList(
            title = stringResource(R.string.home_now),
            itemList = nowList
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}