package zed.rainxch.plmastermememvi.editor.presentation

import androidx.compose.ui.geometry.Offset

sealed interface EditorAction {
    data object OnBackClick : EditorAction
    data object OnAddLabelClick : EditorAction
    data object OnLabelClicked : EditorAction
    data object OnLabelRemove : EditorAction
    data class OnTextDrag(val newPosition: Offset) : EditorAction
    data class OnTextSizeChange(val newSize: Float) : EditorAction
    data object OnTextToolbarCancelClick : EditorAction
    data object OnTextToolbarDoneClick : EditorAction
    data object OnTextDoubleClick : EditorAction
    data object OnContentEditDialogDoneClick : EditorAction
    data object OnContentEditDialogCancelClick : EditorAction
}