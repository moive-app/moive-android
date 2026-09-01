package com.moive.app.data.auth.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("agreements")
    val agreements: List<AgreementItem>,
)

@Serializable
data class AgreementItem(
    @SerialName("type")
    val type: AgreementType,
    @SerialName("version")
    val version: String,
    @SerialName("agreed")
    val agreed: Boolean,
)

@Serializable
enum class AgreementType {
    SERVICE,
    PRIVACY,
    MARKETING,
}
