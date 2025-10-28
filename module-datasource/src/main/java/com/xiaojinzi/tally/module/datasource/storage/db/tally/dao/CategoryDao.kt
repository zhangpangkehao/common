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
    tableName = CategoryDao.TableName,
    indices = [
        Index(
            value = ["id"],
            unique = true,
        ),
    ]
)
data class CategoryDo(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    // 用户 Id
    @ColumnInfo(name = "userId") val userId: String,
    // 账本 Id
    @ColumnInfo(name = "bookId") val bookId: String,
    // 父节点
    @ColumnInfo(name = "parentId") val parentId: String? = null,
    // 类型, "spending" or "income"
    @ColumnInfo(name = "type") val type: String?,
    // 名称
    @ColumnInfo(name = "name") val name: String?,
    // 图标名称
    @ColumnInfo(name = "iconName") val iconName: String?,
    // 排序字段
    @ColumnInfo(name = "sort") val sort: Long,
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
 * 业务方自己过滤.
 * 但是都需要根据 userId 来查. 每一条数据都是和 userId 相关的
 */
@Dao
interface CategoryDao {

    companion object {
        const val TableName = "category"
        const val SelectAll = "SELECT * FROM $TableName"
        const val SelectById = "SELECT * FROM $TableName where uid=:uid"
    }

    /**
     * 插入数据
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(target: CategoryDo)

    /**
     * 插入数据
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(targetList: List<CategoryDo>)

    /**
     * 更新数据
     */
    @Update
    suspend fun update(target: CategoryDo)

    /**
     * 更新数据
     */
    @Update
    suspend fun update(targetList: List<CategoryDo>)

    /**
     * 删除所有有关某个 BookId 的数据
     */
    @Transaction
    @Query("DELETE FROM $TableName WHERE bookId = :bookId")
    suspend fun deleteAllByBookId(bookId: String)

    /**
     * 根据 id 获取数据
     */
    @Query(value = "select * from $TableName where id=:id")
    suspend fun getById(
        id: String,
    ): CategoryDo?

    /**
     * 根据 id 获取数据
     */
    @Query(value = "select * from $TableName where id=:id and bookId=:bookId")
    suspend fun getByIdAndBookId(
        id: String,
        bookId: String,
    ): CategoryDo?

    /**
     * 根据 bookId 获取数据
     */
    @Query(value = "select * from $TableName where bookId=:bookId")
    suspend fun getByBookId(
        bookId: String,
    ): List<CategoryDo>

    /**
     * 根据 parentId 获取数据
     */
    @Query(value = "select * from $TableName where parentId=:parentId")
    suspend fun getByParentId(
        parentId: String,
    ): List<CategoryDo>

    @Query(value = "select * from $TableName where bookId = :bookId and isSync = 0 limit 0, :pageSize")
    suspend fun getUnSyncList(
        bookId: String,
        pageSize: Int,
    ): List<CategoryDo>

    @Query(value = "select * from $TableName where name LIKE '%' ||:key||'%'")
    suspend fun search(
        key: String,
    ): List<CategoryDo>

    /**
     * 根据 timeModify 倒序排列, 获取最新一条数据
     */
    @Query(value = "select * from $TableName where bookId = :bookId order by timeModify desc limit 0, 1")
    suspend fun getLatestOneCategoryOrderByTimeModify(
        bookId: String,
    ): CategoryDo?

    /**
     * 所有数据
     */
    @Query(value = "SELECT COUNT(*) FROM $TableName where userId =:userId")
    fun getSize(
        userId: String,
    ): Long

    /**
     * 订阅
     */
    @Query(value = "SELECT * FROM $TableName where bookId in (:bookIdList)")
    fun subscribeCategory(
        bookIdList: List<String>,
    ): Flow<List<CategoryDo>>

    /**
     * 订阅没同步的数量
     * 包括已经删除的
     */
    @Query("select COUNT(b.id) from $TableName b where b.isSync = 0")
    fun subscribeUnSyncCount(): Flow<Long>

}
