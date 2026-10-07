package com.moive.app.core.analytics.event

sealed interface AnalyticsEvent {
    val name: String
    val properties: Map<String, Any?>
        get() = emptyMap()
}
