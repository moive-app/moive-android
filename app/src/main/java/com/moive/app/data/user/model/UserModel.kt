package com.moive.app.data.user.model

import androidx.compose.runtime.Immutable

@Immutable
data class UserModel(
    val nickname: String,
    val profileImageUrl: String,
    val email: String,
)
