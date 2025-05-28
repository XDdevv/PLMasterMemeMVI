package zed.rainxch.plmastermememvi.editor.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import zed.rainxch.plmastermememvi.R
import zed.rainxch.plmastermememvi.core.presentation.desingsystem.theme.PLMasterMemeMVITheme

@Composable
fun EditorScreen(
    modifier: Modifier,
    onBack: () -> Unit,

    ) {
    MaterialTheme.colorScheme

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceContainerLow),
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
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Image
        // Bar with button (add; save)
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
            modifier = Modifier
        )
    }
}
