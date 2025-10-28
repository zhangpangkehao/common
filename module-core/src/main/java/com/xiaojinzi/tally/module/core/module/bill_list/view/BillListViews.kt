package com.xiaojinzi.tally.module.core.module.bill_list.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.bean.StringItemDto
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.module.base.module.common_bill_list.view.CommonBillListView
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun BillListView(
    needInit: Boolean? = null,
) {
    val context = LocalContext.current
    BusinessContentView<BillListViewModel>(
        needInit = needInit,
    ) { vm ->
        CommonBillListView(
            modifier = Modifier
                .fillMaxSize()
                .nothing(),
            commonBillListViewUseCase = vm.commonBillListViewUseCase,
        )
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun BillListViewWrap(title: StringItemDto? = null) {
    Scaffold(
        topBar = {
            AppbarNormalM3(
                title = title,
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nothing(),
        ) {
            BillListView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun BillListViewPreview() {
    BillListView(
        needInit = false,
    )
}