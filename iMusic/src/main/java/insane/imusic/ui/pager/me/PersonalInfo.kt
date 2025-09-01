package insane.imusic.ui.pager.me

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import insane.imusic.R
import insane.imusic.viewModel.IMusicViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PersonalInfo(viewModel: IMusicViewModel) {
    val me = viewModel.state.value.userVO
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(space = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.mipmap.default_icon),
            modifier = Modifier
                .clip(CircleShape)
                .size(100.dp),
            contentDescription = "用户头像"
        )
        // 昵称
        Text(
            text = me.value?.name ?: "未登录",
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 1
        )
        // 个性签名
        Text(
            text = me.value?.signs ?: "这个人很懒，什么都没写",
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1
        )

        Text(
            text = ((if (me.value?.gender == "男") "♂·" else {
                if (me.value?.gender == "女") "♀·" else ""
            }) + (me.value?.label ?: "无标签")),
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${me.value?.follows ?: 0} 关注",
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1
            )
            Text(
                text = "${me.value?.fans ?: 0} 粉丝",
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1
            )
            Text(
                text = "Lv.${(me.value?.listenTime ?: 0) / 60 / 80}",
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1
            )
            Text(
                text = "${(me.value?.listenTime ?: 0) / 60} 小时",
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1
            )
        }
    }
}