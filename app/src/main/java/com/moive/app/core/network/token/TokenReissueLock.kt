package com.moive.app.core.network.token

import kotlinx.coroutines.sync.Mutex
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenReissueLock @Inject constructor() {
    val mutex = Mutex()
}
