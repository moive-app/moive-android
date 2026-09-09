package com.moive.app.presentation.voting.util

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
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

private val isKakaoMapEngineBusy = MutableStateFlow(false)

@Composable
fun rememberMapViewWithLifecycle(
    locationX: Double,
    locationY: Double,
    onMapReady: (KakaoMap) -> Unit,
): View {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            setFinishManually(true)
        }
    }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(lifecycle) {
        val observer = object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                coroutineScope.launch {
                    withTimeoutOrNull(2_000) {
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

                                onMapReady(kakaoMap)
                            }
                        }
                    )
                }
            }

            override fun onResume(owner: LifecycleOwner) {
                mapView.resume()
            }

            override fun onPause(owner: LifecycleOwner) {
                mapView.pause()
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
