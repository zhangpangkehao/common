package com.xiaojinzi.tally.module.core.module.bill_detail.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.util.circleClip
import com.xiaojinzi.support.compose.util.contentWithComposable
import com.xiaojinzi.support.ktx.commonTimeFormat1
import com.xiaojinzi.support.ktx.format2f
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.orNull
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.OssProcess
import com.xiaojinzi.tally.lib.res.model.support.rememberPainter
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.lib.res.ui.AppDivider
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.lib.res.ui.AppWidthSpace
import com.xiaojinzi.tally.module.base.support.AppRouterImagePreviewApi
import com.xiaojinzi.tally.module.base.view.compose.AppCommonCoilImage
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import com.xiaojinzi.tally.module.core.module.bill_detail.domain.BillDetailIntent
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(ExperimentalLayoutApi::class)
@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun BillDetailView(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    BusinessContentView<BillDetailViewModel>(
        needInit = needInit,
    ) { vm ->
        val billDetailInfo by vm.billDetailStateObVo.collectAsState(initial = null)
        val canEdit by vm.canEditStateOb.collectAsState(initial = false)
        val associatedRefundBillDetailListCount by vm.associatedRefundBillDetailListCountState.collectAsState(
            initial = 0
        )
        val billImageList by vm.billImageListStateOb.collectAsState(initial = emptyList())
        if (billDetailInfo != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                            elevation = 1.dp,
                        )
                    )
                    .nothing(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .nothing(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    AppHeightSpace()

                    Row(
                        modifier = Modifier
                            .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clip(
                                shape = MaterialTheme.shapes.small,
                            )
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                            )
                            .padding(
                                horizontal = APP_PADDING_NORMAL.dp,
                                vertical = APP_PADDING_NORMAL.dp
                            )
                            .nothing(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        when (billDetailInfo?.type) {
                            TallyBillDto.Type.NORMAL,
                            TallyBillDto.Type.REFUND -> {
                                Icon(
                                    modifier = Modifier
                                        .circleClip()
                                        .background(
                                            color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                                elevation = 1.dp,
                                            )
                                        )
                                        .padding(
                                            horizontal = 4.dp,
                                            vertical = 4.dp,
                                        )
                                        .size(size = 24.dp)
                                        .nothing(),
                                    painter = billDetailInfo?.categoryImage?.rememberPainter()
                                        ?: painterResource(
                                            id = com.xiaojinzi.tally.lib.res.R.drawable.res_forbid1,
                                        ),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurface,
                                )

                                AppWidthSpace()

                                Text(
                                    text = billDetailInfo?.categoryName?.contentWithComposable()
                                        ?: "无类别",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.onSurface,
                                    ),
                                    textAlign = TextAlign.Start,
                                )
                            }

                            TallyBillDto.Type.TRANSFER -> {
                                Text(
                                    text = "转账",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.onSurface,
                                    ),
                                    textAlign = TextAlign.Start,
                                )
                            }

                            else -> {}
                        }

                        Spacer(modifier = Modifier.weight(weight = 1f, fill = true))

                        Text(
                            text = billDetailInfo?.amount?.value?.format2f() ?: "",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                MaterialTheme.colorScheme.onSurface,
                            ),
                            textAlign = TextAlign.Start,
                        )

                    }

                    AppHeightSpace()

                    Column(
                        modifier = Modifier
                            .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clip(
                                shape = MaterialTheme.shapes.small,
                            )
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                            )
                            .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                            .nothing(),
                    ) {

                        billDetailInfo?.userInfoCache?.let { userInfo ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(horizontal = 0.dp, vertical = APP_PADDING_NORMAL.dp)
                                    .nothing(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Text(
                                    text = "创建用户",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
                                    ),
                                    textAlign = TextAlign.Start,
                                )
                                Text(
                                    text = userInfo.name.orEmpty(),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.onSurface,
                                    ),
                                    textAlign = TextAlign.Start,
                                )
                            }

                            AppDivider()
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(horizontal = 0.dp, vertical = APP_PADDING_NORMAL.dp)
                                .nothing(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "时间",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                ),
                                textAlign = TextAlign.Start,
                            )
                            Text(
                                text = billDetailInfo?.time?.commonTimeFormat1() ?: "",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                ),
                                textAlign = TextAlign.Start,
                            )
                        }

                        AppDivider()

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(horizontal = 0.dp, vertical = APP_PADDING_NORMAL.dp)
                                .nothing(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "账本",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                ),
                                textAlign = TextAlign.Start,
                            )
                            Text(
                                text = billDetailInfo?.bookName?.contentWithComposable() ?: "无账本",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                ),
                                textAlign = TextAlign.Start,
                            )
                        }

                        when (billDetailInfo?.type) {
                            TallyBillDto.Type.NORMAL,
                            TallyBillDto.Type.REFUND -> {
                                AppDivider()
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .padding(horizontal = 0.dp, vertical = APP_PADDING_NORMAL.dp)
                                        .nothing(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Text(
                                        text = "账户",
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            color = MaterialTheme.colorScheme.onSurface,
                                        ),
                                        textAlign = TextAlign.Start,
                                    )
                                    Text(
                                        text = billDetailInfo?.accountName?.contentWithComposable()
                                            ?: "无账户",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Medium,
                                            color = MaterialTheme.colorScheme.onSurface,
                                        ),
                                        textAlign = TextAlign.Start,
                                    )
                                }
                            }

                            TallyBillDto.Type.TRANSFER -> {
                                AppDivider()
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .padding(horizontal = 0.dp, vertical = APP_PADDING_NORMAL.dp)
                                        .nothing(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Text(
                                        text = "转出账户",
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            color = MaterialTheme.colorScheme.onSurface,
                                        ),
                                        textAlign = TextAlign.Start,
                                    )
                                    Text(
                                        text = billDetailInfo?.accountName?.contentWithComposable()
                                            ?: "无账户",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Medium,
                                            color = MaterialTheme.colorScheme.onSurface,
                                        ),
                                        textAlign = TextAlign.Start,
                                    )
                                }
                                AppDivider()
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .padding(horizontal = 0.dp, vertical = APP_PADDING_NORMAL.dp)
                                        .nothing(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Text(
                                        text = "接受账户",
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            color = MaterialTheme.colorScheme.onSurface,
                                        ),
                                        textAlign = TextAlign.Start,
                                    )
                                    Text(
                                        text = billDetailInfo?.transferTargetAccountName?.contentWithComposable()
                                            ?: "无账户",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Medium,
                                            color = MaterialTheme.colorScheme.onSurface,
                                        ),
                                        textAlign = TextAlign.Start,
                                    )
                                }
                            }

                            else -> {}
                        }

                        AppDivider()

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(horizontal = 0.dp, vertical = APP_PADDING_NORMAL.dp)
                                .nothing(),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "标签",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                ),
                                textAlign = TextAlign.Start,
                            )
                            billDetailInfo?.labelList?.let { labelList ->
                                FlowRow(
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .nothing(),
                                    horizontalArrangement = Arrangement.spacedBy(
                                        space = 4.dp,
                                    ),
                                    verticalArrangement = Arrangement.spacedBy(
                                        space = 4.dp,
                                    ),
                                ) {
                                    labelList.forEach { item ->
                                        Text(
                                            modifier = Modifier
                                                .circleClip()
                                                .background(
                                                    color = MaterialTheme.colorScheme.secondaryContainer,
                                                )
                                                .padding(horizontal = 5.dp, vertical = 2.dp)
                                                .nothing(),
                                            text = item.name.orEmpty(),
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                            ),
                                            textAlign = TextAlign.Start,
                                        )
                                    }
                                }
                            }

                        }

                    }

                    if (billDetailInfo?.type == TallyBillDto.Type.REFUND) {
                        AppHeightSpace()
                        Row(
                            modifier = Modifier
                                .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clip(
                                    shape = MaterialTheme.shapes.small,
                                )
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                )
                                .clickable {
                                    vm.addIntent(
                                        intent = BillDetailIntent.ToOriginBillDetailView(
                                            context = context,
                                        )
                                    )
                                }
                                .padding(
                                    horizontal = APP_PADDING_NORMAL.dp,
                                    vertical = APP_PADDING_NORMAL.dp
                                )
                                .nothing(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "原订单",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                ),
                                textAlign = TextAlign.Start,
                            )
                            Icon(
                                modifier = Modifier
                                    .size(size = 20.dp)
                                    .nothing(),
                                painter = rememberVectorPainter(image = Icons.AutoMirrored.Filled.KeyboardArrowRight),
                                contentDescription = null,
                            )
                        }
                    }

                    if (associatedRefundBillDetailListCount > 0) {
                        AppHeightSpace()
                        Row(
                            modifier = Modifier
                                .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clip(
                                    shape = MaterialTheme.shapes.small,
                                )
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                )
                                .clickable {
                                    vm.addIntent(
                                        intent = BillDetailIntent.ToAssociatedRefundBillDetailListView(
                                            context = context,
                                        )
                                    )
                                }
                                .padding(
                                    horizontal = APP_PADDING_NORMAL.dp,
                                    vertical = APP_PADDING_NORMAL.dp
                                )
                                .nothing(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "关联的 $associatedRefundBillDetailListCount 个退款账单",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                ),
                                textAlign = TextAlign.Start,
                            )
                            Icon(
                                modifier = Modifier
                                    .size(size = 20.dp)
                                    .nothing(),
                                painter = rememberVectorPainter(image = Icons.AutoMirrored.Filled.KeyboardArrowRight),
                                contentDescription = null,
                            )
                        }
                    }

                    AppHeightSpace()

                    Text(
                        modifier = Modifier
                            .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clip(
                                shape = MaterialTheme.shapes.small,
                            )
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                            )
                            .padding(
                                horizontal = APP_PADDING_NORMAL.dp,
                                vertical = APP_PADDING_NORMAL.dp
                            )
                            .nothing(),
                        text = billDetailInfo?.note.orNull() ?: "无备注",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            color = if (billDetailInfo?.note.orNull() == null) {
                                MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.5f,
                                )
                            } else {
                                MaterialTheme.colorScheme.onSurface
                            }
                        ),
                        textAlign = TextAlign.Start,
                    )

                    if (billImageList.isNotEmpty()) {
                        AppHeightSpace()
                        Row(
                            modifier = Modifier
                                .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clip(
                                    shape = MaterialTheme.shapes.small,
                                )
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                )
                                .padding(
                                    horizontal = APP_PADDING_NORMAL.dp,
                                    vertical = APP_PADDING_NORMAL.dp
                                )
                                .nothing(),
                        ) {
                            (0..2).forEach { index ->
                                if (index > 0) {
                                    AppWidthSpace()
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(weight = 1f, fill = true)
                                        .aspectRatio(ratio = 1f)
                                        .clip(
                                            shape = MaterialTheme.shapes.small,
                                        )
                                        .clickable {
                                            AppRouterImagePreviewApi::class
                                                .routeApi()
                                                .toImagePreviewView(
                                                    context = context,
                                                    urlList = ArrayList(
                                                        billImageList.map { it.url.orEmpty() }
                                                    ),
                                                    index = index,
                                                )
                                        }
                                        .nothing(),
                                ) {
                                    billImageList.getOrNull(index = index)?.let { item ->
                                        AppCommonCoilImage(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .nothing(),
                                            cosImageProcess = OssProcess.THUMBNAIL400,
                                            imageRes = item.url,
                                        )
                                    }
                                }
                            }
                        }
                    }

                }
                if (canEdit) {
                    Row(
                        modifier = Modifier
                            .align(alignment = Alignment.BottomCenter)
                            .navigationBarsPadding()
                            .padding(bottom = APP_PADDING_SMALL.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .nothing(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(weight = 2f, fill = true)
                                .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                                .circleClip()
                                .clickable {
                                    vm.addIntent(
                                        intent = BillDetailIntent.ToDeleteBill,
                                    )
                                }
                                .background(
                                    color = MaterialTheme.colorScheme.error,
                                )
                                .padding(horizontal = 0.dp, vertical = APP_PADDING_NORMAL.dp)
                                .nothing(),
                            text = "删除",
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = MaterialTheme.colorScheme.onError,
                            ),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            modifier = Modifier
                                .weight(weight = 3f, fill = true)
                                .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                                .circleClip()
                                .clickable {
                                    vm.addIntent(
                                        intent = BillDetailIntent.ToEditBill(
                                            context = context,
                                        ),
                                    )
                                }
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                )
                                .padding(horizontal = 0.dp, vertical = APP_PADDING_NORMAL.dp)
                                .nothing(),
                            text = "编辑",
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = MaterialTheme.colorScheme.onPrimary,
                            ),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun BillDetailViewWrap() {
    val context = LocalContext.current
    val vm: BillDetailViewModel = viewModel()
    val billDetailVo by vm.billDetailStateObVo.collectAsState(null)
    val isSupportRefund by vm.isSupportRefundState.collectAsState(initial = false)
    Scaffold(
        topBar = {
            AppbarNormalM3(
                title = when (billDetailVo?.type) {
                    TallyBillDto.Type.NORMAL -> "账单详情"
                    TallyBillDto.Type.TRANSFER -> "账单详情 - 转账"
                    TallyBillDto.Type.REFUND -> "账单详情 - 退款"
                    else -> ""
                }.toStringItemDto(),
                menu1IconRsd = if (isSupportRefund) {
                    com.xiaojinzi.tally.lib.res.R.drawable.res_more3
                } else {
                    null
                },
                menu1ClickListener = {
                    vm.addIntent(
                        BillDetailIntent.ToSelectMenu(
                            context = context,
                        )
                    )
                },
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nothing(),
        ) {
            BillDetailView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun BillDetailViewPreview() {
    BillDetailView(
        needInit = false,
    )
}