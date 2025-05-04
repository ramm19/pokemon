package com.ramm.cuscatlanpokemon.di

import com.ramm.framework.data.preferences.ConstantsEncrypt
import com.ramm.framework.data.preferences.InitSharedPreferences
import com.ramm.framework.data.preferences.InitSharedPreferencesImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<InitSharedPreferences> {
        InitSharedPreferencesImpl(
            androidContext(),
            ConstantsEncrypt.SHARED_PREFERENCES_NAME,
            ConstantsEncrypt.MASTER_KEY_ALIAS
        )
    }
}