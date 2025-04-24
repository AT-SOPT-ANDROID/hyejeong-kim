package org.sopt.at.presentation.ui.main.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.at.data.local.Series
import org.sopt.at.data.repository.SeriesRepository
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val seriesRepository: SeriesRepository) :
    ViewModel() {

    private val _seriesList = MutableStateFlow<List<Series>>(emptyList())
    val seriesList: StateFlow<List<Series>> = _seriesList.asStateFlow()

    init {
        setSeriesList()
    }

    private fun setSeriesList() {
        viewModelScope.launch {
            seriesRepository.getAllSeriesStream().collect { series ->
                _seriesList.value = series
            }
        }
    }

    fun insertSeries() {
        val series = Series(
            title = "시리즈",
            imageUrl = "https://i.postimg.cc/d0y0GKnN/20240501-174053.jpg"
        )

        viewModelScope.launch {
            seriesRepository.insertSeries(series)
        }
    }
}