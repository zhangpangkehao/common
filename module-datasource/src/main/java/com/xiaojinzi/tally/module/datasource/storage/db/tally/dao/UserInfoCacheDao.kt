package com.xiaojinzi.tally.module.datasource.storage.db.tally.dao

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Index
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import com.xiaojinzi.tally.module.datasource.storage.db.tally.TallyDb
import kotlinx.coroutines.flow.Flow

@Keep
@Entity(
    tableName = UserInfoCacheDao.TableName,
    indices = [
        Index(
            value = ["id"],
            unique = true,
        ),
    ]
)
data class UserInfoCacheDo(
    // 全宇宙唯一的 string
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    // 名称
    @ColumnInfo(name = "name") val name: String?,
    // 过期时间
    @ColumnInfo(name = "timeExpire") val timeExpire: Long,
)

/**
 * 所有查询不过滤被删除的记录
 * 业务方自己过滤
 */
@Dao
interface UserInfoCacheDao {

    companion object {
        const val TableName = "user_info_cache"
    }

    /**
     * 插入数据
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(target: UserInfoCacheDo)

    /**
     * 插入数据
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(targetList: List<UserInfoCacheDo>)

    /**
     * 根据 id 获取
     */
    @Query("SELECT * FROM $TableName where id=:id")
    suspend fun getById(id: String): UserInfoCacheDo?

    /**
     * 根据 ids 获取
     */
    @Query("SELECT * FROM $TableName where id in (:ids)")
    suspend fun getByIds(ids: List<String>): List<UserInfoCacheDo>

    /**
     * 插入数据
     */
    @Query("SELECT * FROM $TableName")
    fun all(): Flow<List<UserInfoCacheDo>>

}
