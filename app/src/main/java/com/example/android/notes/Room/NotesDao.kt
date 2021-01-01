package com.example.android.notes.Room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: NotesEntity)

    @Delete
    suspend fun delete(note: NotesEntity)

    @Query("SELECT * FROM NotesTable Order By id ASC")
    fun showall(): LiveData<List<NotesEntity>>

}