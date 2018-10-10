package co.cognized.SOApp.di

import co.cognized.SOApp.network.SOApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    val BASE_URL = "https://api.stackexchange.com/"
    val SO_FILTER = "!)b)Gds3nyKwbiCRiAoZkC7woGhsVha)JjvPG0kI38TLoH"
    val SO_SITE = "stackoverflow"
    val PAGE_SIZE = "10"

    @Provides
    @JvmStatic
    internal fun provideApi() : SOApi {
        // add necessary query params to each request
        val client = OkHttpClient.Builder()
            .addInterceptor {
                chain: Interceptor.Chain ->
                val url = chain.request().url()
                    .newBuilder()
                    .addQueryParameter("filter", SO_FILTER)
                    .addQueryParameter("site", SO_SITE)
                    .addQueryParameter("pagesize", PAGE_SIZE)
                    .build()
               chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()

        return retrofit.create(SOApi::class.java)
    }
}