package com.sdr.notes.domain.use_cases

import com.sdr.notes.data.local.model.Note
import com.sdr.notes.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilterBookmarksNotes @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getBookMarkedNotes()
    }
}