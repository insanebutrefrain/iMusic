package insane.imusic.ui.pager.recommend

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RecommendTopBar() {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "侧滑菜单"
                )
            }
        },
        actions = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "搜索"
                    )
                }

                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "更多"
                    )
                }
            }
        }
    )
}