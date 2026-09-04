package com.moive.app.presentation.voting

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

interface VotingContract {
    @Immutable
    data class State(
        val step: Step = Step.RECOMMENDATION,
        val isPlaceListVisible: Boolean = false,
        val locationX: Double = 127.0246, // 신논현역
        val locationY: Double = 37.5044, // 신논현역
        val regionName: String = "신논현동",
        val placeList: PersistentList<PlaceDetailModel> = places,
        val selectedPlaceList: PersistentSet<Long> = persistentSetOf(),
        val currentPlaceId: Long? = null,
    )

    enum class Step {
        RECOMMENDATION,
        DETAIL;
    }
}

//Todo: data layer로 이동
data class PlaceDetailModel(
    val id: Long,
    val name: String,
)

private val places = persistentListOf(
    PlaceDetailModel(id = 1L, name = "장소명(상호명) 1"),
    PlaceDetailModel(id = 2L, name = "장소명(상호명) 2"),
    PlaceDetailModel(id = 3L, name = "장소명(상호명) 3"),
)
