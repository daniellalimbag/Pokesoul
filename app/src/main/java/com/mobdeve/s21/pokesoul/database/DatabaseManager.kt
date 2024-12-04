package com.mobdeve.s21.pokesoul.database

import android.content.ContentValues
import android.content.Context
import com.mobdeve.s21.pokesoul.model.User

class DatabaseManager(context: Context) {
    private val dbHelper = MyDatabaseHelper(context)

    //MANAGING USER TABLE
    fun insertUser(user: User):Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.USERNAME, user.username)
            put(MyDatabaseHelper.IMAGE, user.image)
        }
        val result = db.insert(MyDatabaseHelper.USERS_TABLE, null, values)
        db.close()
        return result
    }
    fun deleteUser(username: String):Int {
        val db = dbHelper.writableDatabase
        val result = db.delete(
            MyDatabaseHelper.USERS_TABLE,
            "${MyDatabaseHelper.USERNAME} = ?",
            arrayOf(username)
        )
        db.close()
        return result
    }

}