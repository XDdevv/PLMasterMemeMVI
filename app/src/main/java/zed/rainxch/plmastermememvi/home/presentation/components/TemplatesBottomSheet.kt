package zed.rainxch.plmastermememvi.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zed.rainxch.plmastermememvi.R
import zed.rainxch.plmastermememvi.core.domain.model.Template
import zed.rainxch.plmastermememvi.core.presentation.desingsystem.theme.PLMasterMemeMVITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplatesBottomSheet(
    onDismiss: () -> Unit,
    onTemplateClick : (Template) -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(
                text = stringResource(R.string.choose_template),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = stringResource(R.string.choose_template_for_your_next_meme_masterpiece),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(176.dp),
            ) {
                items(
                    items = Template.getTemplates(),
                    key = {
                        it.imageRes
                    }
                ) { item ->
                    Image(
                        painter = painterResource(item.imageRes),
                        contentDescription = stringResource(R.string.template_image),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(176.dp)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable{
                                onTemplateClick(item)
                            }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun TemplatesBottomSheetPreview() {
    PLMasterMemeMVITheme {
        TemplatesBottomSheet({}, {})
    }
}