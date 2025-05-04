package com.ramm.framework.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

fun getEncryptSharedPreferences (
    context: Context,
    file_name: String,
    master_key_alias: String,
): SharedPreferences = EncryptedSharedPreferences.create(
    file_name,
    MasterKeys.getOrCreate(getKeyGenParameterSpecAes(master_key_alias)),
    context,
    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
)