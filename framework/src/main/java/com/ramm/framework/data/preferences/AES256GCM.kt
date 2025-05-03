package com.ramm.framework.data.preferences

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties

private const val DEFAULT_AES_KEY_SIZE = 256

fun getKeyGenParameterSpecAes(masterKeyAlias: String): KeyGenParameterSpec =
    KeyGenParameterSpec.Builder(
        masterKeyAlias,
        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    )
        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
        .setKeySize(DEFAULT_AES_KEY_SIZE)
        .setRandomizedEncryptionRequired(true)
        .build()
