package pl.adamnovicki.studentstemplate.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.adamnovicki.studentstemplate.common.ResultType
import pl.adamnovicki.studentstemplate.common.Result
import pl.adamnovicki.studentstemplate.repository.WeatherRepository

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    val weatherLiveData: MutableLiveData<WeatherUI> = MutableLiveData()
    val isErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getWeather(city: String) {
        viewModelScope.launch {
            val apiResult = weatherRepository.getWeather(city)
            val uiResult  = WeatherViewModelMapper.WeatherToUI.map(apiResult)
            updateWeatherLiveData(uiResult)
        }
    }

    private fun updateWeatherLiveData(result: Result<WeatherUI>) {
        if (isResultSuccess(result.resultType)) {
            weatherLiveData.postValue(result.data)
        } else {
            onResultError()
        }
    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        return resultType == ResultType.SUCCESS
    }

    private fun onResultError() = isErrorLiveData.postValue(true)
}