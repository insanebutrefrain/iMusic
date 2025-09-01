package insane.imusic.ui.pager

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import insane.imusic.ui.pager.me.MeTopBar
import insane.imusic.ui.pager.me.PersonalInfo
import insane.imusic.ui.pager.me.MyListAndLocal
import insane.imusic.viewModel.IMusicViewModel

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MePager(
    bottomPadding: PaddingValues,
    navController: NavHostController,
    viewModel: IMusicViewModel
) {
    Scaffold(modifier = Modifier,
        topBar = {
            MeTopBar()
        }) { topPadding ->
        LazyColumn(
            modifier = Modifier.padding(
                // todo 添加上 SnakeBar的高度
                bottom = bottomPadding.calculateBottomPadding(),
                top = topPadding.calculateTopPadding()
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { PersonalInfo(viewModel = viewModel) }
            item { MyListAndLocal() }
        }
    }
}




