package com.xiaojinzi.tally.module.core.module.book_member.view

import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.StateHotObservable
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.core.module.book_member.domain.BookMemberUseCase
import com.xiaojinzi.tally.module.core.module.book_member.domain.BookMemberUseCaseImpl
import kotlinx.coroutines.flow.combine

@ViewLayer
class BookMemberViewModel(
    private val useCase: BookMemberUseCase = BookMemberUseCaseImpl(),
) : BaseViewModel(),
    BookMemberUseCase by useCase {

    /**
     * 成员列表
     */
    @StateHotObservable
    val memberListStateObVo = combine(
        AppServices.userSpi.latestUserIdStateOb,
        useCase.memberListStateOb,
    ) { userId, memberList ->
        // 是否拥有者是我自己
        val isOwnerMe = memberList.any { it.userInfo.id == userId && it.isOwner }
        memberList.map { item ->
            BookMemberItemVo(
                isOwner = item.isOwner,
                userInfo = item.userInfo,
                canDelete = isOwnerMe && item.userInfo.id != userId,
            )
        }
    }

}