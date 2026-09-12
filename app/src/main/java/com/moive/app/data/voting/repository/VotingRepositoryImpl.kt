package com.moive.app.data.voting.repository

import com.moive.app.core.utils.suspendRunCatching
import com.moive.app.data.common.dto.checkData
import com.moive.app.data.voting.mapper.toModel
import com.moive.app.data.voting.model.PlaceRecommendedPlaceCardItemModel
import com.moive.app.data.voting.model.RecommendedPlaceDetailModel
import com.moive.app.data.voting.model.RegionPinModel
import com.moive.app.data.voting.remote.datasource.VotingRemoteDataSource
import javax.inject.Inject

class VotingRepositoryImpl @Inject constructor(
    private val votingRemoteDataSource: VotingRemoteDataSource,
) : VotingRepository {

    override suspend fun getRecommendedAreas(meetingId: Long): Result<List<RegionPinModel>> =
        suspendRunCatching {
            votingRemoteDataSource.getRecommendedAreas(meetingId)
                .checkData()
                .areas
                .map { it.toModel() }
        }

    override suspend fun getRecommendedPlaces(
        meetingId: Long,
        recommendedAreaId: Long,
    ): Result<List<PlaceRecommendedPlaceCardItemModel>> =
        suspendRunCatching {
            votingRemoteDataSource.getRecommendedPlaces(meetingId, recommendedAreaId)
                .checkData()
                .places
                .map { it.toModel() }
        }

    override suspend fun getRecommendedPlaceDetail(
        meetingId: Long,
        recommendedAreaId: Long,
        recommendedPlaceId: Long,
    ): Result<RecommendedPlaceDetailModel> =
        suspendRunCatching {
            votingRemoteDataSource.getRecommendedPlaceDetail(meetingId, recommendedAreaId, recommendedPlaceId)
                .checkData()
                .toModel()
        }
}
