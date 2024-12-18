package com.mobdeve.s21.pokesoul.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.mobdeve.s21.pokesoul.model.Run
import com.mobdeve.s21.pokesoul.model.*
import org.json.JSONArray
import org.json.JSONObject

class DatabaseManager(context: Context) {
    private val dbHelper: MyDatabaseHelper = MyDatabaseHelper(context)

    fun deleteRunById(runId: Int):Boolean{
        val db = dbHelper.writableDatabase
        return try {
            val rowsDeleted = db.delete(
                MyDatabaseHelper.RUNS_TABLE,        // Table name
                "${MyDatabaseHelper.RUN_ID} = ?",      // WHERE clause
                arrayOf(runId.toString()) // Arguments for the WHERE clause
            )
            deleteRunDetailsByRunId(runId)
            rowsDeleted > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false // Return false if there was an error
        } finally {
            db.close() // Close the database to free resources
        }
    }

    fun deleteRunDetailsByRunId(runId: Int):Boolean{
        val db = dbHelper.writableDatabase
        return try {
            // Attempt to delete the row from the OwnedPokemon table
            val rowsDeleted = db.delete(
                MyDatabaseHelper.RUN_DETAILS_TABLE,        // Table name
                "${MyDatabaseHelper.RUN_ID_FK} = ?",      // WHERE clause
                arrayOf(runId.toString()) // Arguments for the WHERE clause
            )
            rowsDeleted > 0 // Return true if at least one row was deleted
        } catch (e: Exception) {
            e.printStackTrace()
            false // Return false if there was an error
        } finally {
            db.close() // Close the database to free resources
        }
    }

    fun deleteFromteam(pokemonId: Int){
        val db = dbHelper.writableDatabase
        db.delete(MyDatabaseHelper.TEAM_TABLE, "${MyDatabaseHelper.TEAM_OWNED_POKEMON_ID} = ?", arrayOf(pokemonId.toString()))
    }

    fun deleteFromBox(pokemonId: Int){
        val db = dbHelper.writableDatabase
        db.delete(MyDatabaseHelper.BOX_TABLE, "${MyDatabaseHelper.TEAM_OWNED_POKEMON_ID} = ?", arrayOf(pokemonId.toString()))
    }
    fun deleteFromDaycare(pokemonId: Int){
        val db = dbHelper.writableDatabase
        db.delete(MyDatabaseHelper.GRAVE_TABLE, "${MyDatabaseHelper.TEAM_OWNED_POKEMON_ID} = ?", arrayOf(pokemonId.toString()))
    }
    fun deleteFromGrave(pokemonId: Int){
        val db = dbHelper.writableDatabase
        db.delete(MyDatabaseHelper.GRAVE_TABLE, "${MyDatabaseHelper.TEAM_OWNED_POKEMON_ID} = ?", arrayOf(pokemonId.toString()))
    }

    @SuppressLint("SuspiciousIndentation")
    fun deletePokemonById(pokemonId: Int, savedLocation: String): Boolean {
        val db = dbHelper.writableDatabase
        return try {
            // Attempt to delete the row from the OwnedPokemon table
            val rowsDeleted = db.delete(
                MyDatabaseHelper.OWNED_POKEMON_TABLE,        // Table name
                "${MyDatabaseHelper.OWNED_POKEMON_ID} = ?",      // WHERE clause
                arrayOf(pokemonId.toString()) // Arguments for the WHERE clause
            )
                when (savedLocation.lowercase()) {
                    "team" -> deleteFromteam(pokemonId)
                    "box" -> deleteFromBox(pokemonId)
                    "daycare" -> deleteFromDaycare(pokemonId)
                    "grave" -> deleteFromGrave(pokemonId)
                    else -> Log.e("DatabaseLog", "Invalid savedLocation: $savedLocation")
            }
            rowsDeleted > 0 // Return true if at least one row was deleted
        } catch (e: Exception) {
            e.printStackTrace()
            false // Return false if there was an error
        } finally {
            db.close() // Close the database to free resources
        }
    }


    fun insertRun(run: Run): Long {
        val db = dbHelper.writableDatabase
        return try {
            val contentValues = ContentValues().apply {
                put(MyDatabaseHelper.RUN_NAME, run.runName)
                put(MyDatabaseHelper.GAME_TITLE, run.gameTitle)
                put(MyDatabaseHelper.UPDATED_TIME, run.updatedTime)
            }
            val runId = db.insert(MyDatabaseHelper.RUNS_TABLE, null, contentValues)
            Log.d("DatabaseLog", "Run inserted with ID: $runId")
            runId
        } catch (e: Exception) {
            Log.e("DatabaseLog", "Error inserting run", e)
            -1
        } finally {
            db.close()
        }
    }

