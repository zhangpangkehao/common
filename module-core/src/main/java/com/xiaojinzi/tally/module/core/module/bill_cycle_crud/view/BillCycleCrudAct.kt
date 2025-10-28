package com.xiaojinzi.tally.module.core.module.bill_cycle_crud.view

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
import com.xiaojinzi.tally.module.core.module.bill_cycle_crud.domain.BillCycleCrudIntent
import kotlinx.coroutines.InternalCoroutinesApi

@RouterAnno(
    hostAndPath = AppRouterConfig.CORE_BILL_CYCLE_CRUD,
    interceptors = [
        BillCycleCrudRouterInterceptor::class,
    ]
)
@ViewLayer
class BillCycleCrudAct : BaseBusinessAct<BillCycleCrudViewModel>() {

    @AttrValueAutowiredAnno("editId")
    var editId: Long? = null

    override fun getViewModelClass(): Class<BillCycleCrudViewModel> {
        return BillCycleCrudViewModel::class.java
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
                intent = BillCycleCrudIntent.ParameterInit(
                    editId = editId,
                )
            )
        }

        setContent {
            AppTheme {
                StateBar {
                    BillCycleCrudViewWrap()
                }
            }
        }

    }

}