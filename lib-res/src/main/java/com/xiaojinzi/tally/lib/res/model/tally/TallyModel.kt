package com.xiaojinzi.tally.lib.res.model.tally

import androidx.annotation.Keep
import kotlin.math.roundToLong

@Keep
enum class TallyTable {
    Book, Category, Account, Label, Bill, BillImage, BillLabel, BillChat,
}

@JvmInline
value class MoneyYuan(val value: Float = 0f) {

    fun toFen(): MoneyFen = MoneyFen(
        value = (this.value * 100).roundToLong()
    )

    operator fun plus(target: MoneyYuan): MoneyYuan = MoneyYuan(
        value = this.value + target.value
    )

    operator fun minus(target: MoneyYuan): MoneyYuan = MoneyYuan(
        value = this.value - target.value
    )

    fun transform(action: (Float) -> Float): MoneyYuan = MoneyYuan(value = action(this.value))

}

@JvmInline
value class MoneyFen(val value: Long = 0L) {

    fun toYuan(): MoneyYuan = MoneyYuan(
        value = this.value / 100f,
    )

    operator fun plus(target: MoneyFen): MoneyFen = MoneyFen(
        value = this.value + target.value
    )

    operator fun minus(target: MoneyFen): MoneyFen = MoneyFen(
        value = this.value - target.value
    )

    operator fun times(target: MoneyFen): MoneyFen = MoneyFen(
        value = this.value * target.value
    )

    fun transform(action: (Long) -> Long): MoneyFen = MoneyFen(value = action(this.value))

}

enum class SyncTable(val dbName: String) {
    Account(
        dbName = "Account",
    ),
    Category(
        dbName = "Category",
    ),
    Label(
        dbName = "Label",
    ),
    BillLabel(
        dbName = "BillLabel",
    ),
    BillImage(
        dbName = "BillImage",
    ),
    Bill(
        dbName = "Bill",
    ),
    ;

    companion object {
        fun from(dbName: String): SyncTable {
            return entries.first { it.dbName == dbName }
        }
    }
}