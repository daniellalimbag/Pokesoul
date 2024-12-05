package com.mobdeve.s21.pokesoul.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.util.Log

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, pokesoulDB, null, DB_VER) {
    companion object {
        private const val pokesoulDB = "my_database.db"
        private const val DB_VER = 3

        //Table: OwnedPokemon
        const val OWNED_POKEMON_TABLE = "OwnedPokemon"
        const val OWNED_POKEMON_ID = "owned_pokemon_id"
        const val OWNED_POKEMON_NICKNAME = "nickname"
        const val OWNED_POKEMON_CAUGHT_LOCATION = "caughtLocation"
        const val OWNED_POKEMON_SAVED_LOCATION = "savedLocation"
        const val OWNED_POKEMON_URL = "url"
        const val OWNED_POKEMON_SPRITE = "sprite"

        //Table: Run
        const val RUN_TABLE = "Run"
        const val RUN_ID = "run_id"
        const val RUN_NAME = "runName"
        const val RUN_GAME_TITLE = "gameTitle"
        const val RUN_UPDATED_TIME = "updatedTime"

        //Table: timelineLog
        const val TIMELINE_LOG_TABLE = "TimelineLog"
        const val TIMELINE_LOG_ID = "timeline_log_id"
        const val TIMELINE_LOG_EVENT_NAME = "eventName"
        const val TIMELINE_LOG_LOCATION = "location"
        const val TIMELINE_LOG_TIME = "time"
        const val TIMELINE_LOG_NOTES = "notes"
        const val TIMELINE_LOG_DISPLAY_TEAM = "displayTeam"

        //Table: deaths
        const val DEATHS_TABLE = "Deaths"
        const val DEATHS_ID = "Death_id"

        //Table: captures
        const val CAPTURES_TABLE = "Captures"
        const val CAPTURES_ID = "Captures_id"

        //Table: Team
        const val TEAM_TABLE = "Team"
        const val TEAM_ID = "Team_id"

        //Table: Box
        const val BOX_TABLE = "Box"
        const val BOX_ID = "Box_id"

        //Table: Grave
        const val GRAVE_TABLE = "Grave"
        const val GRAVE_ID = "Grave_id"

        //Table: Daycare
        const val DAYCARE_TABLE = "Daycare"
        const val DAYCARE_ID = "Daycare_id"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("DatabaseCheck", "onCreate called")
        val createOwnedPokemonQuery = """
    CREATE TABLE $OWNED_POKEMON_TABLE (
        $OWNED_POKEMON_ID INTEGER PRIMARY KEY AUTOINCREMENT,
        $OWNED_POKEMON_NICKNAME TEXT NOT NULL,
        $OWNED_POKEMON_CAUGHT_LOCATION TEXT NOT NULL,
        $OWNED_POKEMON_SAVED_LOCATION TEXT NOT NULL,
        $OWNED_POKEMON_URL TEXT NOT NULL,
        $OWNED_POKEMON_SPRITE TEXT NOT NULL
    )
""".trimIndent()

        val createRunQuery = """
            CREATE TABLE $RUN_TABLE (
                $RUN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $RUN_NAME TEXT NOT NULL,
                $RUN_GAME_TITLE TEXT NOT NULL,
                $RUN_UPDATED_TIME TEXT NOT NULL)
        """.trimIndent()

        val createTeamQuery = """
    CREATE TABLE $TEAM_TABLE(
        $TEAM_ID INTEGER PRIMARY KEY AUTOINCREMENT,
        $RUN_ID INTEGER,
        $OWNED_POKEMON_ID INTEGER, 
        FOREIGN KEY ($RUN_ID) REFERENCES $RUN_TABLE($RUN_ID),
        FOREIGN KEY ($OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_TABLE($OWNED_POKEMON_ID)
    )
""".trimIndent()


        val createBoxQuery = """
    CREATE TABLE $BOX_TABLE(
        $BOX_ID INTEGER PRIMARY KEY AUTOINCREMENT,
        $RUN_ID INTEGER,  -- Add the RUN_ID column
        $OWNED_POKEMON_ID INTEGER,  -- Add the OWNED_POKEMON_ID column
        FOREIGN KEY ($RUN_ID) REFERENCES $RUN_TABLE($RUN_ID),
        FOREIGN KEY ($OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_TABLE($OWNED_POKEMON_ID)
    )
""".trimIndent()


        val createGraveQuery = """
    CREATE TABLE $GRAVE_TABLE(
        $GRAVE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
        $RUN_ID INTEGER,  -- Define the RUN_ID column explicitly here
        $OWNED_POKEMON_ID INTEGER,  -- Define the OWNED_POKEMON_ID column here
        FOREIGN KEY ($RUN_ID) REFERENCES $RUN_TABLE($RUN_ID),  -- Add the foreign key for RUN_ID
        FOREIGN KEY ($OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_TABLE($OWNED_POKEMON_ID)  -- Add the foreign key for OWNED_POKEMON_ID
    )
""".trimIndent()


        val createDaycareQuery = """
            CREATE TABLE $DAYCARE_TABLE(
                $DAYCARE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $RUN_ID INTEGER,
                $OWNED_POKEMON_ID INTEGER,
                FOREIGN KEY (RUN_ID) REFERENCES $RUN_TABLE(RUN_ID),
                FOREIGN KEY (OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_ID(OWNED_POKEMON_ID)
            )
        """.trimIndent()

        val createTimelineLogQuery = """
    CREATE TABLE $TIMELINE_LOG_TABLE (
        $TIMELINE_LOG_ID INTEGER PRIMARY KEY AUTOINCREMENT,
        $TIMELINE_LOG_EVENT_NAME TEXT NOT NULL,
        $TIMELINE_LOG_LOCATION TEXT NOT NULL,
        $TIMELINE_LOG_TIME TEXT NOT NULL,
        $TIMELINE_LOG_NOTES TEXT NOT NULL,
        $TIMELINE_LOG_DISPLAY_TEAM BOOLEAN NOT NULL,
        $RUN_ID INTEGER,  -- Define RUN_ID column here
        $TEAM_ID INTEGER,  -- Define TEAM_ID column here
        FOREIGN KEY ($RUN_ID) REFERENCES $RUN_TABLE($RUN_ID),
        FOREIGN KEY ($TEAM_ID) REFERENCES $TEAM_TABLE($TEAM_ID)
    )
""".trimIndent()


        val createDeathsQuery = """
    CREATE TABLE $DEATHS_TABLE (
        $DEATHS_ID INTEGER PRIMARY KEY AUTOINCREMENT,
        $TIMELINE_LOG_ID INTEGER,  -- Define the TIMELINE_LOG_ID column here
        $OWNED_POKEMON_ID INTEGER,  -- Define the OWNED_POKEMON_ID column here
        FOREIGN KEY ($TIMELINE_LOG_ID) REFERENCES $TIMELINE_LOG_TABLE($TIMELINE_LOG_ID),  -- Add the foreign key for TIMELINE_LOG_ID
        FOREIGN KEY ($OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_TABLE($OWNED_POKEMON_ID)  -- Add the foreign key for OWNED_POKEMON_ID
    )
""".trimIndent()


        val createCapturesQuery = """
            CREATE TABLE $CAPTURES_TABLE(
            $CAPTURES_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $TIMELINE_LOG_ID INTEGER,
            $OWNED_POKEMON_ID INTEGER,
            FOREIGN KEY (TIMELINE_LOG_ID) REFERENCES $TIMELINE_LOG_TABLE(TIMELINE_LOG_ID),
            FOREIGN KEY (OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_ID(OWNED_POKEMON_ID)
            )
        """.trimIndent()

        db?.execSQL(createOwnedPokemonQuery)
        db?.execSQL(createRunQuery)
        db?.execSQL(createTeamQuery)
        db?.execSQL(createBoxQuery)
        db?.execSQL(createGraveQuery)
        db?.execSQL(createDaycareQuery)
        db?.execSQL(createDeathsQuery)
        db?.execSQL(createCapturesQuery)
        db?.execSQL(createTimelineLogQuery)



        if (db != null) {
            Log.d("DatabaseCheck", "Insert being called")
            insertRunDummyValues(db)
            insertOwnedPokemonDummyValues(db)
            insertTeamDummyValues(db)
            insertTimelineLogDummyValues(db)
            insertCapturesDummyValues(db)
            insertDeathsDummyValues(db)
            insertBoxDummyValues(db)
            insertGraveDummyValues(db)
            insertDaycareDummyValues(db)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){
        db?.execSQL("DROP TABLE IF EXISTS $OWNED_POKEMON_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $RUN_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $TIMELINE_LOG_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $TEAM_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $BOX_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $GRAVE_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $DAYCARE_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $DEATHS_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $CAPTURES_TABLE")

    }

    @SuppressLint("Range")
    private fun insertRunDummyValues(db: SQLiteDatabase){
        val contentValues = ContentValues()

        contentValues.put(RUN_NAME, "Run 1")
        contentValues.put(RUN_GAME_TITLE, "Game 1")
        contentValues.put(RUN_UPDATED_TIME, "2023-09-01 12:00:00")
        db.insert(RUN_TABLE, null, contentValues)

    }

    @SuppressLint("Range")
    private fun insertOwnedPokemonDummyValues(db: SQLiteDatabase){
        val contentValues = ContentValues()
        contentValues.put(OWNED_POKEMON_NICKNAME, "Pikachu")
        contentValues.put(OWNED_POKEMON_CAUGHT_LOCATION, "PokeCenter")
        contentValues.put(OWNED_POKEMON_SAVED_LOCATION, "Gym")
        contentValues.put(OWNED_POKEMON_URL, "https://pokeapi.co/api/v2/pokemon/pikachu")
        contentValues.put(OWNED_POKEMON_SPRITE, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png")
        db.insert(OWNED_POKEMON_TABLE, null, contentValues)

    }

    @SuppressLint("Range")
    private fun insertTeamDummyValues(db: SQLiteDatabase){
        val contentValues = ContentValues()
        contentValues.put(OWNED_POKEMON_ID, 1)
        contentValues.put(RUN_ID, 1)
        db.insert(TEAM_TABLE, null, contentValues)

    }

    @SuppressLint("Range", "Recycle")
    private fun insertTimelineLogDummyValues(db: SQLiteDatabase){
        val contentValues = ContentValues()
        contentValues.put(TIMELINE_LOG_EVENT_NAME, "Event 1")
        contentValues.put(TIMELINE_LOG_LOCATION, "Location 1")
        contentValues.put(TIMELINE_LOG_TIME, "2023-09-01 12:00:00")
        contentValues.put(TIMELINE_LOG_NOTES, "Notes 1")
        contentValues.put(TIMELINE_LOG_DISPLAY_TEAM, true)
        contentValues.put(RUN_ID, 1)
        contentValues.put(TEAM_ID, 1)
        db.insert(TIMELINE_LOG_TABLE, null, contentValues)
        Log.d("DatabaseCheck", "Log Dummy called")

    }

    private fun insertCapturesDummyValues(db: SQLiteDatabase){
        val contentValues = ContentValues()
        contentValues.put(TIMELINE_LOG_ID, 1)
        contentValues.put(OWNED_POKEMON_ID, 1)
        db.insert(CAPTURES_TABLE, null, contentValues)
    }

    private fun insertDeathsDummyValues(db: SQLiteDatabase){
        val contentValues = ContentValues()
        contentValues.put(TIMELINE_LOG_ID, 1)
        contentValues.put(OWNED_POKEMON_ID, 1)
        db.insert(DEATHS_TABLE, null, contentValues)
    }

    private fun insertBoxDummyValues(db: SQLiteDatabase){
        val contentValues = ContentValues()
        contentValues.put(RUN_ID, 1)
        contentValues.put(OWNED_POKEMON_ID, 1)
        db.insert(BOX_TABLE, null, contentValues)
    }

    private fun insertGraveDummyValues(db: SQLiteDatabase){
        val contentValues = ContentValues()
        contentValues.put(RUN_ID, 1)
        contentValues.put(OWNED_POKEMON_ID, 1)
        db.insert(GRAVE_TABLE, null, contentValues)
    }

    private fun insertDaycareDummyValues(db: SQLiteDatabase){
        val contentValues = ContentValues()
        contentValues.put(RUN_ID, 1)
        contentValues.put(OWNED_POKEMON_ID, 1)
        db.insert(DAYCARE_TABLE, null, contentValues)
    }



}