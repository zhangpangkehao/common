package com.xiaojinzi.tally.module.core.module.ai_bill_chat.view

import androidx.lifecycle.viewModelScope
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.StateHotObservable
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.ktx.sharedStateIn
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.core.module.ai_bill_chat.domain.AiBillChatUseCase
import com.xiaojinzi.tally.module.core.module.ai_bill_chat.domain.AiBillChatUseCaseImpl
import kotlinx.coroutines.flow.map

@ViewLayer
class AiBillChatViewModel(
    private val useCase: AiBillChatUseCase = AiBillChatUseCaseImpl(),
) : BaseViewModel(),
    AiBillChatUseCase by useCase {

    @StateHotObservable
    val billChatListStateObVo = useCase
        .billChatListStateOb
        .map { list ->
            list.map { item ->
                val billDetail = item.billDetail
                AiBillChatItemVo(
                    core = item.core,
                    billId = billDetail?.core?.id,
                    time = billDetail?.core?.time,
                    cateIconRsd = AppServices.iconMappingSpi[billDetail?.category?.getAdapter?.iconName],
                    cateName = billDetail?.category?.getAdapter?.name,
                    note = billDetail?.core?.note,
                    amount = billDetail?.core?.amount?.toYuan(),
                )
            }
        }
        .sharedStateIn(
            scope = viewModelScope,
        )
}