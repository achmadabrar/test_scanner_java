package com.achmadabrar.myapplication.data.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.achmadabrar.myapplication.data.database.table.NextEvent

interface NextEventDao {
    @Query("SELECT * FROM next_match_table")
    suspend fun getAllNextMatch(): List<NextEvent>?

    @Query("SELECT * FROM next_match_table WHERE `matchTitle` == :matchTitle ")
    suspend fun getUser(matchTitle: String?): NextEvent?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(listPost: List<NextEvent>)
}