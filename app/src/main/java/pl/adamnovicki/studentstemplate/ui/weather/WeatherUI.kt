package pl.adamnovicki.studentstemplate.ui.weather

/**
 * Created by adamnowicki on 2019-09-30.
 */
data class WeatherUI (
    val temp: Long,
    val windSpeed: Long,
    val city: String,
    val iconType: IconType
) {
    override fun toString(): String {
        return "WeatherUI(temp=$temp, windSpeed='$windSpeed', city='$city', iconType=$iconType)"
    }
}