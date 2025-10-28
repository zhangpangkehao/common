package com.xiaojinzi.tally.module.datasource.storage.db.tally.dao

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.DatabaseView
import androidx.room.Entity
import androidx.room.Index
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.xiaojinzi.tally.module.datasource.storage.db.tally.TallyDb
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Locale

@Keep
@Entity(
    tableName = BillLabelDao.TableName,
    indices = [
        Index(
            value = ["id"],
            unique = true,
        ),
        Index(
            name = "billId_labelId",
            value = ["billId", "labelId"],
            unique = true,
        ),
    ]
)
data class BillLabelDo(
    // 全宇宙唯一的 string
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    //   用户Id
    @ColumnInfo(name = "userId") val userId: String,
    //  所属的账本 Id
    @ColumnInfo(name = "bookId") val bookId: String,
    // 账单 Id
    @ColumnInfo(name = "billId") val billId: String,
    // 标签 ID
    @ColumnInfo(name = "labelId") val labelId: String,
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
) : SyncTableInter

/**
 * 所有查询不过滤被删除的记录
 * 业务方自己过滤
 * 但是都需要根据 userId 来查. 每一条数据都是和 userId 和 labelId 相关的
 */
@Dao
interface BillLabelDao {

    companion object {
        const val TableName = "bill_label"
        const val SelectAll = "SELECT * FROM $TableName"
    }

    /**
     * 插入数据
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(target: BillLabelDo)

    /**
     * 插入数据
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(targetList: List<BillLabelDo>)

    /**
     * 更新数据
     */
    @Update
    suspend fun update(target: BillLabelDo)

    /**
     * 更新数据
     */
    @Update
    suspend fun update(targetList: List<BillLabelDo>)

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
    suspend fun getLatestOneBillLabelOrderByTimeModify(
        bookId: String,
    ): BillLabelDo?

    /**
     * 根据 labelId 获取数据
     */
    @Query(value = "select * from $TableName where bookId = :bookId and labelId = :labelId")
    suspend fun getListByLabelId(
        bookId: String,
        labelId: String,
    ): List<BillLabelDo>

    /**
     * 根据 labelIdList 获取数据
     */
    @Query(value = "select * from $TableName where labelId in (:labelIdList)")
    suspend fun getBillIdListByLabelIdList(
        labelIdList: List<String>,
    ): List<BillLabelDo>

    /**
     * 根据 billId 获取数据
     */
    @Query(value = "select * from $TableName where bookId = :bookId and billId = :billId")
    suspend fun getListByBillId(
        bookId: String,
        billId: String,
    ): List<BillLabelDo>

    @Query(value = "select * from $TableName where bookId = :bookId and isSync = 0 limit 0, :pageSize")
    suspend fun getUnSyncList(
        bookId: String, pageSize: Int,
    ): List<BillLabelDo>

    @Query(value = "select * from $TableName where bookId = :bookId order by timeModify desc limit 0, 1")
    suspend fun getLatestOneOrderByTimeModify(
        bookId: String,
    ): BillLabelDo?

    /**
     * 所有数据
     */
    @Query(value = "SELECT * FROM $TableName where bookId = :bookId")
    fun subscribeAllBillLabel(
        bookId: String,
    ): Flow<List<BillLabelDo>>

    /**
     * 订阅没同步的数量
     * 包括已经删除的
     */
    @Query("select COUNT(t.id) from ${AccountDao.TableName} t where t.isSync = 0")
    fun subscribeUnSyncCount(): Flow<Long>

}
