package com.moive.app.data.auth.mapper

import com.moive.app.data.auth.model.ReissueModel
import com.moive.app.data.auth.remote.dto.ReissueResponse

fun ReissueResponse.toModel(): ReissueModel =
    ReissueModel(
        accessToken = accessToken,
        refreshToken = refreshToken,
    )
