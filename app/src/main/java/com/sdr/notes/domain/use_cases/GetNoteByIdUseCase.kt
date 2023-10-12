package com.sdr.notes.domain.use_cases

import com.sdr.notes.domain.repository.Repository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(id: Long) = repository.getNoteByid(id)
}