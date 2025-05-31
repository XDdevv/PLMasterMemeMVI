package zed.rainxch.plmastermememvi.editor.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorLabelBar(
    onCancelClick: () -> Unit,
    onDoneClick: () -> Unit,
    onFontSizeChange: (newSize: Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var currentSize by remember { mutableFloatStateOf(18f) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = onCancelClick,
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Cancel changes",
                tint = MaterialTheme.colorScheme.secondary
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Aa",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Slider(
                value = currentSize,
                onValueChange = { newSize ->
                    currentSize = newSize
                    onFontSizeChange(newSize)
                },
                valueRange = 12f..48f,
                track = {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.secondary)
                    )
                },
                thumb = {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondary.copy(alpha = .6f))
                            .padding(8.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondary)
                    )
                },
                modifier = Modifier.width(220.dp)
            )
            Text(
                text = "Aa",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
        }

        IconButton(
            onClick = onDoneClick,
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Save changes",
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}