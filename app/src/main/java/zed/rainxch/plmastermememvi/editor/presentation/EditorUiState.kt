package zed.rainxch.plmastermememvi.editor.presentation

import androidx.compose.ui.geometry.Size
import zed.rainxch.plmastermememvi.editor.presentation.model.EditorActionBar
import zed.rainxch.plmastermememvi.editor.presentation.model.Label

data class EditorUiState(
    val labels: List<Label> = emptyList(),
    val scale: Float = 1f,
    val isGoingBack: Boolean = false,
    val currentSelectedLabel: Label? = null,
    val currentSelectedIndex: Int? = null,
    val lastSize: Float = currentSelectedLabel?.fontSize ?: 24f,
    val visibleToolbar: EditorActionBar = EditorActionBar.CREATE_LABEL_TOOLBAR,
    val canvasSize: Size = Size(0f, 0f),
    val isLeaveDialogVisible : Boolean = false,
    val isChangeLabelDialogVisible : Boolean = false
)
