package zed.rainxch.plmastermememvi.core.domain.model

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable
import zed.rainxch.plmastermememvi.R

@Serializable
data class Template(
    @DrawableRes val imageRes: Int
) {
    companion object {
        fun getTemplates(): List<Template> {
            return listOf(
                Template(R.drawable.temp_1),
                Template(R.drawable.temp_2),
                Template(R.drawable.temp_3),
                Template(R.drawable.temp_4),
                Template(R.drawable.temp_5),
                Template(R.drawable.temp_6),
                Template(R.drawable.temp_7),
                Template(R.drawable.temp_8),
                Template(R.drawable.temp_9),
                Template(R.drawable.temp_10),
                Template(R.drawable.temp_11),
                Template(R.drawable.temp_12),
                Template(R.drawable.temp_13),
                Template(R.drawable.temp_14),
                Template(R.drawable.temp_15),
                Template(R.drawable.temp_16),
                Template(R.drawable.temp_17),
                Template(R.drawable.temp_18),
                Template(R.drawable.temp_19),
                Template(R.drawable.temp_20),
                Template(R.drawable.temp_21),
                Template(R.drawable.temp_22),
                Template(R.drawable.temp_23),
                Template(R.drawable.temp_24),
                Template(R.drawable.temp_25),
                Template(R.drawable.temp_26),
                Template(R.drawable.temp_27),
                Template(R.drawable.temp_28),
                Template(R.drawable.temp_29),
                Template(R.drawable.temp_30),
                Template(R.drawable.temp_31),
                Template(R.drawable.temp_32),
                Template(R.drawable.temp_33),
                Template(R.drawable.temp_35),
                Template(R.drawable.temp_36),
                Template(R.drawable.temp_37),
                Template(R.drawable.temp_38),
                Template(R.drawable.temp_39),
                Template(R.drawable.temp_40),
                Template(R.drawable.temp_41),
                Template(R.drawable.temp_42),
                Template(R.drawable.temp_43),
                Template(R.drawable.temp_44),
                Template(R.drawable.temp_45),
                Template(R.drawable.temp_46),
                Template(R.drawable.temp_47),
                Template(R.drawable.temp_48),
                Template(R.drawable.temp_49),
            )
        }
    }
}