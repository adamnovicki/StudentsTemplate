package pl.adamnovicki.studentstemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.adamnovicki.studentstemplate.databinding.WeatherActivityBinding
import pl.adamnovicki.studentstemplate.ui.weather.WeatherUI
import pl.adamnovicki.studentstemplate.ui.weather.WeatherViewModel
import timber.log.Timber

class WeatherActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModel()

    private lateinit var binding: WeatherActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.weather_activity)

        observeLiveData()
        getWeather()
    }

    private fun observeLiveData() {
        viewModel.isErrorLiveData.observe(this, Observer { onErrorReceived() })
        viewModel.weatherLiveData.observe(this, Observer(::onWeatherReceived))
    }

    private fun getWeather() {
        viewModel.getWeather("Katowice")
    }

    private fun onErrorReceived() {
        AlertDialog.Builder(this)
            .setTitle(R.string.error_title)
            .setCancelable(false)
            .setNegativeButton(R.string.error_cancel) { _, _ ->
                finish()
            }
            .setPositiveButton(R.string.error_try_again) { _, _ ->
                getWeather()
            }.show()
    }

    private fun onWeatherReceived(weather: WeatherUI) {
        binding.weather = weather
    }
}
