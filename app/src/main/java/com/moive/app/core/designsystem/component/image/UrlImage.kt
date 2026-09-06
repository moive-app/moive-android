package com.moive.app.core.designsystem.component.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.moive.app.R

@Composable
fun UrlImage(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = null,
) {
    if (LocalInspectionMode.current) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_background),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
    } else {
        AsyncImage(
            model = url,
            contentDescription = contentDescription,
            contentScale = contentScale,
            placeholder = painterResource(R.drawable.ic_launcher_background),
            error = painterResource(R.drawable.ic_launcher_background),
            modifier = modifier
        )
    }
}

@Preview
@Composable
fun UrlImagePreview() {
    UrlImage(
        url = "",
        modifier = Modifier.size(100.dp),
    )
}
