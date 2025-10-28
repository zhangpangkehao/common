package com.xiaojinzi.tally.module.base.spi

import androidx.compose.runtime.Composable
import com.xiaojinzi.support.annotation.StateHotObservable
import com.xiaojinzi.support.ktx.SuspendAction0
import com.xiaojinzi.tally.lib.res.model.exception.NotLoggedInException
import com.xiaojinzi.tally.lib.res.model.exception.WxNotInstallException
import com.xiaojinzi.tally.lib.res.model.user.ThirdLoginBindPhoneException
import com.xiaojinzi.tally.lib.res.model.user.UserInfoDto
import com.xiaojinzi.tally.lib.res.model.user.UserVipResDto
import kotlinx.coroutines.flow.Flow

interface UserSpi {

    companion object {
        const val TAG = "UserSpi"
    }

    /**
     * 用户信息
     */
    @StateHotObservable
    val userTokenStateOb: Flow<String?>

    /**
     * 现取的 userToken
     */
    val userTokenNow: String?

    /**
     * 用户信息
     */
    @StateHotObservable
    val userInfoStateOb: Flow<UserInfoDto?>

    /**
     * 是否登录了
     */
    @StateHotObservable
    val isLoginStateOb: Flow<Boolean>

    /**
     * 最近的一次用户信息中的 userId
     * 这个得用在登录已经过期, 但是还可以继续记账的场景
     * 比如没登录, 这里就是 null
     * 如果登录了, 这里就是对应的 userId 值
     * 如果登录过期了, 这里还是上一次登录过的 userId
     * 如果新的登录来了, 这里就是新的 userId
     */
    @StateHotObservable
    val latestUserIdStateOb: Flow<String?>

    /**
     * 用户的 Vip 信息
     */
    @StateHotObservable
    val vipInfoStateOb: Flow<UserVipResDto?>

    /**
     * 是否是会员
     */
    @StateHotObservable
    val isVipStateOb: Flow<Boolean>

    /**
     * 我的视图
     */
    @Composable
    fun MyViewShared()

    /**
     * 用户信息, 没登录会抛异常
     */
    @Throws(NotLoggedInException::class)
    suspend fun requiredUserInfo(): UserInfoDto

    /**
     * 最后一次登录的用户 Id
     */
    suspend fun requiredLastUserId(): String

    /**
     * 验证码登录
     */
    suspend fun loginByCheckCode(
        phoneNumber: String,
        checkCode: String,
    )

    /**
     * 绑定微信登录
     */
    suspend fun loginByBindWx(
        authId: String,
        phoneNumber: String,
        checkCode: String,
    )

    /**
     * 微信登录
     */
    @Throws(
        WxNotInstallException::class,
        ThirdLoginBindPhoneException::class,
    )
    suspend fun loginByWx()

    /**
     * 清除用户信息
     */
    suspend fun clearUserInfo()

    /**
     * 退出登录
     * 0. 等待同步完成
     * 1. 退出登录
     * 2. 清空所有表
     * 3. 打开登录界面
     */
    suspend fun logoutForBusinessLogic()

    /**
     * 更新 Token 信息的动作
     */
    fun updateTokenInfoAction(): SuspendAction0

    /**
     * 更新会员信息的动作
     */
    fun updateVipInfoAction(): SuspendAction0

}