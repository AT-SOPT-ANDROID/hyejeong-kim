package org.sopt.at.presentation.ui.main.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.at.R

class HomeViewModel : ViewModel() {
    private val _bannerList = MutableStateFlow<List<Int>>(emptyList())
    val bannerList: StateFlow<List<Int>> = _bannerList.asStateFlow()

    private val _todayList = MutableStateFlow<List<Int>>(emptyList())
    val todayList: StateFlow<List<Int>> = _todayList.asStateFlow()

    private val _nowList = MutableStateFlow<List<Int>>(emptyList())
    val nowList: StateFlow<List<Int>> = _nowList.asStateFlow()

    init {
        setHomeBannerList()
        setHomeTodayList()
        setHomeNowList()
    }

    // 배너 데이터
    private fun setHomeBannerList() {
        val fetchedData = listOf(
            R.drawable.poster1,
            R.drawable.poster2,
            R.drawable.poster3,
            R.drawable.poster4,
            R.drawable.poster5
        )
        _bannerList.value = fetchedData
    }

    // 오늘의 티빙 TOP 20 데이터
    private fun setHomeTodayList() {
        val fetchedData = listOf(
            R.drawable.poster1,
            R.drawable.poster2,
            R.drawable.poster3,
            R.drawable.poster4,
            R.drawable.poster5,
            R.drawable.poster1,
            R.drawable.poster2,
            R.drawable.poster3,
            R.drawable.poster4,
            R.drawable.poster5,
            R.drawable.poster1,
            R.drawable.poster2,
            R.drawable.poster3,
            R.drawable.poster4,
            R.drawable.poster5,
            R.drawable.poster1,
            R.drawable.poster2,
            R.drawable.poster3,
            R.drawable.poster4,
            R.drawable.poster5
        )
        _todayList.value = fetchedData
    }

    // 지금 방영 중인 콘텐츠 데이터
    private fun setHomeNowList() {
        val fetchedData = listOf(
            R.drawable.poster1,
            R.drawable.poster2,
            R.drawable.poster3,
            R.drawable.poster4,
            R.drawable.poster5,
            R.drawable.poster1,
            R.drawable.poster2,
            R.drawable.poster3,
            R.drawable.poster4,
            R.drawable.poster5
        )
        _nowList.value = fetchedData
    }
}