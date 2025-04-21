package org.sopt.at.presentation.ui.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.at.R
import org.sopt.at.core.component.list.HomeList

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
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(bannerList) { index, banner ->
                Image(
                    painter = painterResource(id = banner),
                    contentDescription = "Banner $index",
                    modifier = Modifier
                        .width(120.dp)
                        .height(180.dp),
                    contentScale = ContentScale.Crop,
                )
            }
        }

        val pagerState = rememberPagerState(pageCount = {bannerList.size})

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            Image(
                painter = painterResource(id = ),
                contentDescription = "Banner",
                modifier = Modifier
                    .width(120.dp)
                    .height(180.dp),
                contentScale = ContentScale.Crop,
            )
        }

        HomeList(
            title = stringResource(R.string.home_today_20),
            itemList = todayList
        )

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