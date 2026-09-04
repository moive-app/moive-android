package com.moive.app.core.designsystem.component.toast

import android.widget.Toast
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals

data class MoiveToastVisuals(
    override val message: String,
    val type: ToastType,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = false,
    override val duration: SnackbarDuration = SnackbarDuration.Indefinite,
) : SnackbarVisuals
