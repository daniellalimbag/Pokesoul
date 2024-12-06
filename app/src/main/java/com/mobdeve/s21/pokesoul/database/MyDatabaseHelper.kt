package com.mobdeve.s21.pokesoul.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, pokesoulDB, null, DB_VER) {
    companion object {
        private const val pokesoulDB = "pokesoul.db"
        private const val DB_VER = 1
        // Table: Runs
        const val RUNS_TABLE = "Runs"
        const val RUN_ID = "run_id"
        const val RUN_NAME = "run_name"
        const val GAME_TITLE = "game_title"
        const val UPDATED_TIME = "updated_time"

        // Table: RunDetails
        const val RUN_DETAILS_TABLE = "RunDetails"
        const val RUN_DETAIL_ID = "run_detail_id"
        const val RUN_ID_FK = "run_id"
        const val RUN_NAME_DETAIL = "run_name"
        const val RUN_GAME_TITLE_DETAIL = "game_title"
        const val RUN_UPDATED_TIME_DETAIL = "updated_time"
        
        // Table: Players
        const val PLAYERS_TABLE = "Players"
        const val PLAYER_ID = "player_id"
        const val PLAYER_NAME = "player_name"
        const val PLAYER_IMAGE = "player_image"
        const val PLAYER_RUN_ID = "run_id"

        // Table: OwnedPokemon
        const val OWNED_POKEMON_TABLE = "OwnedPokemon"
        const val OWNED_POKEMON_ID = "owned_pokemon_id"
        const val OWNED_POKEMON_NICKNAME = "nickname"
        const val OWNED_POKEMON_CAUGHT_LOCATION = "caught_location"
        const val OWNED_POKEMON_SAVED_LOCATION = "saved_location"
        const val OWNED_POKEMON_URL = "url"
        const val OWNED_POKEMON_SPRITE = "sprite"
        const val OWNED_POKEMON_RUN_ID = "run_id"

        // Table: TimelineLog
        const val TIMELINE_LOG_TABLE = "TimelineLog"
        const val TIMELINE_LOG_ID = "timeline_log_id"
        const val TIMELINE_LOG_EVENT_NAME = "event_name"
        const val TIMELINE_LOG_LOCATION = "location"
        const val TIMELINE_LOG_TIME = "time"
        const val TIMELINE_LOG_NOTES = "notes"
        const val TIMELINE_LOG_DISPLAY_TEAM = "display_team"
        const val TIMELINE_LOG_RUN_ID = "run_id"
        const val TIMELINE_LOG_TEAM_ID = "team_id"

        // Table: Deaths
        const val DEATHS_TABLE = "Deaths"
        const val DEATHS_ID = "death_id"
        const val DEATHS_TIMELINE_LOG_ID = "timeline_log_id"
        const val DEATHS_OWNED_POKEMON_ID = "owned_pokemon_id"

        // Table: Captures
        const val CAPTURES_TABLE = "Captures"
        const val CAPTURES_ID = "capture_id"
        const val CAPTURES_TIMELINE_LOG_ID = "timeline_log_id"
        const val CAPTURES_OWNED_POKEMON_ID = "owned_pokemon_id"

        // Table: Team
        const val TEAM_TABLE = "Team"
        const val TEAM_ID = "team_id"
        const val TEAM_RUN_ID = "run_id"
        const val TEAM_OWNED_POKEMON_ID = "owned_pokemon_id"

        // Table: Box
        const val BOX_TABLE = "Box"
        const val BOX_ID = "box_id"
        const val BOX_RUN_ID = "run_id"
        const val BOX_OWNED_POKEMON_ID = "owned_pokemon_id"

        // Table: Daycare
        const val DAYCARE_TABLE = "Daycare"
        const val DAYCARE_ID = "daycare_id"
        const val DAYCARE_RUN_ID = "run_id"
        const val DAYCARE_OWNED_POKEMON_ID = "owned_pokemon_id"

        // Table: Grave
        const val GRAVE_TABLE = "Grave"
        const val GRAVE_ID = "grave_id"
        const val GRAVE_RUN_ID = "run_id"
        const val GRAVE_OWNED_POKEMON_ID = "owned_pokemon_id"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("DatabaseCheck", "onCreate called")

        val createRunsTableQuery = """
            CREATE TABLE $RUNS_TABLE (
                $RUN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $RUN_NAME TEXT NOT NULL,
                $GAME_TITLE TEXT NOT NULL,
                $UPDATED_TIME TEXT NOT NULL
            )
        """.trimIndent()

        val createRunDetailsTableQuery = """
            CREATE TABLE $RUN_DETAILS_TABLE (
                $RUN_DETAIL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $RUN_ID_FK INTEGER NOT NULL,
                $RUN_NAME_DETAIL TEXT NOT NULL,
                $RUN_GAME_TITLE_DETAIL TEXT NOT NULL,
                $RUN_UPDATED_TIME_DETAIL TEXT NOT NULL,
                FOREIGN KEY ($RUN_ID_FK) REFERENCES $RUNS_TABLE($RUN_ID)
            )
        """.trimIndent()

        val createOwnedPokemonTableQuery = """
            CREATE TABLE $OWNED_POKEMON_TABLE (
                $OWNED_POKEMON_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $OWNED_POKEMON_NICKNAME TEXT NOT NULL,
                $OWNED_POKEMON_CAUGHT_LOCATION TEXT NOT NULL,
                $OWNED_POKEMON_SAVED_LOCATION TEXT NOT NULL,
                $OWNED_POKEMON_URL TEXT NOT NULL,
                $OWNED_POKEMON_SPRITE TEXT NOT NULL,
                $OWNED_POKEMON_RUN_ID INTEGER,
                FOREIGN KEY ($OWNED_POKEMON_RUN_ID) REFERENCES $RUNS_TABLE($RUN_ID)
            )
        """.trimIndent()

        val createTimelineLogTableQuery = """
            CREATE TABLE $TIMELINE_LOG_TABLE (
                $TIMELINE_LOG_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $TIMELINE_LOG_EVENT_NAME TEXT NOT NULL,
                $TIMELINE_LOG_LOCATION TEXT NOT NULL,
                $TIMELINE_LOG_TIME TEXT NOT NULL,
                $TIMELINE_LOG_NOTES TEXT,
                $TIMELINE_LOG_DISPLAY_TEAM INTEGER NOT NULL,
                $TIMELINE_LOG_RUN_ID INTEGER,
                $TIMELINE_LOG_TEAM_ID INTEGER,
                FOREIGN KEY ($TIMELINE_LOG_RUN_ID) REFERENCES $RUNS_TABLE($RUN_ID),
                FOREIGN KEY ($TIMELINE_LOG_TEAM_ID) REFERENCES $TEAM_TABLE($TEAM_ID)
            )
        """.trimIndent()

        val createDeathsTableQuery = """
            CREATE TABLE $DEATHS_TABLE (
                $DEATHS_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $DEATHS_TIMELINE_LOG_ID INTEGER,
                $DEATHS_OWNED_POKEMON_ID INTEGER,
                FOREIGN KEY ($DEATHS_TIMELINE_LOG_ID) REFERENCES $TIMELINE_LOG_TABLE($TIMELINE_LOG_ID),
                FOREIGN KEY ($DEATHS_OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_TABLE($OWNED_POKEMON_ID)
            )
        """.trimIndent()

        val createCapturesTableQuery = """
            CREATE TABLE $CAPTURES_TABLE (
                $CAPTURES_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $CAPTURES_TIMELINE_LOG_ID INTEGER,
                $CAPTURES_OWNED_POKEMON_ID INTEGER,
                FOREIGN KEY ($CAPTURES_TIMELINE_LOG_ID) REFERENCES $TIMELINE_LOG_TABLE($TIMELINE_LOG_ID),
                FOREIGN KEY ($CAPTURES_OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_TABLE($OWNED_POKEMON_ID)
            )
        """.trimIndent()

        val createTeamTableQuery = """
            CREATE TABLE $TEAM_TABLE (
                $TEAM_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $TEAM_RUN_ID INTEGER,
                $TEAM_OWNED_POKEMON_ID INTEGER,
                FOREIGN KEY ($TEAM_RUN_ID) REFERENCES $RUNS_TABLE($RUN_ID),
                FOREIGN KEY ($TEAM_OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_TABLE($OWNED_POKEMON_ID)
            )
        """.trimIndent()

        val createBoxTableQuery = """
            CREATE TABLE $BOX_TABLE (
                $BOX_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $BOX_RUN_ID INTEGER,
                $BOX_OWNED_POKEMON_ID INTEGER,
                FOREIGN KEY ($BOX_RUN_ID) REFERENCES $RUNS_TABLE($RUN_ID),
                FOREIGN KEY ($BOX_OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_TABLE($OWNED_POKEMON_ID)
            )
        """.trimIndent()

        val createDaycareTableQuery = """
            CREATE TABLE $DAYCARE_TABLE (
                $DAYCARE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $DAYCARE_RUN_ID INTEGER,
                $DAYCARE_OWNED_POKEMON_ID INTEGER,
                FOREIGN KEY ($DAYCARE_RUN_ID) REFERENCES $RUNS_TABLE($RUN_ID),
                FOREIGN KEY ($DAYCARE_OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_TABLE($OWNED_POKEMON_ID)
            )
        """.trimIndent()

        val createGraveTableQuery = """
            CREATE TABLE $GRAVE_TABLE (
                $GRAVE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $GRAVE_RUN_ID INTEGER,
                $GRAVE_OWNED_POKEMON_ID INTEGER,
                FOREIGN KEY ($GRAVE_RUN_ID) REFERENCES $RUNS_TABLE($RUN_ID),
                FOREIGN KEY ($GRAVE_OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_TABLE($OWNED_POKEMON_ID)
            )
        """.trimIndent()

        db?.execSQL(createRunsTableQuery)
        db?.execSQL(createRunDetailsTableQuery)
        db?.execSQL(createOwnedPokemonTableQuery)
        db?.execSQL(createTimelineLogTableQuery)
        db?.execSQL(createDeathsTableQuery)
        db?.execSQL(createCapturesTableQuery)
        db?.execSQL(createTeamTableQuery)
        db?.execSQL(createBoxTableQuery)
        db?.execSQL(createDaycareTableQuery)
        db?.execSQL(createGraveTableQuery)

        db?.let {
            Log.d("DatabaseCheck", "Insert being called")
            insertRunDummyValues(it)
            insertOwnedPokemonDummyValues(it)
            insertRunDetailsDummyValues(it)
            insertTimelineLogDummyValues(it)
            insertTeamDummyValues(it)
            insertBoxDummyValues(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $RUNS_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $RUN_DETAILS_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $OWNED_POKEMON_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $TIMELINE_LOG_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $DEATHS_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $CAPTURES_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $TEAM_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $BOX_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $DAYCARE_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $GRAVE_TABLE")
        onCreate(db)
    }

    @SuppressLint("Range")
    private fun insertRunDummyValues(db: SQLiteDatabase) {
        val contentValues = ContentValues()

        // Insert first run
        contentValues.put(RUN_NAME, "Best Run Ever")
        contentValues.put(GAME_TITLE, "SoulSilver")
        contentValues.put(UPDATED_TIME, "2024-10-14T17:15:23.000Z")
        db.insert(RUNS_TABLE, null, contentValues)

        // Insert second run
        contentValues.put(RUN_NAME, "Another Run")
        contentValues.put(GAME_TITLE, "HeartGold")
        contentValues.put(UPDATED_TIME, "2024-10-17T22:19:44.000Z")
        db.insert(RUNS_TABLE, null, contentValues)
    }

    @SuppressLint("Range")
    private fun insertOwnedPokemonDummyValues(db: SQLiteDatabase) {
        val contentValues = ContentValues()
        contentValues.put(OWNED_POKEMON_NICKNAME, "Pikachu")
        contentValues.put(OWNED_POKEMON_CAUGHT_LOCATION, "PokeCenter")
        contentValues.put(OWNED_POKEMON_SAVED_LOCATION, "Gym")
        contentValues.put(OWNED_POKEMON_URL, "https://pokeapi.co/api/v2/pokemon/pikachu")
        contentValues.put(OWNED_POKEMON_SPRITE, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png")
        contentValues.put(OWNED_POKEMON_RUN_ID, 1) // Set the run_id
        db.insert(OWNED_POKEMON_TABLE, null, contentValues)
    }

    @SuppressLint("Range")
    private fun insertRunDetailsDummyValues(db: SQLiteDatabase) {
        val contentValues = ContentValues()

        // Insert run details for first run
        contentValues.put(RUN_ID_FK, 1)
        contentValues.put(RUN_NAME_DETAIL, "Best Run Ever")
        contentValues.put(RUN_GAME_TITLE_DETAIL, "SoulSilver")
        contentValues.put(RUN_UPDATED_TIME_DETAIL, "2024-10-14T17:15:23.000Z")
        db.insert(RUN_DETAILS_TABLE, null, contentValues)

        // Insert run details for second run
        contentValues.clear()
        contentValues.put(RUN_ID_FK, 2)
        contentValues.put(RUN_NAME_DETAIL, "Another Run")
        contentValues.put(RUN_GAME_TITLE_DETAIL, "HeartGold")
        contentValues.put(RUN_UPDATED_TIME_DETAIL, "2024-10-17T22:19:44.000Z")
        db.insert(RUN_DETAILS_TABLE, null, contentValues)
    }

    @SuppressLint("Range")
    private fun insertTimelineLogDummyValues(db: SQLiteDatabase) {
        val contentValues = ContentValues()

        // First timeline log entry
        contentValues.put(TIMELINE_LOG_EVENT_NAME, "Started Journey")
        contentValues.put(TIMELINE_LOG_LOCATION, "New Bark Town")
        contentValues.put(TIMELINE_LOG_TIME, "2024-10-14T18:00:00.000Z")
        contentValues.put(TIMELINE_LOG_NOTES, "First steps into the world")
        contentValues.put(TIMELINE_LOG_DISPLAY_TEAM, 1)
        contentValues.put(TIMELINE_LOG_RUN_ID, 1)
        db.insert(TIMELINE_LOG_TABLE, null, contentValues)

        // Second timeline log entry
        contentValues.clear()
        contentValues.put(TIMELINE_LOG_EVENT_NAME, "First Gym Battle")
        contentValues.put(TIMELINE_LOG_LOCATION, "Violet City")
        contentValues.put(TIMELINE_LOG_TIME, "2024-10-15T14:30:00.000Z")
        contentValues.put(TIMELINE_LOG_NOTES, "Defeated Falkner")
        contentValues.put(TIMELINE_LOG_DISPLAY_TEAM, 1)
        contentValues.put(TIMELINE_LOG_RUN_ID, 1)
        db.insert(TIMELINE_LOG_TABLE, null, contentValues)
    }

    @SuppressLint("Range")
    private fun insertTeamDummyValues(db: SQLiteDatabase) {
        val contentValues = ContentValues()
        contentValues.put(TEAM_RUN_ID, 1)
        contentValues.put(TEAM_OWNED_POKEMON_ID, 1)
        db.insert(TEAM_TABLE, null, contentValues)
    }

    @SuppressLint("Range")
    private fun insertBoxDummyValues(db: SQLiteDatabase) {
        val contentValues = ContentValues()

        contentValues.put(BOX_RUN_ID, 1)
        contentValues.put(BOX_OWNED_POKEMON_ID, 2)
        db.insert(BOX_TABLE, null, contentValues)
    }
}
