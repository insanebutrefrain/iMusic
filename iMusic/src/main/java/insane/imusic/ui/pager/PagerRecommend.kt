// src/main/java/insane/imusic/ui/pager/PagerRecommend.kt
package insane.imusic.ui.pager

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import insane.imusic.ui.pager.recommend.RecommendTopBar
import insane.imusic.ui.pager.recommend.SongCard
import insane.imusic.viewModel.IMusicViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun RecommendPager(
    bottomPadding: PaddingValues,
    navController: NavHostController,
    viewModel: IMusicViewModel
) {
    val scope = rememberCoroutineScope()
    val state = viewModel.state
    // 按播放频率由高到低排序
    val songs = state.value.songs.sortedByDescending { it.frequency }
    Scaffold(
        modifier = Modifier,
        topBar = {
            RecommendTopBar()
        }
    ) { topPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = topPadding.calculateTopPadding(),
                    bottom = bottomPadding.calculateBottomPadding()
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(songs) { song ->
                SongCard(song = song, viewModel = viewModel)
            }
        }
    }
}
