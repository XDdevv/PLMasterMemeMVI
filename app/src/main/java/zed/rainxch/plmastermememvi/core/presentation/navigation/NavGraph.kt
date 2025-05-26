package zed.rainxch.plmastermememvi.core.presentation.navigation

import kotlinx.serialization.Serializable
import zed.rainxch.plmastermememvi.core.domain.model.Template

@Serializable
sealed class NavGraph {
    @Serializable
    object HomeScreen : NavGraph()

    @Serializable
    data class EditorScreen (
        val template: Template
    ) : NavGraph()
}