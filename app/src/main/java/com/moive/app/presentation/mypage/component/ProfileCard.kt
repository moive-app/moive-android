package com.moive.app.presentation.mypage.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.component.image.UrlImage
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius

@Composable
fun ProfileCard(
    profileImage: String,
    name: String,
    email: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        UrlImage(
            url = profileImage,
            modifier = modifier
                .size(52.dp)
                .clip(shape = RoundedCornerShape(radius.circular)),
            contentScale = ContentScale.Crop,
            placeholder = R.drawable.img_avatar_placeholder,
        )

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = name,
                color = colors.text.default,
                style = MoiveTheme.typography.title.lgB,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = email,
                color = colors.text.tertiary,
                style = MoiveTheme.typography.body.smNormalR,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
