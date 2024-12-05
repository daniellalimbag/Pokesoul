package com.mobdeve.s21.pokesoul.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DatabaseManager(context: Context) {
    private val dbHelper = MyDatabaseHelper(context)

    //Insert,Update,Delete Run
    fun insertRunEntry(runName: String, gameTitle: String, updatedTime: String): Long? {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.RUN_NAME, runName)
            put(MyDatabaseHelper.RUN_GAME_TITLE, gameTitle)
            put(MyDatabaseHelper.RUN_UPDATED_TIME, updatedTime)
        }
        return db.insert(MyDatabaseHelper.RUN_TABLE, null, values).also { db.close() }
    }
    fun updateRunEntry(id: Long, runName: String, gameTitle: String, updatedTime: String): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.RUN_NAME, runName)
            put(MyDatabaseHelper.RUN_GAME_TITLE, gameTitle)
            put(MyDatabaseHelper.RUN_UPDATED_TIME, updatedTime)
        }
        return db.update(
            MyDatabaseHelper.RUN_TABLE,
            values,
            "${MyDatabaseHelper.RUN_ID} = ?",
            arrayOf(id.toString())).also { db.close() }
    }
    fun deleteRunEntry(id: Long): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.RUN_ID, id)
        }
        return db.delete(
            MyDatabaseHelper.RUN_TABLE,
            "${MyDatabaseHelper.RUN_ID} = ?",
            arrayOf(id.toString())
        ).also { db.close() }
    }


    //Insert,Update,Delete OwnedPokemon
    fun insertOwnedPokemonEntry(nickname: String, caughtLocation: String, savedLocation: String, runId: Long): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(MyDatabaseHelper.OWNED_POKEMON_NICKNAME, nickname)
                put(MyDatabaseHelper.OWNED_POKEMON_CAUGHT_LOCATION, caughtLocation)
                put(MyDatabaseHelper.OWNED_POKEMON_SAVED_LOCATION, savedLocation)
                put(MyDatabaseHelper.RUN_ID, runId)
            }
            return  db.insert(MyDatabaseHelper.OWNED_POKEMON_TABLE, null, values).also { db.close() }
    }

    fun updateOwnedPokemonEntry(id: Long, nickname: String, caughtLocation: String, savedLocation: String): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.OWNED_POKEMON_NICKNAME, nickname)
            put(MyDatabaseHelper.OWNED_POKEMON_CAUGHT_LOCATION, caughtLocation)
            put(MyDatabaseHelper.OWNED_POKEMON_SAVED_LOCATION, savedLocation)
        }
        return db.update(
            MyDatabaseHelper.OWNED_POKEMON_TABLE,
            values,
            "${MyDatabaseHelper.OWNED_POKEMON_ID} = ?",
            arrayOf(id.toString())
        ).also { db.close() }
    }

    fun deleteOwnedPokemonEntry(id: Long): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.OWNED_POKEMON_ID, id)
        }
        return db.delete(
            MyDatabaseHelper.OWNED_POKEMON_TABLE,
            "${MyDatabaseHelper.OWNED_POKEMON_ID} = ?",
            arrayOf(id.toString())
        ).also { db.close() }
    }

}