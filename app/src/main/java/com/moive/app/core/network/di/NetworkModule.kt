package com.moive.app.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.moive.app.BuildConfig
import com.moive.app.core.extensions.isJsonArray
import com.moive.app.core.extensions.isJsonObject
import com.moive.app.core.network.AuthInterceptor
import com.moive.app.core.network.TokenAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Converter
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val CONTENT_TYPE = "application/json"
    private const val LOGGING_TAG = "okhttp"

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        prettyPrint = BuildConfig.DEBUG
    }

    @Provides
    @Singleton
    fun provideJsonConverter(json: Json): Converter.Factory =
        json.asConverterFactory(CONTENT_TYPE.toMediaType())

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): Interceptor = HttpLoggingInterceptor { message ->
        when {
            message.isJsonObject() ->
                Timber.Forest.tag(LOGGING_TAG).d(JSONObject(message).toString(4))

            message.isJsonArray() ->
                Timber.Forest.tag(LOGGING_TAG).d(JSONArray(message).toString(4))

            else -> {
                Timber.Forest.tag(LOGGING_TAG).d("CONNECTION INFO -> $message")
            }
        }
    }.apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    @NoAuth
    fun provideNoAuthOkHttpClient(
        loggingInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    @Auth
    fun provideAuthOkHttpClient(
        loggingInterceptor: Interceptor,
        authInterceptor: AuthInterceptor,
        authenticator: TokenAuthenticator,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .authenticator(authenticator)
        .build()

    @Provides
    @Singleton
    @NoAuth
    fun provideNoAuthRetrofit(
        @NoAuth client: OkHttpClient,
        factory: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(factory)
        .build()

    @Provides
    @Singleton
    @Auth
    fun provideAuthRetrofit(
        @Auth client: OkHttpClient,
        factory: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(factory)
        .build()
}
