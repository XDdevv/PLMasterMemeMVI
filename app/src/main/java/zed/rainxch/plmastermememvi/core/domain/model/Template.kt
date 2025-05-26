package zed.rainxch.plmastermememvi.core.domain.model

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

@Serializable
data class Template(
    @DrawableRes val imageRes: Int
)
