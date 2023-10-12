package com.sdr.notes.domain.use_cases

import com.sdr.notes.data.local.model.Note
import com.sdr.notes.domain.repository.Repository
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(note: Note){
        repository.update(note)
    }
}