package com.xiaojinzi.tally.module.core.module.account_detail.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.compose.util.circleClip
import com.xiaojinzi.support.compose.util.contentWithComposable
import com.xiaojinzi.support.ktx.format2f
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.orNull
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.model.support.rememberPainter
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_LARGE
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.module.base.module.common_bill_list.view.CommonBillListView
import com.xiaojinzi.tally.module.base.support.AppRouterCoreApi
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun AccountDetailView(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    val isAssetsVisible by AppServices.appConfigSpi.isAssetsVisibleStateOb.collectAsState(
        initial = false
    )
    BusinessContentView<AccountDetailViewModel>(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = AppBackgroundColor,
            )
            .nothing(),
        needInit = needInit,
    ) { vm ->
        val accountInfo by vm.accountInfoStateObVo.collectAsState(initial = null)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .nothing(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(ratio = 2f)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                    )
                    .nothing(),
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .nothing(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AppbarNormalM3(
                    title = "账户详情".toStringItemDto(),
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    menu1IconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_search1,
                    menu1ClickListener = {
                        accountInfo?.accountId.orNull()?.let { accountId ->
                            AppRouterCoreApi::class
                                .routeApi()
                                .toBillSearchView(
                                    context = context,
                                    accountIdList = arrayListOf(
                                        accountId
                                    ),
                                )
                        }
                    },
                )
                // 占位
                Spacer(
                    modifier = Modifier
                        .height(height = 20.dp)
                        .nothing()
                )
                Card(
                    modifier = Modifier
                        .padding(
                            horizontal = APP_PADDING_NORMAL.dp,
                            vertical = APP_PADDING_NORMAL.dp
                        )
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .nothing(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .nothing(),
                    ) {

                        val (
                            accountIcon, accountNameValue,
                            balanceName, balanceValue,
                            buttonEdit,
                        ) = createRefs()

                        Icon(
                            modifier = Modifier
                                .constrainAs(ref = accountIcon) {
                                    this.top.linkTo(
                                        anchor = parent.top,
                                        margin = APP_PADDING_LARGE.dp
                                    )
                                    this.start.linkTo(
                                        anchor = parent.start,
                                        margin = APP_PADDING_NORMAL.dp,
                                    )
                                }
                                .circleClip()
                                .background(
                                    color = AppBackgroundColor,
                                )
                                .padding(all = 6.dp)
                                .size(size = 24.dp)
                                .nothing(),
                            painter = accountInfo?.icon?.rememberPainter() ?: ColorPainter(
                                color = Color.Transparent,
                            ),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                        )

                        Text(
                            modifier = Modifier
                                .constrainAs(ref = accountNameValue) {
                                    this.start.linkTo(
                                        anchor = accountIcon.end,
                                        margin = APP_PADDING_NORMAL.dp
                                    )
                                    this.centerVerticallyTo(other = accountIcon)
                                }
                                .nothing(),
                            text = accountInfo?.name?.contentWithComposable() ?: "",
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                            ),
                            textAlign = TextAlign.Start,
                        )

                        Text(
                            modifier = Modifier
                                .constrainAs(ref = balanceName) {
                                    this.start.linkTo(
                                        anchor = parent.start,
                                        margin = APP_PADDING_NORMAL.dp
                                    )
                                    this.top.linkTo(
                                        anchor = accountNameValue.bottom,
                                        margin = 32.dp,
                                    )
                                    this.bottom.linkTo(
                                        anchor = parent.bottom,
                                        margin = APP_PADDING_LARGE.dp,
                                    )
                                }
                                .nothing(),
                            text = "余额",
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                            ),
                            textAlign = TextAlign.Start,
                        )

                        Text(
                            modifier = Modifier
                                .constrainAs(ref = balanceValue) {
                                    this.start.linkTo(
                                        anchor = balanceName.end,
                                        margin = APP_PADDING_NORMAL.dp,
                                    )
                                    this.centerVerticallyTo(other = balanceName)
                                }
                                .nothing(),
                            text = if (isAssetsVisible) {
                                accountInfo?.balanceCurrent?.value?.format2f() ?: "---"
                            } else {
                                "*****"
                            },
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onSurface,
                            ),
                            textAlign = TextAlign.Start,
                        )

                        Text(
                            modifier = Modifier
                                .constrainAs(ref = buttonEdit) {
                                    this.centerVerticallyTo(other = balanceValue)
                                    this.end.linkTo(
                                        anchor = parent.end,
                                        margin = APP_PADDING_NORMAL.dp,
                                    )
                                }
                                .circleClip()
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                )
                                .clickable {
                                    accountInfo?.accountId
                                        .orNull()
                                        ?.let {
                                            AppRouterCoreApi::class
                                                .routeApi()
                                                .toAccountCrudView(
                                                    context = context,
                                                    accountId = it,
                                                )
                                        }
                                }
                                .padding(
                                    horizontal = APP_PADDING_NORMAL.dp,
                                    vertical = APP_PADDING_SMALL.dp,
                                )
                                .nothing(),
                            text = "编辑账户",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary,
                            ),
                            textAlign = TextAlign.Start,
                        )

                    }
                }

                Row(
                    modifier = Modifier
                        .padding(
                            horizontal = APP_PADDING_NORMAL.dp,
                            vertical = APP_PADDING_NORMAL.dp
                        )
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .nothing(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button(
                        modifier = Modifier
                            .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                            .weight(weight = 1f, fill = true)
                            .wrapContentHeight()
                            .nothing(),
                        onClick = {
                            AppRouterCoreApi::class
                                .routeApi()
                                .toBillCrudView(
                                    context = context,
                                    billType = TallyBillDto.Type.TRANSFER,
                                    initTransferAccountId = accountInfo?.accountId.orNull(),
                                )
                        }
                    ) {
                        Text(
                            text = "去转账",
                            textAlign = TextAlign.Center,
                        )
                    }
                    Button(
                        modifier = Modifier
                            .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                            .weight(weight = 1f, fill = true)
                            .wrapContentHeight()
                            .nothing(),
                        onClick = {
                            AppRouterCoreApi::class
                                .routeApi()
                                .toBillCrudView(
                                    context = context,
                                    initAccountId = accountInfo?.accountId.orNull(),
                                )
                        }
                    ) {
                        Text(
                            text = "记一笔",
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                CommonBillListView(commonBillListViewUseCase = vm.commonBillListViewUseCase)
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
fun AccountDetailViewWrap() {
    AccountDetailView()
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun AccountDetailViewPreview() {
    AccountDetailView(
        needInit = false,
    )
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview(showSystemUi = true)
@Composable
private fun AccountDetailViewWrapPreview() {
    AccountDetailViewWrap()
}