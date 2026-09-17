package com.moive.app.data.notification.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeviceTokenRequest(
    @SerialName("fcmToken")
    val fcmToken: String,
    @SerialName("deviceId")
    val deviceId: String,
)
