package zed.rainxch.plmastermememvi.editor.presentation

import zed.rainxch.plmastermememvi.editor.presentation.model.CurrentAction
import zed.rainxch.plmastermememvi.editor.presentation.model.Label

data class EditorUiState(
    val labels: List<Label> = emptyList(),
    val scale: Float = 1f,
    val currentAction: CurrentAction = CurrentAction.SAVE_TOOLBAR,
    val isGoingBack: Boolean = false,
    val currentSelectedLabel: Label? = null,
    val lastSize: Float = currentSelectedLabel?.size ?: 24f
)
