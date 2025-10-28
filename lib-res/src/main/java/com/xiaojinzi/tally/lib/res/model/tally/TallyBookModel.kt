package com.xiaojinzi.tally.lib.res.model.tally

import androidx.annotation.Keep
import com.xiaojinzi.support.annotation.ModelForNetwork
import com.xiaojinzi.tally.lib.res.model.user.UserInfoDto
import com.xiaojinzi.tally.lib.res.model.user.UserInfoRes
import com.xiaojinzi.tally.lib.res.model.user.toDto

@Keep
@ModelForNetwork
data class TallyRemoteBookTypeRes(
    val type: String,
    val name: String?,
    val iconName: String?,
    val desc: String?,
)

@Keep
data class TallyRemoteBookTypeResDto(
    val type: String,
    val name: String?,
    val iconName: String?,
    val desc: String?,
)

fun TallyRemoteBookTypeRes.toDto() = TallyRemoteBookTypeResDto(
    type = type,
    name = name,
    iconName = iconName,
    desc = desc,
)

@Keep
@ModelForNetwork
data class TallyRemoteBookRes(
    val id: String,
    val userId: String,
    val isSystem: Boolean,
    val type: String?,
    val name: String?,
    val iconName: String?,
    val timeCreate: Long,
    val timeModify: Long,
)

@Keep
@ModelForNetwork
data class TallyRemoteBookMemberRes(
    val isOwner: Boolean,
    val userInfo: UserInfoRes,
)

@Keep
@ModelForNetwork
data class TallyRemoteBookNecessaryInfoRes(
    val book: TallyRemoteBookRes,
    val categoryList: List<TallyRemoteCategoryRes>,
)

@Keep
@ModelForNetwork
data class TallyBookNecessaryInfoResDto(
    val book: TallyBookDto,
    val categoryList: List<TallyCategoryDto>,
)

@Keep
@ModelForNetwork
data class TallyBookMemberResDto(
    val isOwner: Boolean,
    val userInfo: UserInfoDto,
)

fun TallyRemoteBookMemberRes.toDto() = TallyBookMemberResDto(
    isOwner = this.isOwner,
    userInfo = this.userInfo.toDto(),
)

@Keep
data class TallyBookInsertDto(
    val id: String? = null,
    val userId: String,
    val isSystem: Boolean,
    val type: String?,
    val name: String?,
    val iconName: String?,
    val timeCreate: Long = System.currentTimeMillis(),
    val timeModify: Long? = null,
)

@Keep
data class TallyBookDto(
    val id: String,
    val userId: String,
    val isSystem: Boolean,
    val type: String?,
    val name: String?,
    val iconName: String?,
    val timeCreate: Long,
    val timeModify: Long? = null,
) {

    companion object {

        // 空账本
        const val TYPE_EMPTY = "empty"

        // 普通账本
        const val TYPE_NORMAL = "normal"

        // 旅游账本
        const val TYPE_TRAVEL = "travel"

        // 生意账本
        const val TYPE_BUSINESS = "business"

        // 宠物账本
        const val TYPE_PET = "pet"

        const val TEST_ID = "1"

        fun createForOpenSource(): TallyBookDto {
            val currentTime = System.currentTimeMillis()
            return TallyBookDto(
                id = TEST_ID,
                userId = UserInfoDto.TEST_ID,
                isSystem = true,
                type = TYPE_NORMAL,
                name = "默认账本",
                iconName = "book1",
                timeCreate = currentTime,
                timeModify = currentTime
            )
        }

    }

}

fun TallyBookDto.toInsertDto() = TallyBookInsertDto(
    id = id,
    userId = userId,
    isSystem = isSystem,
    type = type,
    name = name,
    iconName = iconName,
    timeCreate = timeCreate,
    timeModify = timeModify,
)

fun TallyRemoteBookRes.toDto() = TallyBookDto(
    id = id,
    userId = userId,
    isSystem = isSystem,
    type = type,
    name = name,
    iconName = iconName,
    timeCreate = timeCreate,
    timeModify = timeModify,
)

fun TallyRemoteBookNecessaryInfoRes.toDto() = TallyBookNecessaryInfoResDto(
    book = book.toDto(),
    categoryList = categoryList.map { it.toDto() },
)