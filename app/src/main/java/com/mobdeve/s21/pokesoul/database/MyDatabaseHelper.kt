package com.mobdeve.s21.pokesoul.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.media.Image

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, pokesoulDB, null, DB_ver) {
    companion object {
        private const val pokesoulDB = "my_database.db"
        private const val DB_ver = 1

        //Table: User
        const val USERS_TABLE = "users"
        const val USER_ID = "user_id"
        const val USERNAME = "username"
        const val IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        // Create the users table
        val createUsersQuery = """
            CREATE TABLE $USERS_TABLE(
            $USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $USERNAME TEXT NOT NULL,
            $IMAGE INTEGER NOT NULL
            )
        """
        db?.execSQL(createUsersQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $USERS_TABLE")
        onCreate(db)
    }

}