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
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Locale

@Keep
@Entity(
    tableName = AccountDao.TableName,
    indices = [
        Index(
            value = ["id"],
            unique = true,
        ),
    ]
)
data class AccountDo(
    // 主键 ID
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "userId") val userId: String,
    // 所属的账本 ID
    @ColumnInfo(name = "bookId") val bookId: String,
    // Icon 名称, 通过这个名称找到对应的图标资源 Id
    @ColumnInfo(name = "iconName") val iconName: String?,
    // 名称
    @ColumnInfo(name = "name") val name: String?,
    // 初始化余额, 单位是分
    @ColumnInfo(name = "balanceInit") val balanceInit: Long,
    // 是否被排除, 不计入资产
    @ColumnInfo(name = "isExcluded") val isExcluded: Boolean,
    // 是否是默认的, 这个可能不是唯一的,
    @ColumnInfo(name = "isDefault") val isDefault: Boolean,
    // 创建的时间
    @ColumnInfo(name = "timeCreate") override val timeCreate: Long,
    // 修改的时间
    @ColumnInfo(name = "timeModify") override val timeModify: Long?,
    // 修改的时间的格式化, 就是为了便于开发测试, 实际上没啥用处
    @ColumnInfo(name = "timeModifyFormat") val timeModifyFormat: String? = timeModify?.let {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE).format(java.util.Date(it))
    },
    // 是否被删除了
    @ColumnInfo(name = "isDeleted") override val isDeleted: Boolean,
    // 是否同步了
    @ColumnInfo(name = "isSync") override val isSync: Boolean,
) : SyncTableInter, SyncTableTimeCreateFieldInter

/**
 * 所有查询不过滤被删除的记录
 * 业务方自己过滤
 * 但是都需要根据 userId 来查. 每一条数据都是和 userId 相关的
 */
@Dao
interface AccountDao {

    companion object {
        const val TableName = "account"
        const val SelectAll = "SELECT * FROM $TableName"
    }

    /**
     * 插入数据
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(target: AccountDo)

    /**
     * 插入数据
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(targetList: List<AccountDo>)

    /**
     * 更新数据
     */
    @Update
    suspend fun update(target: AccountDo)

    /**
     * 删除所有有关某个 BookId 的数据
     */
    @Transaction
    @Query("DELETE FROM $TableName WHERE bookId = :bookId")
    suspend fun deleteAllByBookId(bookId: String)

    /**
     * 根据 timeModify 倒序排列, 获取最新一条数据
     */
    @Query(value = "select * from $TableName where bookId = :bookId order by timeModify desc limit 0, 1")
    suspend fun getLatestOneAccountOrderByTimeModify(
        bookId: String,
    ): AccountDo?

    /**
     * 根据 Id 获取数据
     */
    @Query(value = "select * from $TableName where id=:id")
    suspend fun getAccountById(
        id: String,
    ): AccountDo?

    /**
     * 根据 Id 获取数据
     */
    @Query(value = "select * from $TableName where id=:id and bookId = :bookId")
    suspend fun getAccountByIdAndBookId(
        id: String,
        bookId: String,
    ): AccountDo?

    /**
     * 根据 Id 获取数据
     */
    @Transaction
    @Query(value = "update $TableName set isDefault = 0 where bookId = :bookId")
    suspend fun updateAllToNotDefaultByBookId(
        bookId: String,
    )

    /**
     * 根据 Id 获取数据
     */
    @Query(value = "select * from $TableName where bookId = :bookId")
    suspend fun getAll(
        bookId: String,
    ): List<AccountDo>

    @Query(value = "select * from $TableName where bookId = :bookId and isSync = 0 limit 0, :pageSize")
    suspend fun getUnSyncList(
        bookId: String, pageSize: Int,
    ): List<AccountDo>

    @Query(value = "select * from $TableName where name LIKE '%' ||:key||'%'")
    suspend fun search(
        key: String,
    ): List<AccountDo>

    /**
     * 所有数据
     */
    @Query(value = "SELECT * FROM $TableName where bookId = :bookId")
    fun subscribeAllAccount(
        bookId: String,
    ): Flow<List<AccountDo>>

    /**
     * 订阅没同步的数量
     * 包括已经删除的
     */
    @Query("select COUNT(t.id) from $TableName t where t.isSync = 0")
    fun subscribeUnSyncCount(): Flow<Long>

}
