package zed.rainxch.plmastermememvi.editor.presentation

import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import zed.rainxch.plmastermememvi.R
import zed.rainxch.plmastermememvi.core.domain.model.Template
import zed.rainxch.plmastermememvi.core.presentation.desingsystem.theme.PLMasterMemeMVITheme
import zed.rainxch.plmastermememvi.editor.presentation.components.EditorLabelBar
import zed.rainxch.plmastermememvi.editor.presentation.components.CreateLabelToolbar
import zed.rainxch.plmastermememvi.editor.presentation.components.EditLabelDialog
import zed.rainxch.plmastermememvi.editor.presentation.components.LeaveEditorDialog
import zed.rainxch.plmastermememvi.editor.presentation.components.drawScaledImage
import zed.rainxch.plmastermememvi.editor.presentation.components.drawTexts
import zed.rainxch.plmastermememvi.editor.presentation.model.EditorActionBar
import zed.rainxch.plmastermememvi.editor.presentation.vm.EditorViewModel
import androidx.core.graphics.createBitmap

@Composable
fun EditorScreenRoot(
    onBack: () -> Unit,
    template: Template,
) {
    val viewModel = viewModel<EditorViewModel>()
    val state by viewModel.state.collectAsState()

    EditorScreen(
        onBack = onBack,
        template = template,
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun EditorScreen(
    onBack: () -> Unit,
    template: Template,
    state: EditorUiState,
    onAction: (EditorAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
    ) {
        // Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
                .align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(
                onClick = {
                    onAction(EditorAction.OnShowLeaveEditorDialog)
                },
                modifier = Modifier.background(Color.Transparent)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Text(
                text = stringResource(R.string.new_meme),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(Modifier.width(52.dp))
        }

        // Image
        val context = LocalContext.current
        val imageBitmap = ImageBitmap.imageResource(template.imageRes)
        val closeIconBitmap = getImageBitmapFromDrawable(context, R.drawable.ic_close)
        val textMeasurer = rememberTextMeasurer()
        val density = LocalDensity.current
        Canvas(
            modifier = Modifier
                .size(380.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(4.dp))
                .clipToBounds()
                .pointerInput(Unit) {
                    detectTapGestures(
//                        onDoubleTap = { offset ->
//                            val clickedLabelIndex = state.labels.indexOfFirst {
//                                it.isLabelClicked(
//                                    offset,
//                                    textMeasurer,
//                                    density
//                                )
//                            }
//                            if (clickedLabelIndex != -1) {
//                                onAction(EditorAction.OnShowEditLabelDialog(clickedLabelIndex))
//                                onAction(EditorAction.OnCanvasDoubleTap(clickedLabelIndex))
//                            }
//                        }
//                        ,
                        onTap = { offset ->
                            val isIconClicked = state.currentSelectedLabel?.isIconClicked(offset, textMeasurer, density)
                            if (isIconClicked == true) {
                                println("ICON CLICKED")
                            } else {
                                println("its -1")
                            }
                        }
                    )
                }
        ) {
            onAction(EditorAction.OnCanvasReady(size = size))
            drawScaledImage(imageBitmap = imageBitmap)
            drawTexts(
                textMeasurer = textMeasurer,
                labels = state.labels,
                closeIconBitmap = closeIconBitmap
            )
        }

        // Bar with button (add; save)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
                .align(Alignment.BottomCenter)
        ) {
            if (state.visibleToolbar == EditorActionBar.CREATE_LABEL_TOOLBAR) {
                CreateLabelToolbar(
                    onSaveClick = {},
                    onAddLabelClick = {
                        onAction(EditorAction.OnAddLabelClick)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )
            } else if (state.visibleToolbar == EditorActionBar.LABEL_CHANGE_SIZE_TOOLBAR) {
                EditorLabelBar(
                    onCancelClick = {},
                    onDoneClick = {
                        onAction(EditorAction.OnTextToolbarDoneClick)
                    },
                    onFontSizeChange = {}
                )
            }
        }

        // Size bar
    }

    if (state.isLeaveDialogVisible) {
        LeaveEditorDialog(
            onLeaveClick = {
                onAction(EditorAction.OnHideLeaveEditorDialog)
                onBack()
            },
            onCancelClick = {
                onAction(EditorAction.OnHideLeaveEditorDialog)
            }
        )
    }

    if (state.isChangeLabelDialogVisible) {
        EditLabelDialog(
            content = state.currentSelectedLabel?.content ?: "",
            labelIndex = state.currentSelectedIndex ?: -1,
            onCancelClick = {
                onAction(EditorAction.OnHideEditLabelDialog)
            },
            onDoneClick = { index, newLabelText ->
                onAction(
                    EditorAction.OnLabelTextChangeClicked(
                        index,
                        newLabelText
                    )
                )
            }
        )
    }
}

fun getImageBitmapFromDrawable(context: Context, drawableId: Int): ImageBitmap {
    val drawable = AppCompatResources.getDrawable(context, drawableId)
        ?: throw IllegalArgumentException("Drawable not found")

    val bitmap = createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight)

    val canvas = android.graphics.Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return bitmap.asImageBitmap()
}


@Preview(showBackground = true)
@Composable
fun EditScreenPrev(
) {
    PLMasterMemeMVITheme {
        EditorScreen(
            onBack = {},
            modifier = Modifier,
            template = Template(R.drawable.temp_1),
            state = EditorUiState(),
            onAction = {}
        )
    }
}

