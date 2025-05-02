package com.ramm.framework.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

interface Connectivity {
    fun hasNetworkAccess(): Boolean
}

class ConnectivityImpl(context: Context) : Connectivity {
    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun hasNetworkAccess(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

}