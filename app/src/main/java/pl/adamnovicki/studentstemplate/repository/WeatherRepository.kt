package pl.adamnovicki.studentstemplate.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.ing.domain.WeatherRsp
import pl.nowicki.openweatherdexprotector.WeatherApiService
import timber.log.Timber
import pl.adamnovicki.studentstemplate.common.Result
import pl.adamnovicki.studentstemplate.common.exception.CancelledFetchDataException
import pl.adamnovicki.studentstemplate.common.exception.NetworkException

/**
 * Created by adamnowicki on 2020-02-23.
 */
class WeatherRepository(private val weatherApiService: WeatherApiService) {

    suspend fun getWeather(city: String): Result<WeatherRsp> {

        var result: Result<WeatherRsp> = Result.success(WeatherRsp())

        withContext(Dispatchers.IO) {

            try {
                val request = weatherApiService.forecast(city)

                val response: WeatherRsp = request.await()
                Timber.d("onWeatherReceived ${response}")

                request.let {
                    if (it.isCompleted) {
                        result = Result.success(response)
                    }
                    else if (it.isCancelled) {
                        result = Result.error(CancelledFetchDataException())
                    }
                }
            } catch (ex: Throwable) {
                result = Result.error(NetworkException())
                Timber.d("onWeatherReceived NetworkException")
            }
        }
        return result
    }
}