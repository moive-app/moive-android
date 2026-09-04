package com.moive.app.core.network.token

import kotlinx.coroutines.flow.Flow

interface AuthManager {
    val authEvent: Flow<Unit>

    fun emitAuthEvent()
}
