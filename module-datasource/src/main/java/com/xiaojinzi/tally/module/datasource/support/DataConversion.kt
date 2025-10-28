package com.xiaojinzi.tally.module.datasource.support

import com.xiaojinzi.tally.lib.res.model.tally.BillChatDto
import com.xiaojinzi.tally.lib.res.model.tally.BillChatInsertDto
import com.xiaojinzi.tally.lib.res.model.tally.MoneyFen
import com.xiaojinzi.tally.lib.res.model.tally.TallyAccountDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyAccountInsertDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillDetailDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillImageDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillImageInsertDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillInsertDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillLabelDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillLabelInsertDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBookDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBookInsertDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyCategoryDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyCategoryInsertDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyLabelDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyLabelInsertDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyRemoteBookRes
import com.xiaojinzi.tally.lib.res.model.tally.TallyRemoteCategoryRes
import com.xiaojinzi.tally.lib.res.model.user.UserInfoCacheDto
import com.xiaojinzi.tally.module.base.spi.TallyDataSourceSpi
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.datasource.storage.db.tally.TallyDb
import com.xiaojinzi.tally.module.datasource.storage.db.tally.dao.AccountDo
import com.xiaojinzi.tally.module.datasource.storage.db.tally.dao.BillChatDo
import com.xiaojinzi.tally.module.datasource.storage.db.tally.dao.BillDetailDo
import com.xiaojinzi.tally.module.datasource.storage.db.tally.dao.BillDo
import com.xiaojinzi.tally.module.datasource.storage.db.tally.dao.BillImageDo
import com.xiaojinzi.tally.module.datasource.storage.db.tally.dao.BillLabelDo
import com.xiaojinzi.tally.module.datasource.storage.db.tally.dao.BookDo
import com.xiaojinzi.tally.module.datasource.storage.db.tally.dao.CategoryDo
import com.xiaojinzi.tally.module.datasource.storage.db.tally.dao.LabelDo
import com.xiaojinzi.tally.module.datasource.storage.db.tally.dao.UserInfoCacheDo

val tallyDataSourceSpi by lazy {
    AppServices.tallyDataSourceSpi
}

// ===========================  UserInfoCache  =========================== start

fun UserInfoCacheDo.toDto() = UserInfoCacheDto(
    id = this.id,
    name = this.name,
    timeExpire = this.timeExpire,
)

fun UserInfoCacheDto.toDo() = UserInfoCacheDo(
    id = this.id,
    name = this.name,
    timeExpire = this.timeExpire,
)

// ===========================  UserInfoCache  =========================== end

// ===========================  Book  =========================== start

fun TallyRemoteBookRes.toInsertDto(): TallyBookInsertDto = TallyBookInsertDto(
    id = this.id,
    userId = this.userId,
    isSystem = this.isSystem,
    type = this.type,
    name = this.name,
    iconName = this.iconName,
    timeCreate = this.timeCreate,
    timeModify = this.timeModify,
)

fun TallyBookInsertDto.toDo() = BookDo(
    id = this.id ?: TallyDb.generateUniqueStr(),
    userId = this.userId,
    isSystem = this.isSystem,
    type = this.type,
    name = this.name,
    iconName = this.iconName,
    timeCreate = this.timeCreate,
    timeModify = this.timeModify,
)

fun BookDo.toDto() = TallyBookDto(
    id = this.id,
    userId = this.userId,
    isSystem = this.isSystem,
    type = this.type,
    name = this.name,
    iconName = this.iconName,
    timeCreate = this.timeCreate,
    timeModify = this.timeModify,
)

// ===========================  Book  =========================== end

// ===========================  Category  =========================== start

fun TallyCategoryInsertDto.toDo() = CategoryDo(
    id = this.id ?: TallyDb.generateUniqueStr(),
    userId = this.userId,
    bookId = this.bookId,
    name = this.name,
    type = this.type,
    parentId = this.parentId,
    iconName = this.iconName,
    sort = this.sort,
    timeCreate = this.timeCreate,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = this.isSync,
)

