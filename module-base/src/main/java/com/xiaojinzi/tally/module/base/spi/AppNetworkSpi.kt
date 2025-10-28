package com.xiaojinzi.tally.module.base.spi

import com.xiaojinzi.support.annotation.NeedToOptimize
import com.xiaojinzi.tally.lib.res.model.ai.AiBillAnalyzeResDto
import com.xiaojinzi.tally.lib.res.model.app_update.AppUpdateResDto
import com.xiaojinzi.tally.lib.res.model.support.AliSTSTokenResDto
import com.xiaojinzi.tally.lib.res.model.tally.BillCycleResDto
import com.xiaojinzi.tally.lib.res.model.tally.BookInviteShareResDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBookDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBookMemberResDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBookNecessaryInfoResDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyInitSyncRes
import com.xiaojinzi.tally.lib.res.model.tally.TallyRemoteAccountReq
import com.xiaojinzi.tally.lib.res.model.tally.TallyRemoteAccountRes
import com.xiaojinzi.tally.lib.res.model.tally.TallyRemoteBillImageReq
import com.xiaojinzi.tally.lib.res.model.tally.TallyRemoteBillImageRes
import com.xiaojinzi.tally.lib.res.model.tally.TallyRemoteBillLabelReq
import com.xiaojinzi.tally.lib.res.model.tally.TallyRemoteBillLabelRes
import com.xiaojinzi.tally.lib.res.model.tally.TallyRemoteBillReq
import com.xiaojinzi.tally.lib.res.model.tally.TallyRemoteBillRes
import com.xiaojinzi.tally.lib.res.model.tally.TallyRemoteBookTypeResDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyRemoteCategoryReq
import com.xiaojinzi.tally.lib.res.model.tally.TallyRemoteCategoryRes
import com.xiaojinzi.tally.lib.res.model.tally.TallyRemoteLabelReq
import com.xiaojinzi.tally.lib.res.model.tally.TallyRemoteLabelRes
import com.xiaojinzi.tally.lib.res.model.user.LoginResDto
import com.xiaojinzi.tally.lib.res.model.user.TokenResDto
import com.xiaojinzi.tally.lib.res.model.user.UserInfoDto
import com.xiaojinzi.tally.lib.res.model.user.UserVipResDto
import com.xiaojinzi.tally.lib.res.model.user.VipItemResDto
import com.xiaojinzi.tally.lib.res.model.user.WxLoginResDto

interface AppNetworkSpi {

    /**
     * 发送验证码
     */
    suspend fun sendCheckCode(
        usage: String,
        phoneNumber: String,
    )

    /**
     * 验证码登录
     */
    suspend fun loginByCheckCode(
        phoneNumber: String,
        checkCode: String,
    ): LoginResDto

    /**
     * 验证码登录
     */
    suspend fun loginByBindWx(
        authId: String,
        phoneNumber: String,
        checkCode: String,
    ): LoginResDto

    /**
     * 微信登录
     */
    suspend fun loginByWx(
        wxCode: String,
    ): WxLoginResDto

    /**
     * 退出登录
     */
    suspend fun logout()

    /**
     * 注销登录
     */
    suspend fun logOff()

    /**
     * 刷新 token
     */
    suspend fun refreshToken(): TokenResDto

    /**
     * 获取用户信息
     */
    suspend fun getUserInfo(userId: String): UserInfoDto

    /**
     * 批量获取用户信息
     */
    suspend fun getUserInfoList(
        userIdList: List<String>,
    ): List<UserInfoDto>

    /**
     * 初始化同步
     * 首次安装登录的时候会执行
     */
    @NeedToOptimize
    suspend fun initSync(): TallyInitSyncRes

    /**
     * 获取账本列表
     */
    suspend fun getBookList(): List<TallyBookDto>

    /**
     * 类别是不是为空的
     * @param userToken 在没有登录状态的时候, 自定义传入 token 来使用
     */
    suspend fun categoryIsEmpty(
        userToken: String? = null,
    ): Boolean

    /**
     * 获取账本类型的数据
     */
    suspend fun getBookTypeList(): List<TallyRemoteBookTypeResDto>

    /**
     * 或者账本成员列表
     */
    suspend fun getBookMemberList(
        bookId: String,
    ): List<TallyBookMemberResDto>

    /**
     * 退出账本, 非账本拥有者做的
     */
    suspend fun exitBook(
        bookId: String,
        targetUserId: String,
    )

    /**
     * 创建账本
     */
    suspend fun createBook(
        type: String,
        name: String,
    ): TallyBookNecessaryInfoResDto

