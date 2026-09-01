package com.moive.app.core.network.token

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class AuthManagerImpl @Inject constructor() : AuthManager {
    private val _authEvent = Channel<Unit>(Channel.BUFFERED)
    override val authEvent = _authEvent.receiveAsFlow()

    override fun emitAuthEvent() {
        _authEvent.trySend(Unit)
    }
}
