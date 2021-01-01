package com.example.android.notes.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NotesTable")
class NotesEntity(
    @ColumnInfo(name = "MyText") var notesText: String) {
    @PrimaryKey(autoGenerate = true) var id = 0
}
