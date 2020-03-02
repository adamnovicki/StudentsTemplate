package pl.adamnovicki.studentstemplate

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.adamnovicki.studentstemplate.repository.WeatherRepository
import pl.adamnovicki.studentstemplate.ui.weather.WeatherViewModel
import pl.ing.cleanarchitecture.network.WeatherApi
import pl.nowicki.openweatherdexprotector.WeatherApiService

object MainModule {

    val mainModule = module {
        single { WeatherApi(androidContext()) }
        single { provideApiService(get()) }
        single { WeatherRepository(weatherApiService = get()) }
        viewModel { WeatherViewModel(weatherRepository = get()) }
    }

    private fun provideApiService(api: WeatherApi): WeatherApiService {
        return api.getWeatherApiService()
    }
}