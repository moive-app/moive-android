package com.moive.app.data.user.repository

import com.moive.app.data.user.model.UserModel

interface UserRepository {
    suspend fun getMyInfo(): Result<UserModel>
}
