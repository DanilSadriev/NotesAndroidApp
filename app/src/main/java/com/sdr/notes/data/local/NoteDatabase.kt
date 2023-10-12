package com.sdr.notes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sdr.notes.data.local.converters.DateConverter
import com.sdr.notes.data.local.model.Note

@TypeConverters(value = [DateConverter::class])
@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao:NoteDao
}