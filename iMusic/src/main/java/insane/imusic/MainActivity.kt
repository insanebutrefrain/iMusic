package insane.imusic

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import insane.imusic.ui.hepler.MainNavigation
import insane.imusic.ui.theme.IMusicTheme
import insane.imusic.viewModel.IMusicViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
            .create(IMusicViewModel::class.java)
        // 检查通知权限是否已经授予 注意：API 33以上版本需要检查POST_NOTIFICATIONS发布通知权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this, android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 0
                )
            }
        }
        setContent {
            IMusicTheme {
                MainNavigation(viewModel)
            }
        }
    }
}





