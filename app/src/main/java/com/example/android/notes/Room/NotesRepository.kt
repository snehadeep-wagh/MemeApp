package com.example.android.notes.Room

import androidx.lifecycle.LiveData

class NotesRepository(private val Dao: NotesDao) {

    suspend fun insert(Notes: NotesEntity)
    {
        Dao.insert(Notes)
    }

    suspend fun delete(Notes: NotesEntity)
    {
        Dao.delete(Notes)
    }

    fun getAllNotes(): LiveData<List<NotesEntity>>
    {
        return Dao.showall()
    }
}