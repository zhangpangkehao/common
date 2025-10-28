package com.xiaojinzi.tally.module.core.module.first_sync.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.view.WindowCompat
import com.xiaojinzi.component.anno.RouterAnno
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.compose.StateBar
import com.xiaojinzi.support.ktx.initOnceUseViewModel
import com.xiaojinzi.tally.module.base.support.AppRouterConfig
import com.xiaojinzi.tally.module.base.theme.AppTheme
import com.xiaojinzi.tally.module.base.view.BaseBusinessAct
import com.xiaojinzi.tally.module.core.module.first_sync.domain.FirstSyncIntent
import kotlinx.coroutines.InternalCoroutinesApi

@RouterAnno(
    hostAndPath = AppRouterConfig.CORE_FIRST_SYNC,
)
@ViewLayer
class FirstSyncAct : BaseBusinessAct<FirstSyncViewModel>() {

    override fun getViewModelClass(): Class<FirstSyncViewModel> {
        return FirstSyncViewModel::class.java
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
            requiredViewModel().addIntent(
                intent = FirstSyncIntent.StartInit(
                    context = mContext,
                ),
            )
        }

        setContent {
            AppTheme {
                StateBar {
                    FirstSyncViewWrap()
                }
            }
        }

    }

}