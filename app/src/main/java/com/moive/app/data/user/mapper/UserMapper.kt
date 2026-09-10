package com.moive.app.data.user.mapper

import com.moive.app.data.user.model.UserModel
import com.moive.app.data.user.remote.dto.UserResponse

fun UserResponse.toModel(): UserModel =
    UserModel(
        nickname = nickname,
        profileImageUrl = profileImageUrl,
        email = email,
    )
