package zed.rainxch.plmastermememvi.editor.presentation.components

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.style.TextOverflow
import zed.rainxch.plmastermememvi.core.presentation.desingsystem.theme.impactFont
import zed.rainxch.plmastermememvi.editor.presentation.model.Label
import zed.rainxch.plmastermememvi.editor.presentation.model.Label.Companion.ICON_SIZE

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

fun DrawScope.drawTexts(
    textMeasurer: TextMeasurer,
    labels: List<Label>,
    closeIconBitmap: ImageBitmap
) {
    labels.forEach { label ->
        val textSize = textMeasurer.measure(
            label.content,
            style = TextStyle(
                fontSize = label.fontSize.toSp() * 5,
                fontFamily = impactFont,
            )
        ).size
        val textOffset = label.calculateRectOffset()
        drawFilledOutlinedText(
            textMeasurer = textMeasurer,
            label = label,
        )
        if (label.isClicked) {
            val closeCircleOffset = Offset(
                y = textOffset.y,
                x = textOffset.x + textSize.width
            )
            val closeCircleIconOffset = Offset(
                y = textOffset.y - (closeIconBitmap.height / 2),
                x = textOffset.x + textSize.width - (closeIconBitmap.width / 2)
            )

            drawRoundRect(
                color = Color.White,
                topLeft = label.offset.copy(
                    x = label.offset.x - 12f,
                    y = label.offset.y - 4f,
                ),
                style = Stroke(6f),
                size = Size(
                    width = textSize.width + 24f,
                    height = textSize.height + 16f
                ),
                cornerRadius = CornerRadius(8f)
            )

            drawCircle(
                color = Color.Red,
                center = closeCircleOffset,
                radius = ICON_SIZE,
            )
            drawImage(
                image = closeIconBitmap,
                topLeft = closeCircleIconOffset
            )
        }
    }
}

fun DrawScope.drawFilledOutlinedText(
    label: Label,
    textMeasurer: TextMeasurer,
    borderColor: Color = Color.Black,
    innerColor: Color = Color.White
) {
    drawText(
        textMeasurer = textMeasurer,
        text = label.content,
        topLeft = label.offset,
        style = TextStyle(
            fontSize = label.fontSize.toSp() * 5,
            color = innerColor, // Fill color
            fontFamily = impactFont,
            drawStyle = Fill // Fill style,
        ),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
    drawText(
        textMeasurer = textMeasurer,
        text = label.content,
        topLeft = label.offset.copy(y = label.offset.y + 1f),
        style = TextStyle(
            fontSize = label.fontSize.toSp() * 5,
            color = borderColor, // Outline color
            fontFamily = impactFont,
            drawStyle = Stroke(width = 6f), // Outline stroke width
        ),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}