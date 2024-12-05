package com.mobdeve.s21.pokesoul.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.mobdeve.s21.pokesoul.model.TimelineLog

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
            arrayOf(id.toString())
        ).also { db.close() }
    }
    fun deleteRunEntry(id: Long): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        return db.delete(
            MyDatabaseHelper.RUN_TABLE,
            "${MyDatabaseHelper.RUN_ID} = ?",
            arrayOf(id.toString())
        ).also { db.close() }
    }

    //Insert,Update,Delete OwnedPokemon
    fun insertOwnedPokemonEntry(
        nickname: String,
        caughtLocation: String,
        savedLocation: String,
        runId: Long
    ): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.OWNED_POKEMON_NICKNAME, nickname)
            put(MyDatabaseHelper.OWNED_POKEMON_CAUGHT_LOCATION, caughtLocation)
            put(MyDatabaseHelper.OWNED_POKEMON_SAVED_LOCATION, savedLocation)
            put(MyDatabaseHelper.RUN_ID, runId)
        }
        return db.insert(MyDatabaseHelper.OWNED_POKEMON_TABLE, null, values).also { db.close() }
    }
    fun updateOwnedPokemonEntry(
        id: Long,
        nickname: String,
        caughtLocation: String,
        savedLocation: String
    ): Int {
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
        return db.delete(
            MyDatabaseHelper.OWNED_POKEMON_TABLE,
            "${MyDatabaseHelper.OWNED_POKEMON_ID} = ?",
            arrayOf(id.toString())
        ).also { db.close() }
    }

    //Insert,Update,Delete TimeLineLog
    fun insertTimelineLogEntry(eventName: String, location: String, time: String, displayTeam: Boolean, runId: Long, teamId: Long): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.TIMELINE_LOG_EVENT_NAME, eventName)
            put(MyDatabaseHelper.TIMELINE_LOG_LOCATION, location)
            put(MyDatabaseHelper.TIMELINE_LOG_TIME, time)
            put(MyDatabaseHelper.TIMELINE_LOG_DISPLAY_TEAM, displayTeam)
            put(MyDatabaseHelper.RUN_ID, runId)
            put(MyDatabaseHelper.TEAM_ID, teamId)
        }
        return db.insert(MyDatabaseHelper.TIMELINE_LOG_TABLE, null, values).also { db.close() }
    }

    fun updateTimelineLogEntry(id: Long, eventName: String, location: String, time: String, displayTeam: Boolean) {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.TIMELINE_LOG_EVENT_NAME, eventName)
            put(MyDatabaseHelper.TIMELINE_LOG_LOCATION, location)
            put(MyDatabaseHelper.TIMELINE_LOG_TIME, time)
            put(MyDatabaseHelper.TIMELINE_LOG_DISPLAY_TEAM, displayTeam)
        }
        db.update(
            MyDatabaseHelper.TIMELINE_LOG_TABLE,
            values,
            "${MyDatabaseHelper.TIMELINE_LOG_ID} = ?",
            arrayOf(id.toString())
        ).also { db.close() }
    }

    fun deleteTimelineLogEntry(id: Long){
        val db: SQLiteDatabase = dbHelper.writableDatabase
        db.delete(
            MyDatabaseHelper.TIMELINE_LOG_TABLE,
            "${MyDatabaseHelper.TIMELINE_LOG_ID} = ?",
            arrayOf(id.toString())
        ).also { db.close() }
    }

    //Insert, Update, Delete Team
    fun insertTeamEntry(ownedPokemonId: Long, runId: Long): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.OWNED_POKEMON_ID, ownedPokemonId)
            put(MyDatabaseHelper.RUN_ID, runId)
        }
        return db.insert(MyDatabaseHelper.TEAM_TABLE, null, values).also { db.close() }
    }
    fun updateTeamEntry(id: Long, ownedPokemonId: Long, runId: Long) {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.OWNED_POKEMON_ID, ownedPokemonId)
            put(MyDatabaseHelper.RUN_ID, runId)
        }
        db.update(
            MyDatabaseHelper.TEAM_TABLE,
            values,
            "${MyDatabaseHelper.TEAM_ID} = ?",
            arrayOf(id.toString())
        ).also { db.close() }
    }
    fun deleteTeamEntry(id: Long){
        val db: SQLiteDatabase = dbHelper.writableDatabase
        db.delete(
            MyDatabaseHelper.TEAM_TABLE,
            "${MyDatabaseHelper.TEAM_ID} = ?",
            arrayOf(id.toString())
        ).also { db.close() }
    }

    //Insert, Update, Delete Box
    fun insertBoxEntry(ownedPokemonId: Long, runId: Long): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.OWNED_POKEMON_ID, ownedPokemonId)
            put(MyDatabaseHelper.RUN_ID, runId)
        }
        return db.insert(MyDatabaseHelper.BOX_TABLE, null, values).also { db.close() }
    }
    fun updateBoxEntry(id: Long, ownedPokemonId: Long, runId: Long) {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.OWNED_POKEMON_ID, ownedPokemonId)
            put(MyDatabaseHelper.RUN_ID, runId)
        }
        db.update(
            MyDatabaseHelper.BOX_TABLE,
            values,
            "${MyDatabaseHelper.BOX_ID} = ?",
            arrayOf(id.toString())
        ).also { db.close() }
    }
    fun deleteBoxEntry(id: Long){
        val db: SQLiteDatabase = dbHelper.writableDatabase
        db.delete(
            MyDatabaseHelper.BOX_TABLE,
            "${MyDatabaseHelper.BOX_ID} = ?",
            arrayOf(id.toString())
        ).also { db.close() }
    }

    //Insert, Update, Delete Grave
    fun insertGraveEntry(ownedPokemonId: Long, runId: Long): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.OWNED_POKEMON_ID, ownedPokemonId)
            put(MyDatabaseHelper.RUN_ID, runId)
        }
        return db.insert(MyDatabaseHelper.GRAVE_TABLE, null, values).also { db.close() }
    }
    fun updateGraveEntry(id: Long, ownedPokemonId: Long, runId: Long){
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.OWNED_POKEMON_ID, ownedPokemonId)
            put(MyDatabaseHelper.RUN_ID, runId)
        }
        db.update(
            MyDatabaseHelper.GRAVE_TABLE,
            values,
            "${MyDatabaseHelper.GRAVE_ID} = ?",
            arrayOf(id.toString())
        ).also { db.close() }
    }
    fun deleteGraveEntry(id: Long){
        val db: SQLiteDatabase = dbHelper.writableDatabase
        db.delete(
            MyDatabaseHelper.GRAVE_TABLE,
            "${MyDatabaseHelper.GRAVE_ID} = ?",
            arrayOf(id.toString())
        ).also { db.close() }
    }

    //Insert, Update, Delete Daycare
    fun insertDaycareEntry(ownedPokemonId: Long, runId: Long): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.OWNED_POKEMON_ID, ownedPokemonId)
            put(MyDatabaseHelper.RUN_ID, runId)
        }
        return db.insert(MyDatabaseHelper.DAYCARE_TABLE, null, values).also { db.close() }
    }
    fun updateDaycareEntry(id: Long, ownedPokemonId: Long, runId: Long){
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.OWNED_POKEMON_ID, ownedPokemonId)
            put(MyDatabaseHelper.RUN_ID, runId)
        }
        db.update(
            MyDatabaseHelper.DAYCARE_TABLE,
            values,
            "${MyDatabaseHelper.DAYCARE_ID} = ?",
            arrayOf(id.toString())).also { db.close() }
    }
    fun deleteDaycareEntry(id: Long){
        val db: SQLiteDatabase = dbHelper.writableDatabase
        db.delete(
            MyDatabaseHelper.DAYCARE_TABLE,
            "${MyDatabaseHelper.DAYCARE_ID} = ?",
            arrayOf(id.toString())
        ).also { db.close() }
    }

    //Insert, Update, Delete Death
    fun insertDeathEntry(timelineLogId: Long, ownedPokemonId: Long){
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.TIMELINE_LOG_ID, timelineLogId)
            put(MyDatabaseHelper.OWNED_POKEMON_ID,ownedPokemonId)
    }
        db.insert(MyDatabaseHelper.DEATHS_TABLE, null, values).also { db.close() }
    }
    fun updateDeathEntry(id: Long, timelineLogId: Long, ownedPokemonId: Long){
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.TIMELINE_LOG_ID, timelineLogId)
            put(MyDatabaseHelper.OWNED_POKEMON_ID,ownedPokemonId)
        }
        db.update(
            MyDatabaseHelper.DEATHS_TABLE,
            values,
            "${MyDatabaseHelper.DEATHS_ID} = ?",
            arrayOf(id.toString())).also { db.close() }
    }
    fun deleteDeathEntry(id: Long){
        val db: SQLiteDatabase = dbHelper.writableDatabase
        db.delete(
            MyDatabaseHelper.DEATHS_TABLE,
            "${MyDatabaseHelper.DEATHS_ID} = ?",
            arrayOf(id.toString())
        ).also { db.close() }
    }

    //Insert, Update, Delete Capture
    fun insertCaptureEntry(timelineLogId: Long, ownedPokemonId: Long){
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.TIMELINE_LOG_ID, timelineLogId)
            put(MyDatabaseHelper.OWNED_POKEMON_ID,ownedPokemonId)
        }
        db.insert(MyDatabaseHelper.CAPTURES_TABLE, null, values).also { db.close() }
    }
    fun updateCaptureEntry(id: Long, timelineLogId: Long, ownedPokemonId: Long){
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDatabaseHelper.TIMELINE_LOG_ID, timelineLogId)
            put(MyDatabaseHelper.OWNED_POKEMON_ID,ownedPokemonId)
        }
        db.update(
            MyDatabaseHelper.CAPTURES_TABLE,
            values,
            "${MyDatabaseHelper.CAPTURES_ID} = ?",
            arrayOf(id.toString())).also { db.close() }
    }
    fun deleteCaptureEntry(id: Long){
        val db: SQLiteDatabase = dbHelper.writableDatabase
        db.delete(
            MyDatabaseHelper.CAPTURES_TABLE,
            "${MyDatabaseHelper.CAPTURES_ID} = ?",
            arrayOf(id.toString())).also { db.close() }
    }
}

