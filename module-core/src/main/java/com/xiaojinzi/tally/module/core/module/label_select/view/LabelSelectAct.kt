package com.xiaojinzi.tally.module.core.module.label_select.view

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
import com.xiaojinzi.tally.module.core.module.label_select.domain.LabelSelectIntent
import kotlinx.coroutines.InternalCoroutinesApi

@RouterAnno(
    hostAndPath = AppRouterConfig.CORE_LABEL_SELECT,
)
@ViewLayer
class LabelSelectAct : BaseBusinessAct<LabelSelectViewModel>() {

    @AttrValueAutowiredAnno("bookId")
    var bookId: String? = null

    @AttrValueAutowiredAnno("idList")
    var idList: ArrayList<String> = arrayListOf()

    override fun getViewModelClass(): Class<LabelSelectViewModel> {
        return LabelSelectViewModel::class.java
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
                intent = LabelSelectIntent.ParameterInit(
                    bookId = bookId,
                    idList = idList.toSet(),
                )
            )
        }

        setContent {
            AppTheme {
                StateBar {
                    LabelSelectViewWrap()
                }
            }
        }

    }

}