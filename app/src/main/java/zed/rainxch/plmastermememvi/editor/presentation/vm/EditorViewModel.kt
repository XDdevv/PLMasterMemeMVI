package zed.rainxch.plmastermememvi.editor.presentation.vm

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import zed.rainxch.plmastermememvi.editor.presentation.EditorAction
import zed.rainxch.plmastermememvi.editor.presentation.EditorUiState
import zed.rainxch.plmastermememvi.editor.presentation.model.EditorActionBar
import zed.rainxch.plmastermememvi.editor.presentation.model.Label

class EditorViewModel : ViewModel() {
    private val _state = MutableStateFlow(EditorUiState())
    val state = _state.asStateFlow()

    fun onAction(action: EditorAction) {
        when (action) {
            EditorAction.OnAddLabelClick -> {
                val newLabel = createLabel()
                val newLabels = _state.value.labels + newLabel
                _state.update {
                    it.copy(
                        labels = newLabels,
                        currentSelectedLabel = newLabel,
                        visibleToolbar = EditorActionBar.LABEL_CHANGE_SIZE_TOOLBAR
                    )
                }
            }

            EditorAction.OnBackClick -> {}
            EditorAction.OnContentEditDialogCancelClick -> {}
            EditorAction.OnContentEditDialogDoneClick -> {}
            EditorAction.OnLabelClicked -> {}
            EditorAction.OnLabelRemove -> {}
            EditorAction.OnTextDoubleClick -> {}
            is EditorAction.OnTextDrag -> {}
            is EditorAction.OnTextSizeChange -> {}
            EditorAction.OnTextToolbarCancelClick -> {}
            EditorAction.OnTextToolbarDoneClick -> {
                _state.update { it.copy(visibleToolbar = EditorActionBar.CREATE_LABEL_TOOLBAR) }
            }

            is EditorAction.OnCanvasReady -> {
                _state.update { it.copy(canvasSize = action.size) }
            }

            EditorAction.OnHideLeaveEditorDialog -> {
                _state.update { it.copy(isLeaveDialogVisible = false) }
            }

            EditorAction.OnShowLeaveEditorDialog -> {
                _state.update { it.copy(isLeaveDialogVisible = true) }
            }

            is EditorAction.OnCanvasDoubleTap -> {
                _state.update {
                    it.copy(
                        currentSelectedLabel = _state.value.labels[action.index ?: -1],
                        currentSelectedIndex = action.index
                    )
                }
            }

            is EditorAction.OnShowEditLabelDialog -> {
                _state.update {
                    it.copy(
                        isChangeLabelDialogVisible = true
                    )
                }
            }

            EditorAction.OnHideEditLabelDialog -> {
                _state.update {
                    it.copy(
                        isChangeLabelDialogVisible = false
                    )
                }
            }

            is EditorAction.OnLabelTextChangeClicked -> {
                val newLabel = _state.value.labels[action.index].copy(content = action.newLabel)
                val newList = _state.value.labels.toMutableList()
                newList[action.index] = newLabel
                _state.update {
                    it.copy(
                        labels = newList,
                        isChangeLabelDialogVisible = false
                    )
                }
            }
        }
    }

    private fun createLabel(): Label {
        return Label(
            content = "TAP TWICE TO EDIT",
            offset = Offset(
                x = _state.value.canvasSize.width / 5f,
                y = _state.value.canvasSize.height / 2f,
            ),
            isClicked = true
        )
    }
}