package com.xiaojinzi.tally.module.base.spi

interface AppWidgetSpi {

    /**
     * 启动 App 内小部件的更新服务
     * 如果小部件有被使用
     */
    suspend fun startWidgetServiceIfUsed()

    /**
     * 尝试更新一下小部件
     */
    fun tryUpdateWidget()

}