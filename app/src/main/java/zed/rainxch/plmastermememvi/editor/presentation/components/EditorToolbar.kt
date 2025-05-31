package zed.rainxch.plmastermememvi.editor.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import zed.rainxch.plmastermememvi.R

@Composable
fun EditorToolbar(
    onSaveClick: () -> Unit,
    onAddTextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.End)
    ) {
        OutlinedButton(
            onClick = onAddTextClick,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = stringResource(R.string.add_text))
        }
        Button(
            onClick = onSaveClick,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = stringResource(R.string.save_meme))
        }
    }
}