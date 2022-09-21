package com.example.im.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import com.example.im.app.IMApplication
import org.jetbrains.anko.db.*

class DataBaseHelper(ctx: Context = IMApplication.instance)
    : ManagedSQLiteOpenHelper(ctx, NAME, null, VERSION) {
    companion object{
        val NAME = "im.db"
        val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            Friends.NAME,true,Friends.ID to INTEGER + PRIMARY_KEY
                    + AUTOINCREMENT,Friends.CONTACT to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Friends.NAME,true)
        onCreate(db)
    }
}