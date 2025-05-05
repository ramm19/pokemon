package com.ramm.framework.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

fun getEncryptSharedPreferences (
    context: Context,
    fileName: String,
    masterKeyAlias: String,
): SharedPreferences {
    return try {
        createEncryptedPrefs(context, fileName, masterKeyAlias)
    } catch (e: Exception) {
        return try {
            context.deleteSharedPreferences(fileName)
            createEncryptedPrefs(context, fileName, masterKeyAlias)
        } catch (e: Exception) {
            context.getSharedPreferences("${fileName}_fallback", Context.MODE_PRIVATE)
        }
    }
}

private fun createEncryptedPrefs(
    context: Context,
    fileName: String,
    masterKeyAlias: String,
): SharedPreferences {
    val masterKey = MasterKeys.getOrCreate(getKeyGenParameterSpecAes(masterKeyAlias))
    return EncryptedSharedPreferences.create(
        fileName,
        masterKey,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}
