package com.xiaojinzi.tally.module.core.module.account_detail.view

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
import com.xiaojinzi.tally.module.base.support.AppRouterConfig
import com.xiaojinzi.tally.module.base.theme.AppTheme
import com.xiaojinzi.tally.module.base.view.BaseBusinessAct
import com.xiaojinzi.tally.module.core.module.account_detail.domain.AccountDetailIntent
import kotlinx.coroutines.InternalCoroutinesApi

@RouterAnno(
    hostAndPath = AppRouterConfig.CORE_ACCOUNT_DETAIL,
)
@ViewLayer
class AccountDetailAct : BaseBusinessAct<AccountDetailViewModel>() {

    @AttrValueAutowiredAnno("accountId")
    var accountId: String? = null

    override fun getViewModelClass(): Class<AccountDetailViewModel> {
        return AccountDetailViewModel::class.java
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
                intent = AccountDetailIntent.ParameterDataInit(
                    accountId = accountId,
                )
            )
        }

        setContent {
            AppTheme {
                StateBar {
                    AccountDetailViewWrap()
                }
            }
        }

    }

}