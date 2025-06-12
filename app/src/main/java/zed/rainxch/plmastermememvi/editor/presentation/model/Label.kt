package zed.rainxch.plmastermememvi.editor.presentation.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import kotlin.math.pow
import kotlin.math.sqrt

data class Label(
    val content: String,
    val offset: Offset,
    val fontSize: Float = 24f,
    val isClicked: Boolean = false
) {
    fun calculateRectOffset(): Offset {
        return offset.copy(
            x = offset.x,
            y = offset.y,
        )
    }

    fun calculateRectSize(
        textMeasurer: TextMeasurer,
        density: Density
    ): Size {
        val textSize = textMeasurer.measure(content, getTextStyle(density, fontSize)).size
        return Size(
            width = textSize.width + 24f,
            height = textSize.height + 16f
        )
    }

    fun isLabelClicked(
        clickPosition: Offset,
        textMeasurer: TextMeasurer,
        density: Density
    ): Boolean {
        val rectOffset = calculateRectOffset()
        val rectSize = calculateRectSize(textMeasurer, density)
        return clickPosition.x in (rectOffset.x..rectOffset.x + rectSize.width) &&
                clickPosition.y in (rectOffset.y..rectOffset.y + rectSize.height)
    }

    fun calculateIconOffset(
        textMeasurer: TextMeasurer,
        density: Density
    ): Offset {
        val textSize = calculateRectSize(
            textMeasurer, density
        )
        val iconSize = with(density) { ICON_SIZE.dp.toPx() }
        return Offset(
            y = offset.y - (iconSize / 2),
            x = offset.x + textSize.width - (iconSize / 2)
        )
    }

    fun isIconClicked(
        clickPosition: Offset,
        textMeasurer: TextMeasurer,
        density: Density
    ): Boolean {
        val iconSize = with(density) { ICON_SIZE.dp.toPx() }
        val textSize = calculateRectSize(textMeasurer, density)
        val closeCircleOffset = Offset(
            y = offset.y,
            x = offset.x + textSize.width
        )
        val deltaX = clickPosition.x - closeCircleOffset.x
        val deltaY = clickPosition.y - closeCircleOffset.y
        return sqrt(
            deltaX.toDouble().pow(2.toDouble()) +
                    deltaY.toDouble().pow(2.toDouble())
        ) <= iconSize
    }

    companion object {
        fun getTextStyle(
            density: Density,
            fontSize: Float
        ): TextStyle {
            return TextStyle(fontSize = with(density) { fontSize.toSp() * 5 })
        }

        const val ICON_SIZE = 50f
    }
}