fun TallyCategoryDto.toDo(): CategoryDo = CategoryDo(
    id = this.id,
    userId = this.userId,
    bookId = this.bookId,
    name = this.name,
    type = this.type.dbStr,
    parentId = this.parentId,
    iconName = this.iconName,
    sort = this.sort,
    timeCreate = this.timeCreate,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = this.isSync,
)

fun CategoryDo.toDto(): TallyCategoryDto = TallyCategoryDto(
    id = this.id,
    userId = this.userId,
    bookId = this.bookId,
    name = this.name,
    type = TallyCategoryDto.Companion.TallyCategoryType.fromDbStr(
        dbStr = this.type
    ),
    parentId = this.parentId,
    iconName = this.iconName,
    sort = this.sort,
    timeCreate = this.timeCreate,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = this.isSync,
)

// ===========================  Category  =========================== end

// ===========================  Account  =========================== start

fun TallyAccountInsertDto.toDo() = AccountDo(
    id = this.id ?: TallyDb.generateUniqueStr(),
    userId = this.userId,
    bookId = this.bookId,
    iconName = this.iconName,
    name = this.name,
    balanceInit = this.balanceInit.value,
    isExcluded = this.isExcluded,
    isDefault = this.isDefault,
    timeCreate = this.timeCreate,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = this.isSync,
)

suspend fun AccountDo.toDto(): TallyAccountDto = TallyAccountDto(
    id = this.id,
    userId = this.userId,
    bookId = this.bookId,
    iconName = this.iconName,
    name = this.name,
    balanceInit = MoneyFen(value = this.balanceInit),
    balanceCurrent = MoneyFen(
        value = this.balanceInit + (tallyDataSourceSpi.getBillAmountByCondition(
            queryCondition = TallyDataSourceSpi.Companion.BillQueryConditionDto(
                accountIdList = listOf(
                    element = this.id,
                ),
            )
        ).value) - (tallyDataSourceSpi.getBillAmountByCondition(
            queryCondition = TallyDataSourceSpi.Companion.BillQueryConditionDto(
                transferTargetAccountIdList = listOf(element = this.id),
            )
        ).value),
    ),
    isExcluded = this.isExcluded,
    isDefault = this.isDefault,
    timeCreate = this.timeCreate,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = this.isSync,
)

fun TallyAccountDto.toDo(): AccountDo = AccountDo(
    id = this.id,
    userId = this.userId,
    bookId = this.bookId,
    iconName = this.iconName,
    name = this.name,
    balanceInit = this.balanceInit.value,
    isExcluded = this.isExcluded,
    isDefault = this.isDefault,
    timeCreate = this.timeCreate,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = this.isSync,
)

// ===========================  Account  =========================== end

// ===========================  Bill  =========================== start

fun TallyBillInsertDto.toDo(): BillDo = BillDo(
    id = this.id ?: TallyDb.generateUniqueStr(),
    userId = this.userId,
    type = this.type,
    time = this.time,
    bookId = this.bookId,
    categoryId = this.categoryId,
    accountId = this.accountId,
    transferTargetAccountId = this.transferTargetAccountId,
    originBillId = this.originBillId,
    amount = this.amount.value,
    note = this.note,
    isNotCalculate = this.isNotCalculate,
    timeCreate = this.timeCreate,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = this.isSync,
)

fun BillDo.toDto(): TallyBillDto = TallyBillDto(
    id = this.id,
    userId = this.userId,
    type = TallyBillDto.Type.from(
        value = this.type,
    ),
    time = this.time,
    bookId = this.bookId,
    categoryId = this.categoryId,
    accountId = this.accountId,
    transferTargetAccountId = this.transferTargetAccountId,
    originBillId = this.originBillId,
    amount = MoneyFen(value = this.amount),
    note = this.note,
    isNotCalculate = this.isNotCalculate,
    timeCreate = this.timeCreate,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = this.isSync,
)

