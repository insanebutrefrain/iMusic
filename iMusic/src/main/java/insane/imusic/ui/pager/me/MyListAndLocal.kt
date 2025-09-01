package insane.imusic.ui.pager.me

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun MyListAndLocal() {
    Card(modifier = Modifier.fillMaxWidth()) {
        val pagerState =
            rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f, pageCount = { 2 })
        val scope = rememberCoroutineScope()
        Column {
            TabRow(
                selectedTabIndex = pagerState.currentPage
            ) {
                Tab(selected = true, onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(page = 0)
                    }
                }, content = {
                    Text(
                        text = "歌单", style = MaterialTheme.typography.headlineSmall
                    )
                })

                Tab(selected = true, onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            page = 1, animationSpec = tween(durationMillis = 500)
                        )
                    }
                }, content = {
                    Text(
                        text = "本地", style = MaterialTheme.typography.headlineSmall
                    )
                })
            }
            HorizontalPager(
                state = pagerState
            ) { page ->
                when (page) {
                    0 -> MyList()
                    1 -> Local()
                }
            }
        }
    }
}




