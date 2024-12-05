package com.mobdeve.s21.pokesoul.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, pokesoulDB, null, DB_ver) {
    companion object {
        private const val pokesoulDB = "my_database.db"
        private const val DB_ver = 1

        //Table: Pokemon
        const val POKEMON_TABLE = "Pokemon"
        const val POKEMON_ID = "pokemon_id"
        const val POKEMON_NAME = "name"
        const val POKEMON_URL = "url"
        const val POKEMON_SPRITE = "sprite"

        //Table: OwnedPokemon
        const val OWNED_POKEMON_TABLE = "OwnedPokemon"
        const val OWNED_POKEMON_ID = "owned_pokemon_id"
        const val OWNED_POKEMON_NICKNAME = "nickname"
        const val OWNED_POKEMON_CAUGHT_LOCATION = "caughtLocation"
        const val OWNED_POKEMON_SAVED_LOCATION = "savedLocation"

        //Table: Run
        const val RUN_TABLE = "Run"
        const val RUN_ID = "run_id"
        const val RUN_NAME = "runName"
        const val RUN_GAME_TITLE = "gameTitle"
        const val RUN_UPDATED_TIME = "updatedTime"

        //Table: timelineLog
        const val TIMELINE_LOG_TABLE = "TimelineLog"
        const val TIMELINE_LOG_ID = "timeline_log_id"
        const val TIMELINE_LOG_EVENT_NAME = "evenName"
        const val TIMELINE_LOG_LOCATION = "location"
        const val TIMELINE_LOG_TIME = "time"
        const val TIMELINE_LOG_NOTES = "notes"
        const val TIMELINE_LOG_DISPLAY_TEAM = "displayTeam"

        //Table: user_run
        const val USER_RUN_TABLE = "UserRun"
        const val USER_RUN_ID = "userRun_id"

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

        val createPokemonQuery = """
            CREATE TABLE $POKEMON_TABLE (
                $POKEMON_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $POKEMON_NAME TEXT NOT NULL,
                $POKEMON_URL TEXT NOT NULL,
                $POKEMON_SPRITE TEXT NOT NULL
            )
        """.trimIndent()

        val createOwnedPokemonQuery = """
            CREATE TABLE $OWNED_POKEMON_TABLE (
                $OWNED_POKEMON_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $OWNED_POKEMON_NICKNAME TEXT NOT NULL,
                $OWNED_POKEMON_CAUGHT_LOCATION TEXT NOT NULL,
                $OWNED_POKEMON_SAVED_LOCATION TEXT NOT NULL,
                FOREIGN KEY (POKEMON_ID) REFERENCES $POKEMON_TABLE(POKEMON_ID),
                FOREIGN KEY (RUN_ID) REFERENCES $RUN_TABLE(RUN_ID)
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
                FOREIGN KEY (RUN_ID) REFERENCES $RUN_TABLE(RUN_ID),
                FOREIGN KEY (OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_ID(OWNED_POKEMON_ID)
            )
        """.trimIndent()

        val createBoxQuery = """
            CREATE TABLE $BOX_TABLE(
                $BOX_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                FOREIGN KEY (RUN_ID) REFERENCES $RUN_TABLE(RUN_ID),
                FOREIGN KEY (OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_ID(OWNED_POKEMON_ID)
            )
        """.trimIndent()

        val createGraveQuery = """
            CREATE TABLE $GRAVE_TABLE(
                $GRAVE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                FOREIGN KEY (RUN_ID) REFERENCES $RUN_TABLE(RUN_ID),
                FOREIGN KEY (OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_ID(OWNED_POKEMON_ID)
            )
        """.trimIndent()

        val createDaycareQuery = """
            CREATE TABLE $DAYCARE_TABLE(
                $DAYCARE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
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
                FOREIGN KEY (RUN_ID) REFERENCES $RUN_TABLE(RUN_ID),
                FOREIGN KEY (TEAM_ID) REFERENCES $TEAM_TABLE(TEAM_ID)
                )
        """.trimIndent()

        val createDeathQuery = """
            CREATE TABLE $DEATHS_TABLE(
            $DEATHS_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            FOREIGN KEY (TIMELINE_LOG_ID) REFERENCES $TIMELINE_LOG_TABLE(TIMELINE_LOG_ID),
            FOREIGN KEY (OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_ID(OWNED_POKEMON_ID)
            )
        """.trimIndent()

        val createCapturesQuery = """
            CREATE TABLE $CAPTURES_TABLE(
            $CAPTURES_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            FOREIGN KEY (TIMELINE_LOG_ID) REFERENCES $TIMELINE_LOG_TABLE(TIMELINE_LOG_ID),
            FOREIGN KEY (OWNED_POKEMON_ID) REFERENCES $OWNED_POKEMON_ID(OWNED_POKEMON_ID)
            )
        """.trimIndent()

        val createUserRunQuery = """
            CREATE TABLE $USER_RUN_TABLE(
            $USER_RUN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            FOREIGN KEY (RUN_ID) REFERENCES $RUN_TABLE(RUN_ID)
            )
        """.trimIndent()

        db?.execSQL(createPokemonQuery)
        db?.execSQL(createOwnedPokemonQuery)
        db?.execSQL(createRunQuery)
        db?.execSQL(createTeamQuery)
        db?.execSQL(createBoxQuery)
        db?.execSQL(createGraveQuery)
        db?.execSQL(createDaycareQuery)
        db?.execSQL(createDeathQuery)
        db?.execSQL(createCapturesQuery)
        db?.execSQL(createTimelineLogQuery)
        db?.execSQL(createUserRunQuery)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){
        db?.execSQL("DROP TABLE IF EXISTS $POKEMON_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $OWNED_POKEMON_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $RUN_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $TIMELINE_LOG_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $TEAM_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $BOX_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $GRAVE_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $DAYCARE_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $DEATHS_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $CAPTURES_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $USER_RUN_TABLE")

    }

}