    /**
     * 创建账本的分享信息
     */
    suspend fun createBookShareInfo(
        bookId: String,
    ): BookInviteShareResDto

    /**
     * 接受账本分享
     */
    suspend fun acceptBookShare(
        code: String,
        bookId: String,
    ): TallyBookNecessaryInfoResDto

    /**
     * 获取阿里的临时凭证
     */
    suspend fun getAliOssStsToken(): AliSTSTokenResDto

    /**
     * 获取 Vip 可重置的选项列表ø
     */
    suspend fun getVipItems(): List<VipItemResDto>

    /**
     * 创建支付宝 vip 订单
     */
    suspend fun createAlipayVipOrder(
        itemId: String,
    ): String

    /**
     * 获取会员信息
     */
    suspend fun getVipInfo(): UserVipResDto?

    /**
     * 获取新版本的信息
     */
    suspend fun getNewUpdate(): AppUpdateResDto?

    /**
     * 删除周期记账的任务
     */
    suspend fun deleteBillCycleById(
        id: Long,
    )

    /**
     * 获取周期记账的任务
     */
    suspend fun getBillCycleById(
        id: Long,
    ): BillCycleResDto

    /**
     * 获取周期记账的任务
     */
    suspend fun getBillCycleList(): List<BillCycleResDto>

    /**
     * 设置周期记账的状态
     */
    suspend fun setBillCycleState(
        id: Long,
        state: String,
    ): BillCycleResDto

    /**
     *  跑一次周期记账 o
     */
    suspend fun runBillCycleOnce(
        id: Long,
    ): BillCycleResDto

    /**
     * 创建周期记账
     */
    suspend fun createOrUpdateBillCycle(
        id: Long?,
        bookId: String,
        cycleType: String,
        loopCount: Int?,
        timeZone: Int,
        dayOfMonth: Int?,
        dayOfWeek: Int?,
        hour: Int,
        billType: String,
        categoryId: String?,
        accountId: String?,
        transferTargetAccountId: String?,
        amount: Long,
        note: String?,
    ): BillCycleResDto

    /**
     * 账单的 ai 分析
     */
    suspend fun aiBillAnalyze(
        spendingCategoryNameList: List<String>,
        incomeCategoryNameList: List<String>,
        content: String,
    ): AiBillAnalyzeResDto

    // =================================== 数据同步相关接口 ========================================

    suspend fun getNeedSyncAccountList(
        bookId: String,
        timeModify: Long?,
        pageSize: Int = 500,
    ): List<TallyRemoteAccountRes>

    suspend fun accountSync(
        timeModify: Long?,
        dataList: List<TallyRemoteAccountReq>,
    ): List<TallyRemoteAccountRes>

    suspend fun getNeedSyncBillImageList(
        bookId: String,
        timeModify: Long?,
        pageSize: Int = 500,
    ): List<TallyRemoteBillImageRes>

    suspend fun billImageSync(
        timeModify: Long?,
        dataList: List<TallyRemoteBillImageReq>,
    ): List<TallyRemoteBillImageRes>

    suspend fun getNeedSyncBillLabelList(
        bookId: String,
        timeModify: Long?,
        pageSize: Int = 500,
    ): List<TallyRemoteBillLabelRes>

    suspend fun billLabelSync(
        timeModify: Long?,
        dataList: List<TallyRemoteBillLabelReq>,
    ): List<TallyRemoteBillLabelRes>

    suspend fun getNeedSyncBillList(
        bookId: String,
        timeModify: Long?,
        pageSize: Int = 500,
    ): List<TallyRemoteBillRes>

    suspend fun billSync(
        timeModify: Long?,
        dataList: List<TallyRemoteBillReq>,
    ): List<TallyRemoteBillRes>

    suspend fun getNeedSyncCategoryList(
        bookId: String,
        timeModify: Long?,
        pageSize: Int = 500,
    ): List<TallyRemoteCategoryRes>

    suspend fun categorySync(
        timeModify: Long?,
        dataList: List<TallyRemoteCategoryReq>,
    ): List<TallyRemoteCategoryRes>

    suspend fun getNeedSyncLabelList(
        bookId: String,
        timeModify: Long?,
        pageSize: Int = 500,
    ): List<TallyRemoteLabelRes>

    suspend fun labelSync(
        timeModify: Long?,
        dataList: List<TallyRemoteLabelReq>,
    ): List<TallyRemoteLabelRes>

    // =================================== 数据同步相关接口 ========================================

}