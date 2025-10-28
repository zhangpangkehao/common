package com.xiaojinzi.tally.module.core.module.book_select1.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.view.WindowCompat
import com.xiaojinzi.component.anno.AttrValueAutowiredAnno
import com.xiaojinzi.component.anno.RouterAnno
import com.xiaojinzi.module.common.base.interceptor.AlphaInAnimInterceptor
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.compose.StateBar
import com.xiaojinzi.support.ktx.initOnceUseViewModel
import com.xiaojinzi.tally.module.base.support.AppRouterConfig
import com.xiaojinzi.tally.module.base.support.alphaOutWhenFinish
import com.xiaojinzi.tally.module.base.theme.AppTheme
import com.xiaojinzi.tally.module.base.view.BaseBusinessAct
import com.xiaojinzi.tally.module.core.module.book_select.domain.BookSelectIntent
import com.xiaojinzi.tally.module.core.module.book_select.view.BookSwitchViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@RouterAnno(
    hostAndPath = AppRouterConfig.CORE_BOOK_SELECT1,
    interceptors = [
        AlphaInAnimInterceptor::class,
    ],
)
@ViewLayer
class BookSelect1Act : BaseBusinessAct<BookSwitchViewModel>() {

    @AttrValueAutowiredAnno("maxCount")
    var maxCount: Int? = null

    @AttrValueAutowiredAnno("bookIdList")
    var bookIdList: ArrayList<String> = arrayListOf()

    override fun getViewModelClass(): Class<BookSwitchViewModel> {
        return BookSwitchViewModel::class.java
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
                intent = BookSelectIntent.ParameterInit(
                    maxCount = maxCount,
                    bookIdSelectList = bookIdList.toSet(),
                )
            )
        }

        setContent {
            AppTheme {
                StateBar {
                    BookSelect1ViewWrap()
                }
            }
        }

    }

    override fun finish() {
        super.finish()
        this.alphaOutWhenFinish()
    }

}