package com.moive.app.presentation.voting.util

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraUpdateFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import timber.log.Timber
import kotlin.time.Duration.Companion.milliseconds

private val isKakaoMapEngineBusy = MutableStateFlow(false)

@Composable
fun rememberMapViewWithLifecycle(
    locationX: Double,
    locationY: Double,
    onMapReady: (KakaoMap) -> Unit,
    cornerRadius: Dp = 0.dp,
): View {
    val context = LocalContext.current
    val density = LocalDensity.current
    val mapView = remember {
        MapView(context).apply {
            isFinishManually = true
            if (cornerRadius > 0.dp) {
                clipToOutline = true
                outlineProvider = object : ViewOutlineProvider() {
                    override fun getOutline(view: View, outline: Outline) {
                        val radiusPx = with(density) { cornerRadius.toPx() }
                        outline.setRoundRect(0, 0, view.width, view.height, radiusPx)
                    }
                }
            }
        }
    }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val coroutineScope = rememberCoroutineScope()
    var isMapStarted by remember { mutableStateOf(false) }
    var isResumePending by remember { mutableStateOf(false) }

    DisposableEffect(lifecycle) {
        val observer = object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                coroutineScope.launch {
                    withTimeoutOrNull(2_000.milliseconds) {
                        isKakaoMapEngineBusy.first { busy -> !busy }
                    }

                    mapView.start(
                        object : MapLifeCycleCallback() {
                            override fun onMapDestroy() {
                                Timber.tag("KakaoMap").d("카카오 맵이 정상적으로 종료됐습니다.")
                                isKakaoMapEngineBusy.value = false
                            }

                            override fun onMapError(error: Exception) {
                                Timber.tag("KakaoMap").e("카카오 맵 사용 중 에러가 발생했습니다. $error")
                                isKakaoMapEngineBusy.value = false
                            }
                        },
                        object : KakaoMapReadyCallback() {
                            override fun getPosition(): LatLng {
                                return LatLng.from(locationY, locationX)
                            }

                            override fun onMapReady(kakaoMap: KakaoMap) {
                                val cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(locationY, locationX), 13)
                                kakaoMap.moveCamera(cameraUpdate)

                                isMapStarted = true
                                if (isResumePending) {
                                    isResumePending = false
                                    mapView.resume()
                                }

                                onMapReady(kakaoMap)
                            }
                        }
                    )
                }
            }

            override fun onResume(owner: LifecycleOwner) {
                if (isMapStarted) {
                    mapView.resume()
                } else {
                    isResumePending = true
                }
            }

            override fun onPause(owner: LifecycleOwner) {
                isResumePending = false
                if (isMapStarted) {
                    mapView.pause()
                }
            }

        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
            isKakaoMapEngineBusy.value = true
            mapView.finish()
        }
    }
    return mapView
}
