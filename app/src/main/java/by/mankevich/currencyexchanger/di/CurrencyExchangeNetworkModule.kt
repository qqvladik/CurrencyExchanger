package by.mankevich.currencyexchanger.di

import by.mankevich.currencyexchanger.data.api.CurrencyExchangeApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val REST_API_URL = "https://developers.paysera.com/tasks/api/"

@Module
class CurrencyExchangeNetworkModule {

    @Provides
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient,
        @BaseUrl baseUrl: String
    ) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .build()

    @Provides
    fun provideGson() = GsonBuilder().create()

    @Provides
    fun provideOkHttpClient(
    ) = OkHttpClient.Builder()
        .build()

    @Provides
    fun provideCurrencyExchangeApi(retrofit: Retrofit): CurrencyExchangeApi {
        return retrofit.create(CurrencyExchangeApi::class.java)
    }

    @Provides
    @BaseUrl
    fun provideRestApiUrl(): String {
        return REST_API_URL
    }
}
