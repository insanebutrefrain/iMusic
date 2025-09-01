package insane.imusic.ui.pager

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import insane.imusic.ui.hepler.HandleBack

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MessagePager(
    bottomPadding: PaddingValues,
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            //todo 顶部栏
        }
    ) { topPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = topPadding.calculateTopPadding(),
                    bottom = bottomPadding.calculateBottomPadding()
                ), contentAlignment = Alignment.Center
        ) {
            Text(text = "私信")
        }
    }
}