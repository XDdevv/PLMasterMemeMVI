package zed.rainxch.plmastermememvi.editor.presentation.model

import androidx.compose.ui.geometry.Offset

data class Label(
    val content: String,
    val offset: Offset,
    val size: Float = 24f
)
