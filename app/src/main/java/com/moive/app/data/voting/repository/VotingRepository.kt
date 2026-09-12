package com.moive.app.data.voting.repository

import com.moive.app.data.voting.model.RegionPinModel

interface VotingRepository {
    suspend fun getRecommendedAreas(meetingId: Long): Result<List<RegionPinModel>>
}
