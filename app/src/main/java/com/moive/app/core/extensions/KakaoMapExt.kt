package com.moive.app.core.extensions

import android.graphics.Bitmap
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.label.Label
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.kakao.vectormap.label.LabelTransition
import com.kakao.vectormap.label.Transition
import com.kakao.vectormap.route.RouteLine
import com.kakao.vectormap.route.RouteLineOptions
import com.kakao.vectormap.route.RouteLineSegment
import com.kakao.vectormap.route.RouteLineStyle
import com.kakao.vectormap.route.RouteLineStyles

fun KakaoMap.addBitmapMarker(
    position: LatLng,
    bitmap: Bitmap,
    anchorX: Float? = null,
    anchorY: Float? = null,
): Label? {
    val noTransition = LabelTransition.from(Transition.None, Transition.None)
    val labelStyle = LabelStyle.from(bitmap)
        .setIconTransition(noTransition)
        .setTextTransition(noTransition)
        .let { style ->
            if (anchorX != null && anchorY != null) style.setAnchorPoint(anchorX, anchorY) else style
        }
    val style = labelManager?.addLabelStyles(LabelStyles.from(labelStyle))
    val options = LabelOptions.from(position).setStyles(style)
    return labelManager?.layer?.addLabel(options)
}

fun KakaoMap.addRouteLine(
    points: List<LatLng>,
    lineWidth: Float,
    lineColor: Int,
): RouteLine? {
    val manager = routeLineManager ?: return null
    val styles = RouteLineStyles.from(RouteLineStyle.from(lineWidth, lineColor))
    val segment = RouteLineSegment.from(points, styles)
    return manager.layer?.addRouteLine(RouteLineOptions.from(segment))
}
