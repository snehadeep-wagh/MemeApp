package com.example.android.notes.MVVM

import com.example.android.notes.Room.NotesRoomDataBase
import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.notes.Room.NotesEntity
import com.example.android.notes.Room.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    val getAllNotes: LiveData<List<NotesEntity>>
    private val repo: NotesRepository

    init {
        val db: NotesRoomDataBase? = NotesRoomDataBase.getDatabase(application)
        val dao = db!!.getRoomDao()
        repo = NotesRepository(dao)

        getAllNotes = repo.getAllNotes()
    }

    fun deleteNote(notesEntity: NotesEntity) = viewModelScope.launch(Dispatchers.IO){
        repo.delete(notesEntity)
    }

    fun insertNote(notesEntity: NotesEntity) = viewModelScope.launch(Dispatchers.IO){
        repo.insert(notesEntity)
    }


}
