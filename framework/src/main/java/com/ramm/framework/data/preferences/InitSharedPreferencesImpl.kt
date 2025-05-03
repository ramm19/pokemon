package com.ramm.framework.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class InitSharedPreferencesImpl(
    context: Context,
    sharedPreferencesName: String,
    masterKeyAlias: String,
) : InitSharedPreferences {

    private val prefs: SharedPreferences = getEncryptSharedPreferences(
        context,
        sharedPreferencesName,
        masterKeyAlias
    )
    override var savedProfile: Boolean
        get() = prefs.getBoolean("SAVED_PROFILE", false)
        set(value) = prefs.edit() { putBoolean("SAVED_PROFILE", value) }
    override var pathFile: String
        get() = prefs.getString("PATH_FILE", "").toString()
        set(value) = prefs.edit() { putString("PATH_FILE", value) }
    override var nameProfile: String
        get() = prefs.getString("NAME_PROFILE", "").toString()
        set(value) = prefs.edit() { putString("NAME_PROFILE", value) }
    override var hobby: String
        get() = prefs.getString("HOBBY", "").toString()
        set(value) = prefs.edit() { putString("HOBBY", value) }
    override var birthDay: String
        get() = prefs.getString("BIRTHDAY", "").toString()
        set(value) = prefs.edit() { putString("BIRTHDAY", value) }
    override var document: String
        get() = prefs.getString("DOCUMENT", "").toString()
        set(value) = prefs.edit() { putString("DOCUMENT", value) }
    override var myFavorites: String
        get() = prefs.getString("MY_FAVORITES", "").toString()
        set(value) = prefs.edit() { putString("MY_FAVORITES", value) }
}