package com.xiaojinzi.tally.module.core.module.category_select1.view

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
import com.xiaojinzi.tally.module.core.module.category_select1.domain.CategorySelect1Intent
import kotlinx.coroutines.InternalCoroutinesApi

@RouterAnno(
    hostAndPath = AppRouterConfig.CORE_CATEGORY_SELECT1,
    interceptors = [
        AlphaInAnimInterceptor::class,
    ],
)
@ViewLayer
class CategorySelect1Act : BaseBusinessAct<CategorySelect1ViewModel>() {

    @AttrValueAutowiredAnno("bookId")
    lateinit var bookId: String

    @AttrValueAutowiredAnno("categoryIdList")
    var categoryIdList: ArrayList<String> = arrayListOf()

    override fun getViewModelClass(): Class<CategorySelect1ViewModel> {
        return CategorySelect1ViewModel::class.java
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
                intent = CategorySelect1Intent.ParameterInit(
                    bookId = bookId,
                    categoryIdSet = categoryIdList.toSet(),
                )
            )
        }

        setContent {
            AppTheme {
                StateBar {
                    CategorySelect1ViewWrap()
                }
            }
        }

    }

    override fun finish() {
        super.finish()
        this.alphaOutWhenFinish()
    }

}