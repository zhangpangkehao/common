package com.xiaojinzi.tally.lib.res.model.user

import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import com.xiaojinzi.support.annotation.ModelForNetwork

@Keep
data class VipRightItem(
    @DrawableRes
    val iconRsd: Int,
    val title: String,
    val desc: String,
    val isComingSoon: Boolean = false,
)

val ALL_VIP_RIGHT_LIST = listOf(
    VipRightItem(
        iconRsd = com.xiaojinzi.tally.lib.res.R.drawable. res_robot1,
        title = "AI 记账",
        desc = "记账更智能",
    ),
    VipRightItem(
        iconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_theme1,
        title = "漂亮主题",
        desc = "主题随心换",
    ),
    VipRightItem(
        iconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_backup1,
        title = "数据同步",
        desc = "自动同步更省心",
    ),
    VipRightItem(
        iconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_device1,
        title = "多设备记账",
        desc = "多设备同步记账",
    ),
    VipRightItem(
        iconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_book1,
        title = "多账本",
        desc = "记账更清晰",
    ),
    VipRightItem(
        iconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_book1,
        title = "共享账本",
        desc = "共同记账更实用",
    ),
    VipRightItem(
        iconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_account1,
        title = "多账户",
        desc = "资产更明了",
    ),
    VipRightItem(
        iconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_calendar1,
        title = "日历",
        desc = "数据更直观",
    ),
    VipRightItem(
        iconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_search1,
        title = "高级搜索",
        desc = "搜索更方便",
    ),
    VipRightItem(
        iconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_clock1,
        title = "周期记账",
        desc = "分期不再难",
    ),
    VipRightItem(
        iconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_image1,
        title = "图片附件",
        desc = "记账更方便",
    ),
    VipRightItem(
        iconRsd = com.xiaojinzi.tally.lib.res.R.drawable.res_image1,
        title = "账单相册",
        desc = "回忆更美好",
    ),
)

@Keep
@ModelForNetwork
data class VipItemRes(
    val id: String,
    val title: String,
    val originalPrice: Float,
    val price: Float,
    val sort: Int,
)

@Keep
@ModelForNetwork
data class UserVipRes(
    var expiredTime: Long,
)

@Keep
@ModelForNetwork
data class AlipayOrderPayRes(
    val orderStr: String,
)

@Keep
data class VipItemResDto(
    val id: String,
    val title: String,
    val originalPrice: Float,
    val price: Float,
    val isSamePrice: Boolean = originalPrice == price,
    val sort: Int,
)

@Keep
data class UserVipResDto(
    var expiredTime: Long,
) {

    companion object {

        fun createForOpensource(): UserVipResDto {
            return UserVipResDto(
                expiredTime = System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365,
            )
        }

    }

}

fun VipItemRes.toDto() = VipItemResDto(
    id = id,
    title = title,
    originalPrice = originalPrice,
    price = price,
    sort = sort,
)

fun UserVipRes.toDto() = UserVipResDto(
    expiredTime = this.expiredTime,
)