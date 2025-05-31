package zed.rainxch.plmastermememvi.editor.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import zed.rainxch.plmastermememvi.R
import zed.rainxch.plmastermememvi.core.domain.model.Template
import zed.rainxch.plmastermememvi.core.presentation.desingsystem.theme.PLMasterMemeMVITheme
import zed.rainxch.plmastermememvi.editor.presentation.components.EditorLabelBar
import zed.rainxch.plmastermememvi.editor.presentation.components.EditorToolbar
import zed.rainxch.plmastermememvi.editor.presentation.model.EditorActionBar
import zed.rainxch.plmastermememvi.editor.presentation.vm.EditorViewModel

@Composable
fun EditorScreen(
    modifier: Modifier,
    onBack: () -> Unit,
    template: Template
) {
    val viewModel = viewModel<EditorViewModel>()
    val state by viewModel.state.collectAsState()
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
                onClick = { onBack() },
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
        val imageBitmap = ImageBitmap.imageResource(template.imageRes)
        Canvas(
            modifier = Modifier
                .size(380.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(4.dp))
                .clipToBounds()
        ) {
            drawScaledImage(imageBitmap = imageBitmap)
        }

        // Bar with button (add; save)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
                .align(Alignment.BottomCenter)
        ) {
            if (state.visibleToolbar == EditorActionBar.LABEL_TOOLBAR) {
                EditorToolbar(
                    onSaveClick = {},
                    onAddTextClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )
            } else {
                EditorLabelBar(
                    {},
                    {},
                    {}
                )
            }
        }

        // Size bar
    }
}

@Preview(showBackground = true)
@Composable
fun EditScreenPrev(
) {
    PLMasterMemeMVITheme {
        EditorScreen(
            onBack = {},
            modifier = Modifier,
            template = Template(R.drawable.temp_1)
        )
    }
}

fun DrawScope.drawScaledImage(imageBitmap: ImageBitmap) {
    val (imageWidth, imageHeight) = imageBitmap.width to imageBitmap.height
    val (width, height) = size.width to size.height

    val deltaX = (width - imageWidth) / 2
    val deltaY = (height - imageHeight) / 2

    val scale = maxOf(
        height / imageHeight,
        width / imageWidth
    )
    scale(
        scale = scale
    ) {
        drawImage(
            image = imageBitmap,
            topLeft = Offset(
                x = deltaX,
                y = deltaY,
            )
        )
    }
}
