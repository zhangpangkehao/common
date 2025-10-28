package com.xiaojinzi.tally.module.datasource.storage.db.tally.dao

interface SyncTableTimeCreateFieldInter {

    // 创建的时间
    val timeCreate: Long

}

interface SyncTableInter {

    // 修改的时间, 这个是查询和远程的 diff 数据的关键
    val timeModify: Long?

    // 是否被删除了
    val isDeleted: Boolean

    // 是否同步了
    val isSync: Boolean

}