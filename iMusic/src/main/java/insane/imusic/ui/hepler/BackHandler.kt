package insane.imusic.ui.hepler

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * 控制两次左滑退出
 */
var lastTimeStamp: Long = 0L
@Composable
fun HandleBack() {
    val context = LocalContext.current
    BackHandler {
        val nowTimeStamp = System.currentTimeMillis()
        if (nowTimeStamp - lastTimeStamp > 1500) {
            Toast.makeText(context, "再次滑动退出", Toast.LENGTH_SHORT).show()
            lastTimeStamp = nowTimeStamp
        } else {
            lastTimeStamp = 0L
            (context as Activity).moveTaskToBack(true)
        }
    }
}