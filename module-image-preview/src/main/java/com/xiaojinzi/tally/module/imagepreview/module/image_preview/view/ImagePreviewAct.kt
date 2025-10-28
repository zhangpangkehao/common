package com.xiaojinzi.tally.module.imagepreview.module.image_preview.view

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
import kotlinx.coroutines.InternalCoroutinesApi

@RouterAnno(
    hostAndPath = AppRouterConfig.IMAGE_PREVIEW_MAIN,
)
@ViewLayer
class ImagePreviewAct : BaseBusinessAct<ImagePreviewViewModel>() {

    @AttrValueAutowiredAnno("urlList")
    lateinit var urlList: ArrayList<String>

    @AttrValueAutowiredAnno("index")
    var index: Int = 0

    override fun getViewModelClass(): Class<ImagePreviewViewModel> {
        return ImagePreviewViewModel::class.java
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
                    ImagePreviewViewWrap(
                        urlList = urlList.toList(),
                        index = index.coerceIn(
                            minimumValue = 0, maximumValue = urlList.lastIndex,
                        ),
                    )
                }
            }
        }

    }

}