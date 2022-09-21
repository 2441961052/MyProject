package com.example.im.data.db

import com.example.im.extentions.toChangeArray
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class IMDatabase {
    companion object{
        val dataBaseHelper = DataBaseHelper()
        val instance = IMDatabase()
    }
    fun saveUser(friend: Friend){
        dataBaseHelper.use {
            insert(Friends.NAME,*friend.map.toChangeArray())
        }
    }
    fun getAllUser():List<Friend> = dataBaseHelper.use {
            select(Friends.NAME).parseList(object : MapRowParser<Friend> {
                override fun parseRow(columns: Map<String, Any?>): Friend {
                    return columns.toMutableMap()?.let(::Friend)
                }

            })
    }
    fun deleteAlluser(){
        dataBaseHelper.use {
            delete(Friends.NAME,null,null)
        }
    }
}