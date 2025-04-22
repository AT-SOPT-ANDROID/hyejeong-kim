package org.sopt.at.presentation.ui.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.Text
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val tabSections by viewModel.tabSections.collectAsState()
    val selectedTabIndex by viewModel.selectedTabIndex.collectAsState()

    val bannerList by viewModel.bannerList.collectAsState()
    val todayList by viewModel.todayList.collectAsState()
    val nowList by viewModel.nowList.collectAsState()

    // 배너 페이지 상태
    val pagerState = rememberPagerState(
        pageCount = { bannerList.size },
        initialPage = 0)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        stickyHeader {
            TabRow(
                selectedTabIndex = selectedTabIndex.coerceAtLeast(0),
                modifier = Modifier
                    .fillMaxWidth(),
                backgroundColor = Color.Black,
                contentColor = Color.White,
                indicator = {}
            ) {
                tabSections.forEachIndexed { index, resId ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { viewModel.selectTab(index) },
                        text = {
                            Text(
                                text = stringResource(id = resId),
                                color = if (selectedTabIndex == index) Color.White else Color.Gray
                            )
                        }
                    )
                }
            }
        }

        // 배너
        item {
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
                        .aspectRatio(3f / 4f)
                )
            }
        }

        // 오늘의 티빙 TOP 20
        item {
            HomeList(
                title = stringResource(R.string.home_today_20),
                itemList = todayList
            )
        }

        // 지금 방영 중인 콘텐츠
        item {
            HomeList(
                title = stringResource(R.string.home_now),
                itemList = nowList
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}