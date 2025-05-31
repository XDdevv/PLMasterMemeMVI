package zed.rainxch.plmastermememvi.editor.presentation.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import zed.rainxch.plmastermememvi.editor.presentation.EditorAction
import zed.rainxch.plmastermememvi.editor.presentation.EditorUiState

class EditorViewModel : ViewModel() {
    private val _state = MutableStateFlow(EditorUiState())
    val state = _state.asStateFlow()

    fun onAction(action: EditorAction) {
        when (action) {
            EditorAction.OnAddLabelClick -> {}
            EditorAction.OnBackClick -> {}
            EditorAction.OnContentEditDialogCancelClick -> {}
            EditorAction.OnContentEditDialogDoneClick -> {}
            EditorAction.OnLabelClicked -> {}
            EditorAction.OnLabelRemove -> {}
            EditorAction.OnTextDoubleClick -> {}
            is EditorAction.OnTextDrag -> {}
            is EditorAction.OnTextSizeChange -> {}
            EditorAction.OnTextToolbarCancelClick -> {}
            EditorAction.OnTextToolbarDoneClick -> {}
        }
    }
}