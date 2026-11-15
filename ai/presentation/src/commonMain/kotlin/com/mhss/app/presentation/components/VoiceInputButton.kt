package com.mhss.app.presentation.components

import androidx.compose.runtime.Composable

@Composable
expect fun VoiceInputButton(
    enabled: Boolean = true,
    onResult: (String) -> Unit,
)
