package com.moive.app.presentation.condition

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Immutable
import com.moive.app.data.condition.model.PlaceSearchItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

interface ConditionContract {
    @Immutable
    data class State(
        val step: Step = Step.INPUT,
        val isDateBottomSheetVisible: Boolean = false,
        val selectedDateText: String? = null,
        val searchFieldState: TextFieldState = TextFieldState(initialText = ""),
        val placeList: ImmutableList<PlaceSearchItemModel> = persistentListOf(
            PlaceSearchItemModel(
                id = 1L,
                name = "꽃뫼버들마을금강KCC아파트",
                address = "경기 수원시 팔달구 정자천로 32번길 27",
            ),
            PlaceSearchItemModel(
                id = 2L,
                name = "꽃뫼버들마을금강KCC아파트",
                address = "경기 수원시 팔달구 정자천로 32번길 27",
            ),
            PlaceSearchItemModel(
                id = 3L,
                name = "꽃뫼버들마을금강KCC아파트",
                address = "경기 수원시 팔달구 정자천로 32번길 27",
            ),
        ),
        val selectedPlaceId: Long? = null,
        val selectedPlaceName: String? = null,
        val selectedTravelTime: String? = null,
        val selectedPreferences: PersistentList<String> = persistentListOf(),
    ) {
        val travelTimeList: ImmutableList<String>
            get() = TRAVEL_TIME_OPTIONS

        val preferenceCategories: ImmutableList<PreferenceCategory>
            get() = PREFERENCE_CATEGORIES

        val isNextButtonEnabled: Boolean
            get() = selectedPlaceId != null &&
                selectedTravelTime != null &&
                selectedPreferences.isNotEmpty()

        companion object {
            private val TRAVEL_TIME_OPTIONS = persistentListOf(
                "30분 이내",
                "1시간 이내",
                "1시간 30분 이내",
                "상관 없어요",
            )

            private val PREFERENCE_CATEGORIES = persistentListOf(
                PreferenceCategory(
                    title = "놀거리",
                    items = persistentListOf("보드게임", "볼링", "방탈출", "노래방", "PC방", "만화카페"),
                ),
                PreferenceCategory(
                    title = "문화·관람",
                    items = persistentListOf("영화관", "공연", "전시·미술관", "박물관"),
                ),
                PreferenceCategory(
                    title = "먹고 마시기",
                    items = persistentListOf("한식", "양식", "중식", "일식", "고기", "해산물", "카페·디저트"),
                ),
                PreferenceCategory(
                    title = "나들이·쇼핑",
                    items = persistentListOf("공원", "산책", "등산", "쇼핑"),
                ),
            )
        }
    }

    enum class Step {
        INPUT,
        SEARCH,
        CONFIRM;
    }
}

@Immutable
data class PreferenceCategory(
    val title: String,
    val items: ImmutableList<String>,
)
