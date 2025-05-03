package com.ramm.framework.di

import com.itkacher.okprofiler.BuildConfig
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.ramm.framework.service.PokemonService
import com.ramm.framework.utils.NORMAL
import com.ramm.framework.utils.POKEMON
import com.ramm.framework.utils.TIMEOUT_SECONDS
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { GsonConverterFactory.create() as Converter.Factory }
    single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) as Interceptor }

    single(named(NORMAL)) {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(get() as Interceptor)
                    .callTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                addInterceptor(OkHttpProfilerInterceptor())
                    .callTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            }
        }.readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    single(named(POKEMON)){
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(get(named(NORMAL)))
            .addConverterFactory(get())
            .build()
    }

    single { get<Retrofit>(named(POKEMON)).create(PokemonService::class.java) }
}