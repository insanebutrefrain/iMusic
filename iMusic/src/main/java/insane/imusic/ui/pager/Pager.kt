package insane.imusic.ui.pager

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import insane.imusic.R
import insane.imusic.viewModel.IMusicViewModel

/**
 * 密封类，用于封装各个页面对象
 */
val pagers = listOf(Pager.Recommend, Pager.Me)

sealed class Pager(
    val route: String, // 唯一标识
    val title: String, // 标题
    val icon: Int, // 图标
    val ui: @Composable (bottomPadding: PaddingValues, navController: NavHostController, viewModel: IMusicViewModel) -> Unit,
) {
    // 推荐页面
    data object Recommend : Pager(
        route = "recommend",
        title = "推荐",
        icon = R.drawable.pager_recommend,
        ui = { bottomPadding: PaddingValues, navController: NavHostController, viewModel: IMusicViewModel ->
            RecommendPager(
                bottomPadding = bottomPadding,
                navController = navController,
                viewModel = viewModel
            )
        })

    // 私信页面
    data object Message : Pager(
        route = "message",
        title = "私信",
        icon = R.drawable.pager_message,
        ui = { bottomPadding: PaddingValues, navController: NavHostController, viewModel: IMusicViewModel ->
            MessagePager(
                bottomPadding = bottomPadding,
                navController = navController,
            )
        })

    // 我的页面
    data object Me : Pager(
        route = "me",
        title = "我的",
        icon = R.drawable.pager_me,
        ui = { bottomPadding: PaddingValues, navController: NavHostController, viewModel: IMusicViewModel ->
            MePager(
                bottomPadding = bottomPadding,
                navController = navController,
                viewModel = viewModel
            )
        })
}