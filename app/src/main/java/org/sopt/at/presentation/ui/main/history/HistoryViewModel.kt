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
class HistoryViewModel @Inject constructor(
    private val seriesRepository: SeriesRepository
) :
    ViewModel() {

    private val _seriesList = MutableStateFlow<List<Series>>(emptyList())
    val seriesList: StateFlow<List<Series>> = _seriesList.asStateFlow()

    private val _showDeleteDialog = MutableStateFlow<Boolean>(false)
    val showDeleteDialog: StateFlow<Boolean> = _showDeleteDialog.asStateFlow()

    private val _selectedSeries = MutableStateFlow<Series>(Series())
    val selectedSeries: StateFlow<Series> = _selectedSeries.asStateFlow()

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
        // 임의의 시리즈 설정
        val series = Series(
            title = "시리즈",
            imageUrl = "https://i.postimg.cc/d0y0GKnN/20240501-174053.jpg"
        )

        viewModelScope.launch {
            seriesRepository.insertSeries(series)
        }
    }

    fun deleteSeries(series: Series) {
        viewModelScope.launch {
            seriesRepository.deleteSeries(series = series)
        }
    }

    fun showDeleteDialog(selectedSeries: Series) {
        _showDeleteDialog.value = true
        _selectedSeries.value = selectedSeries
    }

    fun dismissDeleteDialog() {
        _showDeleteDialog.value = false
    }
}