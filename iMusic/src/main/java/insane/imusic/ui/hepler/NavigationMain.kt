package insane.imusic.ui.hepler

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import insane.imusic.ui.screen.MainScreen
import insane.imusic.ui.screen.PlayingScreen
import insane.imusic.viewModel.IMusicViewModel

//import insane.imusic.viewModel.IMusicViewModel

/**
 * 主页面导航
 */

@Composable
fun MainNavigation(viewModel: IMusicViewModel) {
    // 导航控制器
    val navController: NavHostController = rememberNavController()
    // 当前屏幕
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            // 主界面
            MainScreen(viewModel = viewModel, navController = navController)
        }
        composable("playing") {
            // 正在播放界面
            PlayingScreen(viewModel = viewModel,navController = navController)
        }
    }
}