    fun insertRunDetails(runId: Long, run: Run) {
        val db = dbHelper.writableDatabase
        try {
            val contentValues = ContentValues().apply {
                put(MyDatabaseHelper.RUN_ID_FK, runId)
                put(MyDatabaseHelper.RUN_NAME_DETAIL, run.runName)
                put(MyDatabaseHelper.RUN_GAME_TITLE_DETAIL, run.gameTitle)
                put(MyDatabaseHelper.RUN_UPDATED_TIME_DETAIL, run.updatedTime)
            }
            db.insert(MyDatabaseHelper.RUN_DETAILS_TABLE, null, contentValues)
            Log.d("DatabaseLog", "Run details inserted for run ID: $runId")
        } catch (e: Exception) {
            Log.e("DatabaseLog", "Error inserting run details", e)
        } finally {
            db.close()
        }
    }

    fun insertPlayer(player: Player): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        return try {
            val contentValues = ContentValues().apply {
                put(MyDatabaseHelper.PLAYER_NAME, player.name)
                put(MyDatabaseHelper.PLAYER_IMAGE, player.image)
                // Assuming PLAYER_RUN_ID is available, you can include it if necessary.
            }
            val playerId = db.insert(MyDatabaseHelper.PLAYERS_TABLE, null, contentValues)
            Log.d("DatabaseLog", "Player entry inserted with ID: $playerId")
            playerId
        } catch (e: Exception) {
            Log.e("DatabaseLog", "Error inserting player entry", e)
            -1
        } finally {
            db.close()
        }
    }

    fun insertOwnedPokemonEntry(
        runId: Int,
        ownerId: Int,
        name: String,
        nickname: String,
        caughtLocation: String,
        savedLocation: String,
        url: String,
        sprite: String
    ): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        Log.d("DatabaseLog", "Inserting Pokémon entry: nickname=$nickname, caughtLocation=$caughtLocation, savedLocation=$savedLocation, url=$url, sprite=$sprite, runId=$runId, ownerId=$ownerId")

        return try {
            val contentValues = ContentValues().apply {
                put(MyDatabaseHelper.OWNED_POKEMON_NAME, name)
                put(MyDatabaseHelper.OWNED_POKEMON_NICKNAME, nickname)
                put(MyDatabaseHelper.OWNED_POKEMON_CAUGHT_LOCATION, caughtLocation)
                put(MyDatabaseHelper.OWNED_POKEMON_SAVED_LOCATION, savedLocation)
                put(MyDatabaseHelper.OWNED_POKEMON_URL, url)
                put(MyDatabaseHelper.OWNED_POKEMON_SPRITE, sprite)
                put(MyDatabaseHelper.OWNED_POKEMON_RUN_ID, runId)
                put(MyDatabaseHelper.OWNED_POKEMON_OWNER_ID, ownerId)
            }
            val pokemonId = db.insert(MyDatabaseHelper.OWNED_POKEMON_TABLE, null, contentValues)
            Log.d("DatabaseLog", "Owned Pokémon entry inserted with ID: $pokemonId")

            if (pokemonId != -1L) {
                when (savedLocation.lowercase()) {
                    "team" -> insertIntoTeam(runId, pokemonId)
                    "box" -> insertIntoBox(runId, pokemonId)
                    "daycare" -> insertIntoDaycare(runId, pokemonId)
                    "grave" -> insertIntoGrave(runId, pokemonId)
                    else -> Log.e("DatabaseLog", "Invalid savedLocation: $savedLocation")
                }
            }

            pokemonId
        } catch (e: Exception) {
            Log.e("DatabaseLog", "Error inserting owned Pokémon entry", e)
            -1
        } finally {
            db.close()
        }
    }

    private fun insertIntoTeam(runId: Int, pokemonId: Long) {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        try {
            val contentValues = ContentValues().apply {
                put(MyDatabaseHelper.TEAM_RUN_ID, runId)
                put(MyDatabaseHelper.TEAM_OWNED_POKEMON_ID, pokemonId)
            }
            db.insert(MyDatabaseHelper.TEAM_TABLE, null, contentValues)
            Log.d("DatabaseLog", "Inserted Pokémon into Team with ID: $pokemonId")
        } finally {
            db.close()
        }
    }

    private fun insertIntoBox(runId: Int, pokemonId: Long) {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        try {
            val contentValues = ContentValues().apply {
                put(MyDatabaseHelper.BOX_RUN_ID, runId)
                put(MyDatabaseHelper.BOX_OWNED_POKEMON_ID, pokemonId)
            }
            db.insert(MyDatabaseHelper.BOX_TABLE, null, contentValues)
            Log.d("DatabaseLog", "Inserted Pokémon into Box with ID: $pokemonId")
        } finally {
            db.close()
        }
    }

    private fun insertIntoDaycare(runId: Int, pokemonId: Long) {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        try {
            val contentValues = ContentValues().apply {
                put(MyDatabaseHelper.DAYCARE_RUN_ID, runId)
                put(MyDatabaseHelper.DAYCARE_OWNED_POKEMON_ID, pokemonId)
            }
            db.insert(MyDatabaseHelper.DAYCARE_TABLE, null, contentValues)
            Log.d("DatabaseLog", "Inserted Pokémon into Daycare with ID: $pokemonId")
        } finally {
            db.close()
        }
    }

    private fun insertIntoGrave(runId: Int, pokemonId: Long) {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        try {
            val contentValues = ContentValues().apply {
                put(MyDatabaseHelper.GRAVE_RUN_ID, runId)
                put(MyDatabaseHelper.GRAVE_OWNED_POKEMON_ID, pokemonId)
            }
            db.insert(MyDatabaseHelper.GRAVE_TABLE, null, contentValues)
            Log.d("DatabaseLog", "Inserted Pokémon into Grave with ID: $pokemonId")
        } finally {
            db.close()
        }
    }

    fun insertTimelineLogEntry(
        eventName: String,
        location: String,
        time: String,
        notes: String,
        displayTeam: Int,
        runId: Int,
        team: List<OwnedPokemon>
    ): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase

        //converting TEAM from List to JSON String
        val teamJSONArray = JSONArray()
        team.forEach { ownedPokemon ->
            val pokemonJson = JSONObject().apply {
                put("ownedPokemonId", ownedPokemon.ownedPokemonId)
                put("pokemonName", ownedPokemon.pokemon.name)
                put("pokemonUrl", ownedPokemon.pokemon.url)
                put("pokemonSprite", ownedPokemon.pokemon.sprite)
                put("name", ownedPokemon.name)
                put("nickname", ownedPokemon.nickname)
                put("ownerId", ownedPokemon.owner.id)
                put("ownerName", ownedPokemon.owner.name)
                put("ownerImage", ownedPokemon.owner.image)
                put("caughtLocation", ownedPokemon.caughtLocation)
                put("savedLocation", ownedPokemon.savedLocation)
                put("url", ownedPokemon.url)
                put("sprite", ownedPokemon.sprite)
            }
            teamJSONArray.put(pokemonJson)
        }
        val contentValues = ContentValues().apply {
            put(MyDatabaseHelper.TIMELINE_LOG_EVENT_NAME, eventName)
            put(MyDatabaseHelper.TIMELINE_LOG_LOCATION, location)
            put(MyDatabaseHelper.TIMELINE_LOG_TIME, time)
            put(MyDatabaseHelper.TIMELINE_LOG_NOTES, notes)
            put(MyDatabaseHelper.TIMELINE_LOG_DISPLAY_TEAM, displayTeam)
            put(MyDatabaseHelper.TIMELINE_LOG_RUN_ID, runId)
            put(MyDatabaseHelper.TIMELINE_LOG_TEAM, teamJSONArray.toString())
        }
        val logId = db.insert(MyDatabaseHelper.TIMELINE_LOG_TABLE, null, contentValues)
        db.close()
        listAllTimelineLogs()
        return logId
    }

    //TO BE DELETED
    fun listAllTimelineLogs() {
        val db: SQLiteDatabase = dbHelper.readableDatabase

        val projection = arrayOf(
            MyDatabaseHelper.TIMELINE_LOG_ID,
            MyDatabaseHelper.TIMELINE_LOG_EVENT_NAME,
            MyDatabaseHelper.TIMELINE_LOG_LOCATION,
            MyDatabaseHelper.TIMELINE_LOG_TIME,
            MyDatabaseHelper.TIMELINE_LOG_NOTES,
            MyDatabaseHelper.TIMELINE_LOG_DISPLAY_TEAM,
            MyDatabaseHelper.TIMELINE_LOG_RUN_ID,
        )

        val cursor = db.query(
            MyDatabaseHelper.TIMELINE_LOG_TABLE,  // Table name
            null,  // Select all columns
            null,
            null,
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.TIMELINE_LOG_ID))
                val eventName = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.TIMELINE_LOG_EVENT_NAME))
                val location = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.TIMELINE_LOG_LOCATION))
                val time = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.TIMELINE_LOG_TIME))
                val notes = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.TIMELINE_LOG_NOTES))
                val displayTeam = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.TIMELINE_LOG_DISPLAY_TEAM)) == 1
                val runId = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.TIMELINE_LOG_RUN_ID))
                val team = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.TIMELINE_LOG_TEAM))

                Log.d("TimelineLog", """
                ID: $id
                Event Name: $eventName
                Location: $location
                Time: $time
                Notes: $notes
                Display Team: $displayTeam
                Run ID: $runId
                Team: $team
            """.trimIndent())
            } while (cursor.moveToNext())
        } else {
            Log.d("TimelineLog", "No timeline logs found in the database.")
        }

        cursor.close()
        db.close()
    }

    fun getRunById(runId: Int): Run? {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        var run: Run? = null

        try {
            val cursor = db.query(
                MyDatabaseHelper.RUNS_TABLE,
                null,
                "${MyDatabaseHelper.RUN_ID} = ?",
                arrayOf(runId.toString()),
                null, null, null
            )

            if (cursor.moveToFirst()) {
                val idIndex = cursor.getColumnIndex(MyDatabaseHelper.RUN_ID)
                val nameIndex = cursor.getColumnIndex(MyDatabaseHelper.RUN_NAME)
                val gameTitleIndex = cursor.getColumnIndex(MyDatabaseHelper.GAME_TITLE)
                val updatedTimeIndex = cursor.getColumnIndex(MyDatabaseHelper.UPDATED_TIME)

                if (idIndex >= 0 && nameIndex >= 0 && gameTitleIndex >= 0 && updatedTimeIndex >= 0) {
                    val id = cursor.getInt(idIndex)
                    val name = cursor.getString(nameIndex)
                    val gameTitle = cursor.getString(gameTitleIndex)
                    val updatedTime = cursor.getString(updatedTimeIndex)

                    run = Run(
                        runId = id,
                        runName = name,
                        gameTitle = gameTitle,
                        players = getPlayersByRunId(id),
                        team = getTeamByRunId(id),
                        box = getBoxByRunId(id),
                        daycare = getDaycareByRunId(id),
                        grave = getGraveByRunId(id),
                        updatedTime = updatedTime,
                        logs = getTimelineLogsByRun(id)
                    )
                }
                cursor.close()
            }
        } catch (e: Exception) {
            Log.e("DatabaseLog", "Error retrieving run by ID", e)
        } finally {
            db.close()
        }
        return run
    }

    fun getPlayersByRunId(runId: Int): List<Player> {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor = db.query(
            MyDatabaseHelper.PLAYERS_TABLE,
            null,
            "${MyDatabaseHelper.PLAYER_RUN_ID} = ?",
            arrayOf(runId.toString()),
            null, null, null
        )

        val players = mutableListOf<Player>()
        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(MyDatabaseHelper.PLAYER_ID)
            val nameIndex = cursor.getColumnIndex(MyDatabaseHelper.PLAYER_NAME)
            val imageIndex = cursor.getColumnIndex(MyDatabaseHelper.PLAYER_IMAGE)

            if (idIndex >= 0 && nameIndex >= 0 && imageIndex >= 0) {
                do {
                    val id = cursor.getInt(idIndex)
                    val name = cursor.getString(nameIndex)
                    val image = cursor.getString(imageIndex)

                    players.add(Player(id, name, image))
                } while (cursor.moveToNext())
            } else {
                // Log the missing columns for debugging
                Log.e("DatabaseLog", "Missing columns in Players table: idIndex=$idIndex, nameIndex=$nameIndex, imageIndex=$imageIndex")
            }
        }
        cursor.close()
        db.close()
        return players
    }


    fun getTeamByRunId(runId: Int): List<OwnedPokemon> {
        return getOwnedPokemonByRunId(
            runId,
            MyDatabaseHelper.TEAM_TABLE,
            MyDatabaseHelper.TEAM_RUN_ID,
            MyDatabaseHelper.TEAM_OWNED_POKEMON_ID
        )
    }

    fun getBoxByRunId(runId: Int): List<OwnedPokemon> {
        return getOwnedPokemonByRunId(
            runId,
            MyDatabaseHelper.BOX_TABLE,
            MyDatabaseHelper.BOX_RUN_ID,
            MyDatabaseHelper.BOX_OWNED_POKEMON_ID
        )
    }

    fun getDaycareByRunId(runId: Int): List<OwnedPokemon> {
        return getOwnedPokemonByRunId(
            runId,
            MyDatabaseHelper.DAYCARE_TABLE,
            MyDatabaseHelper.DAYCARE_RUN_ID,
            MyDatabaseHelper.DAYCARE_OWNED_POKEMON_ID
        )
    }

    fun getGraveByRunId(runId: Int): List<OwnedPokemon> {
        return getOwnedPokemonByRunId(
            runId,
            MyDatabaseHelper.GRAVE_TABLE,
            MyDatabaseHelper.GRAVE_RUN_ID,
            MyDatabaseHelper.GRAVE_OWNED_POKEMON_ID
        )
    }

    private fun getOwnedPokemonByRunId(
        runId: Int,
        table: String,
        runIdColumn: String,
        pokemonIdColumn: String
    ): List<OwnedPokemon> {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val query = """
        SELECT op.*, p.${MyDatabaseHelper.PLAYER_ID}, p.${MyDatabaseHelper.PLAYER_NAME}, p.${MyDatabaseHelper.PLAYER_IMAGE} 
        FROM $table t 
        JOIN ${MyDatabaseHelper.OWNED_POKEMON_TABLE} op ON t.$pokemonIdColumn = op.${MyDatabaseHelper.OWNED_POKEMON_ID}
        JOIN ${MyDatabaseHelper.PLAYERS_TABLE} p ON op.${MyDatabaseHelper.OWNED_POKEMON_OWNER_ID} = p.${MyDatabaseHelper.PLAYER_ID}
        WHERE t.$runIdColumn = ?
    """.trimIndent()
        val cursor = db.rawQuery(query, arrayOf(runId.toString()))

        val ownedPokemon = mutableListOf<OwnedPokemon>()
        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_ID)
            val nameIndex = cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_NAME)
            val nicknameIndex = cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_NICKNAME)
            val playerIdIndex = cursor.getColumnIndex(MyDatabaseHelper.PLAYER_ID)
            val playerNameIndex = cursor.getColumnIndex(MyDatabaseHelper.PLAYER_NAME)
            val playerImageIndex = cursor.getColumnIndex(MyDatabaseHelper.PLAYER_IMAGE)
            val caughtLocationIndex = cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_CAUGHT_LOCATION)
            val savedLocationIndex = cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_SAVED_LOCATION)
            val urlIndex = cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_URL)
            val spriteIndex = cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_SPRITE)

            if (idIndex >= 0 && nameIndex >= 0 && nicknameIndex >= 0 && playerIdIndex >= 0 && playerNameIndex >= 0 && playerImageIndex >= 0 && caughtLocationIndex >= 0 && savedLocationIndex >= 0 && urlIndex >= 0 && spriteIndex >= 0) {
                do {
                    val id = cursor.getInt(idIndex)
                    val name = cursor.getString(nameIndex)
                    val nickname = cursor.getString(nicknameIndex)
                    val caughtLocation = cursor.getString(caughtLocationIndex)
                    val savedLocation = cursor.getString(savedLocationIndex)
                    val url = cursor.getString(urlIndex)
                    val sprite = cursor.getString(spriteIndex)
                    val playerId = cursor.getInt(playerIdIndex)
                    val playerName = cursor.getString(playerNameIndex)
                    val playerImage = cursor.getString(playerImageIndex)

                    val owner = Player(playerId, playerName, playerImage)

                    ownedPokemon.add(
                        OwnedPokemon(
                            id,
                            Pokemon(name, url, sprite),
                            name,
                            nickname,
                            owner,
                            caughtLocation,
                            savedLocation,
                            url,
                            sprite
                        )
                    )
                } while (cursor.moveToNext())
            } else {
                // Log the missing columns for debugging
                Log.e("DatabaseLog", "Missing columns in OwnedPokemon table: idIndex=$idIndex, nicknameIndex=$nicknameIndex, playerIdIndex=$playerIdIndex, playerNameIndex=$playerNameIndex, playerImageIndex=$playerImageIndex, caughtLocationIndex=$caughtLocationIndex, savedLocationIndex=$savedLocationIndex, urlIndex=$urlIndex, spriteIndex=$spriteIndex")
            }
        }
        cursor.close()
        db.close()
        return ownedPokemon
    }

    fun getAllOwnedPokemonByRunId(runId: Int): List<OwnedPokemon> {
        val teamList = getTeamByRunId(runId)
        val boxList = getBoxByRunId(runId)
        val daycareList = getDaycareByRunId(runId)
        val graveList = getGraveByRunId(runId)

        val allOwnedPokemon = mutableListOf<OwnedPokemon>()
        allOwnedPokemon.addAll(teamList)
        allOwnedPokemon.addAll(boxList)
        allOwnedPokemon.addAll(daycareList)
        allOwnedPokemon.addAll(graveList)

        return allOwnedPokemon
    }

    fun getTimelineLogsByRun(runId: Int): List<TimelineLog> {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor = db.query(
            MyDatabaseHelper.TIMELINE_LOG_TABLE,
            null,
            "${MyDatabaseHelper.TIMELINE_LOG_RUN_ID} = ?",
            arrayOf(runId.toString()),
            null, null, null
        )

        val logs = mutableListOf<TimelineLog>()
        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(MyDatabaseHelper.TIMELINE_LOG_ID)
            val eventNameIndex = cursor.getColumnIndex(MyDatabaseHelper.TIMELINE_LOG_EVENT_NAME)
            val locationIndex = cursor.getColumnIndex(MyDatabaseHelper.TIMELINE_LOG_LOCATION)
            val timeIndex = cursor.getColumnIndex(MyDatabaseHelper.TIMELINE_LOG_TIME)
            val notesIndex = cursor.getColumnIndex(MyDatabaseHelper.TIMELINE_LOG_NOTES)
            val displayTeamIndex = cursor.getColumnIndex(MyDatabaseHelper.TIMELINE_LOG_DISPLAY_TEAM)
            val teamIndex = cursor.getColumnIndex(MyDatabaseHelper.TIMELINE_LOG_TEAM)

            if (idIndex >= 0 && eventNameIndex >= 0 && locationIndex >= 0 && timeIndex >= 0 && notesIndex >= 0 && displayTeamIndex >= 0 && teamIndex >= 0) {
                do {
                    val id = cursor.getInt(idIndex)
                    val eventName = cursor.getString(eventNameIndex)
                    val location = cursor.getString(locationIndex)
                    val time = cursor.getString(timeIndex)
                    val notes = cursor.getString(notesIndex)
                    val displayTeam = cursor.getInt(displayTeamIndex) > 0
                    val teamJsonString = cursor.getString(teamIndex)

                    val team = parseJsonTeam(teamJsonString)
                    val deaths = getDeathsByTimelineLogId(id)
                    val captures = getCapturesByTimelineLogId(id)

                    logs.add(TimelineLog(id, eventName, location, time, team, deaths, captures, notes, displayTeam))
                } while (cursor.moveToNext())
            } else {
                // Log the missing columns for debugging
                Log.e("DatabaseLog", "Missing columns in TimelineLog table: idIndex=$idIndex, eventNameIndex=$eventNameIndex, locationIndex=$locationIndex, timeIndex=$timeIndex, notesIndex=$notesIndex, displayTeamIndex=$displayTeamIndex, teamIndex=$teamIndex")
            }
        }
        cursor.close()
        db.close()
        return logs
    }

    // Parse JSON team from the log
    private fun parseJsonTeam(jsonString: String): List<OwnedPokemon> {
        val team = mutableListOf<OwnedPokemon>()
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val pokemonJson = jsonArray.getJSONObject(i)
            val ownedPokemon = OwnedPokemon(
                ownedPokemonId = pokemonJson.getInt("ownedPokemonId"),
                pokemon = Pokemon(
                    name = pokemonJson.getString("pokemonName"),
                    url = pokemonJson.getString("pokemonUrl"),
                    sprite = pokemonJson.getString("pokemonSprite")
                ),
                name = pokemonJson.getString("name"),
                nickname = pokemonJson.getString("nickname"),
                owner = Player(
                    id = pokemonJson.getInt("ownerId"),
                    name = pokemonJson.getString("ownerName"),
                    image = pokemonJson.getString("ownerImage")
                ),
                caughtLocation = pokemonJson.getString("caughtLocation"),
                savedLocation = pokemonJson.getString("savedLocation"),
                url = pokemonJson.getString("url"),
                sprite = pokemonJson.getString("sprite")
            )
            team.add(ownedPokemon)
        }
        return team
    }

    fun getDeathsByTimelineLogId(timelineLogId: Int): List<OwnedPokemon> {
        return getOwnedPokemonByTimelineLogId(timelineLogId, MyDatabaseHelper.DEATHS_TABLE, MyDatabaseHelper.DEATHS_TIMELINE_LOG_ID, MyDatabaseHelper.DEATHS_OWNED_POKEMON_ID)
    }

    fun getCapturesByTimelineLogId(timelineLogId: Int): List<OwnedPokemon> {
        return getOwnedPokemonByTimelineLogId(timelineLogId, MyDatabaseHelper.CAPTURES_TABLE, MyDatabaseHelper.CAPTURES_TIMELINE_LOG_ID, MyDatabaseHelper.CAPTURES_OWNED_POKEMON_ID)
    }

    private fun getOwnedPokemonByTimelineLogId(timelineLogId: Int, table: String, logIdColumn: String, pokemonIdColumn: String): List<OwnedPokemon> {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val query = """
        SELECT op.*, p.${MyDatabaseHelper.PLAYER_ID}, p.${MyDatabaseHelper.PLAYER_NAME}, p.${MyDatabaseHelper.PLAYER_IMAGE} 
        FROM $table t 
        JOIN ${MyDatabaseHelper.OWNED_POKEMON_TABLE} op ON t.$pokemonIdColumn = op.${MyDatabaseHelper.OWNED_POKEMON_ID}
        JOIN ${MyDatabaseHelper.PLAYERS_TABLE} p ON op.${MyDatabaseHelper.OWNED_POKEMON_OWNER_ID} = p.${MyDatabaseHelper.PLAYER_ID}
        WHERE t.$logIdColumn = ?
    """.trimIndent()
        val cursor = db.rawQuery(query, arrayOf(timelineLogId.toString()))

        val ownedPokemon = mutableListOf<OwnedPokemon>()
        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_ID)
            val nameIndex = cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_NAME)
            val nicknameIndex = cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_NICKNAME)
            val playerIdIndex = cursor.getColumnIndex(MyDatabaseHelper.PLAYER_ID)
            val playerNameIndex = cursor.getColumnIndex(MyDatabaseHelper.PLAYER_NAME)
            val playerImageIndex = cursor.getColumnIndex(MyDatabaseHelper.PLAYER_IMAGE)
            val caughtLocationIndex = cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_CAUGHT_LOCATION)
            val savedLocationIndex = cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_SAVED_LOCATION)
            val urlIndex = cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_URL)
            val spriteIndex = cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_SPRITE)

            if (idIndex >= 0 && nameIndex >= 0 && nicknameIndex >= 0 && playerIdIndex >= 0 && playerNameIndex >= 0 && playerImageIndex >= 0 && caughtLocationIndex >= 0 && savedLocationIndex >= 0 && urlIndex >= 0 && spriteIndex >= 0) {
                do {
                    val id = cursor.getInt(idIndex)
                    val name = cursor.getString(nameIndex)
                    val nickname = cursor.getString(nicknameIndex)
                    val caughtLocation = cursor.getString(caughtLocationIndex)
                    val savedLocation = cursor.getString(savedLocationIndex)
                    val url = cursor.getString(urlIndex)
                    val sprite = cursor.getString(spriteIndex)
                    val playerId = cursor.getInt(playerIdIndex)
                    val playerName = cursor.getString(playerNameIndex)
                    val playerImage = cursor.getString(playerImageIndex)

                    val owner = Player(playerId, playerName, playerImage)

                    ownedPokemon.add(
                        OwnedPokemon(
                            id,
                            Pokemon(name, url, sprite),
                            name,
                            nickname,
                            owner,
                            caughtLocation,
                            savedLocation,
                            url,
                            sprite
                        )
                    )
                } while (cursor.moveToNext())
            } else {
                // Log the missing columns for debugging
                Log.e("DatabaseLog", "Missing columns in OwnedPokemon table: idIndex=$idIndex, nicknameIndex=$nicknameIndex, playerIdIndex=$playerIdIndex, playerNameIndex=$playerNameIndex, playerImageIndex=$playerImageIndex, caughtLocationIndex=$caughtLocationIndex, savedLocationIndex=$savedLocationIndex, urlIndex=$urlIndex, spriteIndex=$spriteIndex")
            }
        }
        cursor.close()
        db.close()
        return ownedPokemon
    }


    fun getAllRuns(): Runs {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor = db.query(
            MyDatabaseHelper.RUNS_TABLE,
            null,
            null,
            null,
            null, null, null
        )

        val runs = ArrayList<Run>()
        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(MyDatabaseHelper.RUN_ID)
            val nameIndex = cursor.getColumnIndex(MyDatabaseHelper.RUN_NAME)
            val gameTitleIndex = cursor.getColumnIndex(MyDatabaseHelper.GAME_TITLE)
            val updatedTimeIndex = cursor.getColumnIndex(MyDatabaseHelper.UPDATED_TIME)

            if (idIndex >= 0 && nameIndex >= 0 && gameTitleIndex >= 0 && updatedTimeIndex >= 0) {
                do {
                    val id = cursor.getInt(idIndex)
                    val name = cursor.getString(nameIndex)
                    val gameTitle = cursor.getString(gameTitleIndex)
                    val updatedTime = cursor.getString(updatedTimeIndex)
                    val players = getPlayersByRunId(id)
                    val team = getTeamByRunId(id)
                    val box = getBoxByRunId(id)
                    val daycare = getDaycareByRunId(id)
                    val grave = getGraveByRunId(id)
                    val logs = getTimelineLogsByRun(id)

                    runs.add(
                        Run(
                            runId = id,
                            runName = name,
                            gameTitle = gameTitle,
                            players = players,
                            team = team,
                            box = box,
                            daycare = daycare,
                            grave = grave,
                            updatedTime = updatedTime,
                            logs = logs
                        )
                    )
                } while (cursor.moveToNext())
            } else {
                // Log the missing columns for debugging
                Log.e("DatabaseLog", "Missing columns in Runs table: idIndex=$idIndex, nameIndex=$nameIndex, gameTitleIndex=$gameTitleIndex, updatedTimeIndex=$updatedTimeIndex")
            }
        }
        cursor.close()
        db.close()
        return Runs(runs)
    }

    fun updateRunDetails(run: Run): Boolean {
        val db = dbHelper.writableDatabase
        return try {
            val contentValues = ContentValues().apply {
                put(MyDatabaseHelper.RUN_NAME, run.runName)
                put(MyDatabaseHelper.GAME_TITLE, run.gameTitle)
                put(MyDatabaseHelper.UPDATED_TIME, run.updatedTime)
            }
            val rowsAffected = db.update(
                MyDatabaseHelper.RUNS_TABLE,
                contentValues,
                "${MyDatabaseHelper.RUN_ID} = ?",
                arrayOf(run.runId.toString())
            )
            Log.d("DatabaseLog", "Run details updated for run ID: ${run.runId}")
            rowsAffected > 0
        } catch (e: Exception) {
            Log.e("DatabaseLog", "Error updating run details", e)
            false
        } finally {
            db.close()
        }
    }

    @SuppressLint("Range")
    private fun logAllPokemonEntries() {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor = db.query(
            MyDatabaseHelper.OWNED_POKEMON_TABLE,
            null, // null means select all columns
            null, // no WHERE clause (select all rows)
            null, // no selection args
            null, // no GROUP BY
            null, // no HAVING
            null // no ORDER BY
        )

        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    val id = cursor.getLong(cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_ID))
                    val nickname = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_NICKNAME))
                    val ownerId = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_OWNER_ID))
                    val caughtLocation = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_CAUGHT_LOCATION))
                    val savedLocation = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_SAVED_LOCATION))
                    val url = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_URL))
                    val sprite = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_SPRITE))
                    val runId = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.OWNED_POKEMON_RUN_ID))

                    // Log each entry
                    Log.d("DatabaseLog", "Owned Pokemon - ID: $id, Nickname: $nickname, Owner: $ownerId, CaughtLocation: $caughtLocation, SavedLocation: $savedLocation, URL: $url, Sprite: $sprite, RunID: $runId")
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.e("DatabaseLog", "Error reading from database", e)
        } finally {
            cursor?.close()
            db.close()
        }
    }
}
