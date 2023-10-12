package com.sdr.notes.domain.use_cases

import com.sdr.notes.domain.repository.Repository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Long) = repository.delete(id)
}