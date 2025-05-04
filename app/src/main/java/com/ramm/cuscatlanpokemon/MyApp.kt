package com.ramm.cuscatlanpokemon

import android.annotation.SuppressLint
import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.util.DebugLogger
import com.ramm.core.di.interactionModule
import com.ramm.cuscatlanpokemon.BuildConfig
import com.ramm.cuscatlanpokemon.di.appModule
import com.ramm.cuscatlanpokemon.di.mainModule
import com.ramm.framework.data.preferences.ConstantsEncrypt
import com.ramm.framework.data.preferences.InitSharedPreferencesImpl
import com.ramm.framework.di.networkModule
import com.ramm.framework.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.module

val preferences: InitSharedPreferencesImpl by lazy { MyApp.prefs!! }

class MyApp : Application() {

    private val appModules = listOf(appModule, mainModule)
    private val frameworkModules = listOf(networkModule(BuildConfig.IS_DEBUG), repositoryModule)
    private val coreModules = listOf<Module>(interactionModule)

    companion object {
        var prefs : InitSharedPreferencesImpl? = null

        @SuppressLint("StaticFieldLeak")
        lateinit var instance: MyApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        prefs = InitSharedPreferencesImpl(
            applicationContext,
            ConstantsEncrypt.SHARED_PREFERENCES_NAME,
            ConstantsEncrypt.MASTER_KEY_ALIAS
        )

        startKoinModules()
        loggerCoil()
    }

    private fun startKoinModules() {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MyApp)
            modules(appModules + frameworkModules + coreModules)
        }
    }

    private fun loggerCoil(){
        Coil.setImageLoader(
            ImageLoader.Builder(applicationContext)
                .logger(DebugLogger())
                .build()
        )
    }

}