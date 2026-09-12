package com.moive.app.data.voting.repository

import com.moive.app.data.voting.model.PlaceRecommendationCardItemModel
import com.moive.app.data.voting.model.RegionPinModel

interface VotingRepository {
    suspend fun getRecommendedAreas(meetingId: Long): Result<List<RegionPinModel>>

    suspend fun getRecommendedPlaces(
        meetingId: Long,
        recommendedAreaId: Long,
    ): Result<List<PlaceRecommendationCardItemModel>>
}
