package com.xiaojinzi.tally.lib.res.model.tally

import androidx.annotation.Keep
import com.xiaojinzi.support.annotation.ModelForNetwork

@Keep
@ModelForNetwork
data class TallyInitSyncRes(
    val bookList: List<TallyRemoteBookRes>,
    val categoryList: List<TallyRemoteCategoryRes>
) {

    companion object {
        fun createForOpenSource(): TallyInitSyncRes {
            val currentTime = System.currentTimeMillis()
            return TallyInitSyncRes(
                bookList = listOf(
                    TallyRemoteBookRes(
                        id = "1",
                        userId = "",
                        isSystem = true,
                        type = TallyBookDto.TYPE_NORMAL,
                        name = "默认账本",
                        iconName = "icon_name",
                        timeCreate = currentTime,
                        timeModify = currentTime
                    )
                ),
                categoryList = TallyRemoteCategoryRes.createForOpenSource(),
            )
        }
    }

}
