package com.xiaojinzi.tally.module.datasource.storage.db.tally

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1to2 : Migration(
    startVersion = 1,
    endVersion = 2,
) {

    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(sql = "ALTER TABLE `account` ADD COLUMN `isDefault` INTEGER NOT NULL DEFAULT 0")
        db.execSQL("CREATE TABLE IF NOT EXISTS `bill_chat` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `state` TEXT NOT NULL, `content` TEXT, `bookId` TEXT, `billId` TEXT, `timeCreated` INTEGER NOT NULL)");
    }

}

class Migration2to3 : Migration(
    startVersion = 2,
    endVersion = 3,
) {

    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(sql = "ALTER TABLE `bill` ADD COLUMN `originBillId` TEXT")
    }

}