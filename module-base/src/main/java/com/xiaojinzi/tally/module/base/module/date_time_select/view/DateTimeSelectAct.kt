package com.xiaojinzi.tally.module.base.module.date_time_select.view

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
import com.xiaojinzi.tally.lib.res.model.support.DateTimeModel
import com.xiaojinzi.tally.lib.res.model.support.DateTimeType
import com.xiaojinzi.tally.module.base.support.AppRouterConfig
import com.xiaojinzi.tally.module.base.support.alphaOutWhenFinish
import com.xiaojinzi.tally.module.base.theme.AppTheme
import com.xiaojinzi.tally.module.base.view.BaseBusinessAct
import kotlinx.coroutines.InternalCoroutinesApi

@RouterAnno(
    hostAndPath = AppRouterConfig.BASE_DATE_TIME_SELECT,
    interceptors = [
        AlphaInAnimInterceptor::class,
    ],
)
@ViewLayer
class DateTimeSelectAct : BaseBusinessAct<DateTimeSelectViewModel>() {

    @AttrValueAutowiredAnno("time")
    var time: Long? = null

    @AttrValueAutowiredAnno("type")
    var type: DateTimeType = DateTimeType.Day

    @AttrValueAutowiredAnno("model")
    var model: DateTimeModel = DateTimeModel.Current

    override fun getViewModelClass(): Class<DateTimeSelectViewModel> {
        return DateTimeSelectViewModel::class.java
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
        }

        setContent {
            AppTheme {
                StateBar {
                    DateTimeSelectViewWrap(
                        time = time,
                        type = type,
                        model = model,
                    )
                }
            }
        }

    }

    override fun finish() {
        super.finish()
        this.alphaOutWhenFinish()
    }

}