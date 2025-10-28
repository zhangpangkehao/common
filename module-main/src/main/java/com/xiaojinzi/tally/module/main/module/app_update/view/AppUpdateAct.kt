package com.xiaojinzi.tally.module.main.module.app_update.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.view.WindowCompat
import com.xiaojinzi.component.anno.AttrValueAutowiredAnno
import com.xiaojinzi.component.anno.RouterAnno
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.compose.StateBar
import com.xiaojinzi.support.ktx.initOnceUseViewModel
import com.xiaojinzi.tally.lib.res.model.app_update.AppUpdateResDto
import com.xiaojinzi.tally.module.base.support.AppRouterConfig
import com.xiaojinzi.tally.module.base.theme.AppTheme
import com.xiaojinzi.tally.module.base.view.BaseBusinessAct
import kotlinx.coroutines.InternalCoroutinesApi

@RouterAnno(
    hostAndPath = AppRouterConfig.MAIN_APP_UPDATE,
    interceptors = [
        AppUpdateRouterInterceptor::class,
    ]
)
@ViewLayer
class AppUpdateAct : BaseBusinessAct<AppUpdateViewModel>() {

    @AttrValueAutowiredAnno("appInfo")
    lateinit var appInfo: AppUpdateResDto

    override fun getViewModelClass(): Class<AppUpdateViewModel> {
        return AppUpdateViewModel::class.java
    }

    @OptIn(
        InternalCoroutinesApi::class,
        ExperimentalMaterial3Api::class,
        ExperimentalAnimationApi::class,
        ExperimentalFoundationApi::class,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        initOnceUseViewModel {
            requiredViewModel().appInfoInitData.value = appInfo
        }

        setContent {
            AppTheme {
                StateBar {
                    AppUpdateViewWrap()
                }
            }
        }

    }

}