package zed.rainxch.plmastermememvi.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import zed.rainxch.plmastermememvi.core.presentation.navigation.NavGraph
import zed.rainxch.plmastermememvi.home.presentation.HomeScreen

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
            HomeScreen()
        }

//        composable<NavGraph.EditorScreen> {
//
//        }
    }
}