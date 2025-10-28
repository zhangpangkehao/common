package com.xiaojinzi.tally.module.core.module.bill_list.view

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
import com.xiaojinzi.support.bean.StringItemDto
import com.xiaojinzi.support.compose.StateBar
import com.xiaojinzi.support.ktx.initOnceUseViewModel
import com.xiaojinzi.tally.module.base.spi.TallyDataSourceSpi
import com.xiaojinzi.tally.module.base.support.AppRouterConfig
import com.xiaojinzi.tally.module.base.theme.AppTheme
import com.xiaojinzi.tally.module.base.view.BaseBusinessAct
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * 用户显示某些条件的账单.
 */
@RouterAnno(
    hostAndPath = AppRouterConfig.CORE_BILL_LIST,
)
@ViewLayer
class BillListAct : BaseBusinessAct<BillListViewModel>() {

    @AttrValueAutowiredAnno("title")
    var title: StringItemDto? = null

    @AttrValueAutowiredAnno("question")
    var question: TallyDataSourceSpi.Companion.BillQueryConditionDto? = null

    override fun getViewModelClass(): Class<BillListViewModel> {
        return BillListViewModel::class.java
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
            requiredViewModel()
                .billQueryConditionUseCase
                .queryConditionStateOb
                .value = question
        }

        setContent {
            AppTheme {
                StateBar {
                    BillListViewWrap(
                        title = title,
                    )
                }
            }
        }

    }

}