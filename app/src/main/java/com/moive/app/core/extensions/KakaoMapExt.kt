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

fun KakaoMap.addBitmapMarker(
    position: LatLng,
    bitmap: Bitmap,
): Label? {
    val noTransition = LabelTransition.from(Transition.None, Transition.None)
    val style = labelManager?.addLabelStyles(
        LabelStyles.from(
            LabelStyle.from(bitmap)
                .setIconTransition(noTransition)
                .setTextTransition(noTransition),
        ),
    )
    val options = LabelOptions.from(position).setStyles(style)
    return labelManager?.layer?.addLabel(options)
}
