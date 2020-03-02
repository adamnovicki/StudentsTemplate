package pl.adamnovicki.studentstemplate.ui.weather

import pl.adamnovicki.studentstemplate.common.ResultType
import pl.adamnovicki.studentstemplate.common.Result
import pl.ing.domain.WeatherRsp
import kotlin.math.roundToLong

/**
 * Created by adamnowicki on 2019-09-30.
 */
class WeatherViewModelMapper {

    object WeatherToUI {
        fun map(rsp: Result<WeatherRsp>?): Result<WeatherUI> {
            lateinit var result: Result<WeatherUI>

            if (rsp?.resultType == ResultType.ERROR) {
                result = Result.error(error = rsp.error)

            } else {
                val details = rsp?.data?.list?.first()
                val weatherData = WeatherUI(
                    temp = getCelsiusTemp(details?.main?.temp ?: KELVIN_ZERO),
                    windSpeed = (details?.wind?.speed ?: 0.0).roundToLong(),
                    city = rsp?.data?.city?.name ?: "",
                    iconType = mapWeatherType(details?.weather?.first()?.id)
                )
                result = Result.success(data = weatherData)
            }
            return result
        }
    }

    companion object {

        private fun mapWeatherType(id: Int?): IconType {
            return when (id) {
                800 -> IconType.SUN
                in 801..804 -> IconType.CLOUD
                in 500..531 -> IconType.RAIN
                in 600..622 -> IconType.SNOW
                else -> IconType.CLOUD
            }
        }

        private const val KELVIN_ZERO = 273.15

        private fun getCelsiusTemp(kelvins: Double) : Long = (kelvins - KELVIN_ZERO).roundToLong()
    }
}
