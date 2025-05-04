package com.ramm.framework.data.repositories.base

import com.ramm.core.domain.DataSourceError
import com.ramm.core.domain.Result
import com.ramm.core.domain.Failure
import com.ramm.framework.utils.Connectivity
import com.ramm.framework.utils.GENERAL_NETWORK_ERROR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseRepository : KoinComponent {
    private val connectivity: Connectivity by inject()

    protected suspend fun <N: Any> fetchData(
        dataProvider: suspend () -> Result<N>
    ): Result<N> {
        return try {
            val resultReturn = if (connectivity.hasNetworkAccess()) {
                withContext(Dispatchers.IO) {
                    dataProvider()
                }
            } else {
                Failure(DataSourceError(Throwable(GENERAL_NETWORK_ERROR)))
            }

            resultReturn
        } catch (e: Exception) {
            Failure(DataSourceError(Throwable(GENERAL_NETWORK_ERROR)))
        }
    }
}