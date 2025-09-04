package insane.imusic.ui.pager.me

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MeTopBar() {
    TopAppBar(
        title = {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Spacer(modifier = Modifier.width(30.dp))
//                Text(
//                    text = "状态",
//                    textAlign = TextAlign.Center,
//                    style = MaterialTheme.typography.labelSmall
//                )
//                Icon(
//                    modifier = Modifier
//                        .size(10.dp)
//                        .clickable {
//                            // todo 编辑状态
//                        },
//                    imageVector = Icons.Filled.Edit,
//                    contentDescription = "编辑状态",
//                    tint = Color.Gray
//                )
//            }
        },
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
            IconButton(onClick = {
                // todo 更多
            }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "更多"
                )
            }
        }
    )
}