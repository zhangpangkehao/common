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
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Locale

@Keep
@Entity(
    tableName = BookDao.TableName,
    indices = [
        Index(
            value = ["id"],
            unique = true,
        ),
    ]
)
data class BookDo(
    // 全宇宙唯一的 string
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    // 所属用户
    @ColumnInfo(name = "userId") val userId: String,
    // 类型
    @ColumnInfo(name = "isSystem") val isSystem: Boolean,
    // 类型
    @ColumnInfo(name = "type") val type: String?,
    // 名称
    @ColumnInfo(name = "name") val name: String?,
    // 图标名
    @ColumnInfo(name = "iconName") val iconName: String?,
    // 创建的时间
    @ColumnInfo(name = "timeCreate") val timeCreate: Long,
    // 修改的时间
    @ColumnInfo(name = "timeModify") val timeModify: Long?,
    // 修改的时间的格式化, 就是为了便于开发测试, 实际上没啥用处
    @ColumnInfo(name = "timeModifyFormat") val timeModifyFormat: String? = timeModify?.let {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE).format(java.util.Date(it))
    },
)

/**
 * 所有查询不过滤被删除的记录
 * 业务方自己过滤
 */
@Dao
interface BookDao {

    companion object {
        const val TableName = "book"
    }

    /**
     * 插入数据
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(target: BookDo)

    /**
     * 插入数据
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(targetList: List<BookDo>)

    /**
     * 删除数据
     */
    @Transaction
    @Query("DELETE FROM $TableName where id=:id")
    suspend fun deleteById(id: String)

    /**
     * 插入数据
     */
    @Query("SELECT * FROM $TableName where id=:id")
    suspend fun getById(id: String): BookDo?

    @Query("SELECT COUNT(*) FROM $TableName")
    suspend fun getCount(): Long

    /**
     * 所有的账本
     */
    @Query("SELECT * FROM $TableName")
    fun getAll(): List<BookDo>

    /**
     * 所有的账本
     */
    @Query("SELECT * FROM $TableName")
    fun all(): Flow<List<BookDo>>

}
