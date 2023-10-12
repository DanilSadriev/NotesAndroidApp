package com.sdr.notes.domain.repository

import com.sdr.notes.data.local.model.Note
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getAllNotes(): Flow<List<Note>>
    fun getNoteByid(id:Long):Flow<Note>
    suspend fun insert(note: Note)
    suspend fun update(note: Note)
    suspend fun delete(id: Long)
    fun getBookMarkedNotes():Flow<List<Note>>
}