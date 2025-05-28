package zed.rainxch.plmastermememvi.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.serializer
import zed.rainxch.plmastermememvi.core.domain.model.Template
import zed.rainxch.plmastermememvi.core.presentation.navigation.NavGraph
import zed.rainxch.plmastermememvi.core.presentation.navigation.SerializableNavType
import zed.rainxch.plmastermememvi.editor.presentation.EditorScreen
import zed.rainxch.plmastermememvi.home.presentation.HomeScreen
import kotlin.reflect.typeOf

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavGraph.HomeScreen,
        modifier = modifier
    ) {
        composable<NavGraph.HomeScreen> {
            HomeScreen(onNavigateToEditorScreen = {
                navController.navigate(NavGraph.EditorScreen(it))
            })
        }

        composable<NavGraph.EditorScreen>(
            typeMap = mapOf(
                typeOf<Template>() to SerializableNavType.create(serializer<Template>())
            )
        ) {
            EditorScreen(
                onBack = { navController.popBackStack() },
                modifier = Modifier
            )
        }
    }
}