fun TallyBillDto.toDo(): BillDo = BillDo(
    id = this.id,
    userId = this.userId,
    type = this.type.value,
    time = this.time,
    bookId = this.bookId,
    categoryId = this.categoryId,
    accountId = this.accountId,
    transferTargetAccountId = this.transferTargetAccountId,
    originBillId = this.originBillId,
    amount = this.amount.value,
    note = this.note,
    isNotCalculate = this.isNotCalculate,
    timeCreate = this.timeCreate,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = this.isSync,
)

suspend fun BillDetailDo.toDto(): TallyBillDetailDto =
    TallyBillDetailDto(
        core = this.bill.toDto(),
        user = this.user?.toDto(),
        book = this.book?.toDto(),
        category = this.category?.toDto(),
        account = this.account?.toDto(),
        labelList = AppServices.tallyDataSourceSpi.getLabelListByBillId(
            bookId = this.bill.bookId,
            billId = this.bill.id,
        ),
        transferTargetAccount = this.transferTargetAccount?.toDto(),
    )

// ===========================  Bill  =========================== end

// ===========================  BillLabel  =========================== start

fun BillLabelDo.toDto() = TallyBillLabelDto(
    id = this.id,
    userId = this.userId,
    bookId = this.bookId,
    billId = this.billId,
    labelId = this.labelId,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = this.isSync,
)

fun TallyBillLabelInsertDto.toDo() = BillLabelDo(
    id = this.id ?: TallyDb.generateUniqueStr(),
    userId = this.userId,
    bookId = this.bookId,
    billId = this.billId,
    labelId = this.labelId,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = this.isSync,
)

// ===========================  BillLabel  =========================== end

// ===========================  BillImage  =========================== start

fun BillImageDo.toDto() = TallyBillImageDto(
    id = this.id,
    userId = this.userId,
    bookId = this.bookId,
    billId = this.billId,
    url = this.url,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = this.isSync,
)

fun TallyBillImageInsertDto.toDo() = BillImageDo(
    id = this.id ?: TallyDb.generateUniqueStr(),
    userId = this.userId,
    bookId = this.bookId,
    billId = this.billId,
    url = this.url,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = this.isSync,
)

// ===========================  BillImage  =========================== end

// ===========================  Label  =========================== start

fun TallyLabelInsertDto.toDo() = LabelDo(
    id = this.id ?: TallyDb.generateUniqueStr(),
    userId = this.userId,
    bookId = this.bookId,
    name = this.name,
    timeCreate = this.timeCreate,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = this.isSync,
)

fun LabelDo.toDto() = TallyLabelDto(
    id = this.id,
    userId = this.userId,
    bookId = this.bookId,
    name = this.name,
    timeCreate = this.timeCreate,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = this.isSync,
)

fun TallyLabelDto.toDo() = LabelDo(
    id = this.id,
    userId = this.userId,
    bookId = this.bookId,
    name = this.name,
    timeCreate = this.timeCreate,
    timeModify = this.timeModify,
    isDeleted = this.isDeleted,
    isSync = this.isSync,
)

// ===========================  Label  =========================== end

// ===========================  BillChat  =========================== start

fun BillChatDto.toDo() = BillChatDo(
    id = this.id,
    state = this.state,
    content = this.content,
    bookId = this.bookId,
    billId = this.billId,
    timeCreated = timeCreated,
)
fun BillChatInsertDto.toDo() = BillChatDo(
    state = this.state,
    content = this.content,
    bookId = this.bookId,
    billId = this.billId,
    timeCreated = System.currentTimeMillis(),
)

fun BillChatDo.toDto() = BillChatDto(
    id = this.id!!,
    state = this.state,
    content = this.content,
    bookId = this.bookId,
    billId = this.billId,
    timeCreated = this.timeCreated,
)

// ===========================  BillChat  =========================== end

