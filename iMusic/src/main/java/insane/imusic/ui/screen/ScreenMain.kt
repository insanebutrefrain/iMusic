// src/main/java/insane/imusic/ui/screen/ScreenMain.kt
package insane.imusic.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import insane.imusic.ui.componet.BottomBar
import insane.imusic.ui.componet.SnackBar
import insane.imusic.ui.hepler.HandleBack
import insane.imusic.ui.pager.pagers
import insane.imusic.viewModel.IMusicViewModel

/**
 * 主界面
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(viewModel: IMusicViewModel, navController: NavHostController) {
    val pageState = rememberPagerState(initialPage = 0, pageCount = { pagers.size })
    Scaffold(
        bottomBar = {
            BottomBar(pageState = pageState)
        },
        snackbarHost = {
            SnackBar(navController = navController, viewModel = viewModel)
        }
    ) { bottomPadding ->
        HandleBack()//两次左滑退出

        HorizontalPager(
            state = pageState,
            userScrollEnabled = false, // 禁用用户滚动
        ) { page: Int ->
            pagers[page].ui(bottomPadding, navController, viewModel)
        }

    }
}
