package com.xiaojinzi.tally.module.core.module.book_invite.view

import com.xiaojinzi.tally.module.core.module.book_invite.domain.BookInviteUseCase
import com.xiaojinzi.tally.module.core.module.book_invite.domain.BookInviteUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class BookInviteViewModel(
    private val useCase: BookInviteUseCase = BookInviteUseCaseImpl(),
): BaseViewModel(),
    BookInviteUseCase by useCase{
}