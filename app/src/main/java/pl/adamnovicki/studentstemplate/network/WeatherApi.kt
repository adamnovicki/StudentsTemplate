package pl.ing.cleanarchitecture.network

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.adamnovicki.studentstemplate.R
import pl.nowicki.openweatherdexprotector.WeatherApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

class WeatherApi(private val context: Context) {

    val SERVER_URL = "https://api.openweathermap.org"

    fun getWeatherApiService() : WeatherApiService {

        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

        val okBuilder = OkHttpClient.Builder()
        okBuilder.cookieJar(JavaNetCookieJar(cookieManager))

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        okBuilder.addNetworkInterceptor(logging)
        okBuilder.readTimeout(
            context.resources.getInteger(R.integer.read_timeout).toLong(),
            TimeUnit.MILLISECONDS
        )
        okBuilder.connectTimeout(
            context.resources.getInteger(R.integer.connect_timeout).toLong(),
            TimeUnit.MILLISECONDS
        )

        val retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        
        return retrofit.create(WeatherApiService::class.java)
    }
}