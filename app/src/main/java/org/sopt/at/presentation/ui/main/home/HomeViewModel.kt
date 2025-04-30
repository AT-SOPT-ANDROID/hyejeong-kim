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

    private val _tabSections = MutableStateFlow<List<Int>>(emptyList())
    val tabSections: StateFlow<List<Int>> = _tabSections.asStateFlow()

    private val _selectedTabIndex = MutableStateFlow<Int>(-1)
    val selectedTabIndex : StateFlow<Int> = _selectedTabIndex.asStateFlow()

    private val _tabDramaList = MutableStateFlow<List<Int>>(emptyList())
    val tabDramaList: StateFlow<List<Int>> = _tabDramaList.asStateFlow()

    private val _tabEntList = MutableStateFlow<List<Int>>(emptyList())
    val tabEntSList: StateFlow<List<Int>> = _tabEntList.asStateFlow()

    private val _tabMovieList = MutableStateFlow<List<Int>>(emptyList())
    val tabMovieList: StateFlow<List<Int>> = _tabMovieList.asStateFlow()

    private val _tabSportsList = MutableStateFlow<List<Int>>(emptyList())
    val tabSportsList: StateFlow<List<Int>> = _tabSportsList.asStateFlow()

    private val _tabAniList = MutableStateFlow<List<Int>>(emptyList())
    val tabAniList: StateFlow<List<Int>> = _tabAniList.asStateFlow()

    private val _tabNewsList = MutableStateFlow<List<Int>>(emptyList())
    val tabNewsList: StateFlow<List<Int>> = _tabNewsList.asStateFlow()

    init {
        setHomeBannerList()
        setHomeTodayList()
        setHomeNowList()
        setTabSections()
        setDramaList()
        setEntList()
        setMovieList()
        setSportsList()
        setAniList()
        setNewList()
    }

    fun selectTab(index: Int) {
        _selectedTabIndex.value = index
        setTabData(index)
    }

    private fun setTabData(index: Int) {
        val banner = when(index) {
            0 -> _tabDramaList.value
            1 -> _tabEntList.value
            2 -> _tabMovieList.value
            3 -> _tabSportsList.value
            4 -> _tabAniList.value
            else -> _tabNewsList.value
        }

        val today = when(index) {
            0 -> _tabDramaList.value
            1 -> _tabEntList.value
            2 -> _tabMovieList.value
            3 -> _tabSportsList.value
            4 -> _tabAniList.value
            else -> _tabNewsList.value
        }

        val now = when(index) {
            0 -> _tabDramaList.value
            1 -> _tabEntList.value
            2 -> _tabMovieList.value
            3 -> _tabSportsList.value
            4 -> _tabAniList.value
            else -> _tabNewsList.value
        }

        _bannerList.value = banner
        _todayList.value = today
        _nowList.value = now
    }

    // 배너 디폴트 데이터
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

    // 오늘의 티빙 TOP 20 디폴트 데이터
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

    // 지금 방영 중인 콘텐츠 디폴트 데이터
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

    // Tab Layout 데이터
    private fun setTabSections() {
        val fetchedData = listOf(
            R.string.tab_drama,
            R.string.tab_ent,
            R.string.tab_movie,
            R.string.tab_sports,
            R.string.tab_ani,
            R.string.tab_news
        )
        _tabSections.value = fetchedData
    }

    private fun setDramaList() {
        val fetchedData = listOf(
            R.drawable.drama_poster1,
            R.drawable.drama_poster2,
            R.drawable.drama_poster3,
            R.drawable.drama_poster1,
            R.drawable.drama_poster2,
            R.drawable.drama_poster3
        )
        _tabDramaList.value = fetchedData
    }

    private fun setEntList() {
        val fetchedData = listOf(
            R.drawable.ent_poster1,
            R.drawable.ent_poster2,
            R.drawable.ent_poster3,
            R.drawable.ent_poster1,
            R.drawable.ent_poster2,
            R.drawable.ent_poster3
        )
        _tabEntList.value = fetchedData
    }

    private fun setMovieList() {
        val fetchedData = listOf(
            R.drawable.poster5,
            R.drawable.poster4,
            R.drawable.poster3,
            R.drawable.poster2,
            R.drawable.poster1,
        )
        _tabMovieList.value = fetchedData
    }

    private fun setSportsList() {
        val fetchedData = listOf(
            R.drawable.sports_poster1,
            R.drawable.sports_poster2,
            R.drawable.sports_poster3
        )
        _tabSportsList.value = fetchedData
    }

    private fun setAniList() {
        val fetchedData = listOf(
            R.drawable.poster1,
            R.drawable.poster2,
            R.drawable.poster3,
            R.drawable.poster4,
            R.drawable.poster5,
        )
        _tabAniList.value = fetchedData
    }

    private fun setNewList() {
        val fetchedData = listOf(
            R.drawable.news_poster1,
            R.drawable.news_poster2,
            R.drawable.news_poster3,
            R.drawable.news_poster1,
            R.drawable.news_poster2,
            R.drawable.news_poster3
        )
        _tabNewsList.value = fetchedData
    }
}