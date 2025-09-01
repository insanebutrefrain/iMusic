package insane.imusic.ui.componet

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import insane.imusic.ui.pager.Pager
import insane.imusic.ui.pager.pagers
import kotlinx.coroutines.launch

/**
 * 底部导航栏
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomBar(pageState: PagerState) {
    BottomAppBar {
        pagers.forEachIndexed { index, pager ->
            val scope = rememberCoroutineScope()
            NavigationBarItem(
                interactionSource = MutableInteractionSource(),
                selected = (pageState.currentPage == index),
                onClick = {
                    // 确保跳转到正确的页面
                    scope.launch { pageState.scrollToPage(index) }
                },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(pager.icon),
                            contentDescription = pager.title,
                            tint = if (index == pageState.currentPage) Color.Red else Color.Unspecified
                        )
                        Text(
                            text = pager.title,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            )
        }
    }
}