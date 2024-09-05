package com.together.domain.usecase.notes.get_local_notes

import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.NoteRepository
import com.together.ui.models.LocalNoteVO
import javax.inject.Inject

class GetLocalLastNoteUseCaseImpl  @Inject constructor(
    private val noteRepository: NoteRepository,
    private val domainToUiMapper: DomainToUiMapper
) : GetLocalLastNoteUseCase {
    override suspend fun getLastLocalNote(): LocalNoteVO? {
        return try {
            val lastLocalNote = noteRepository.getLastLocalNote()
            domainToUiMapper.run { lastLocalNote?.toViewObject() }
        } catch (e: Exception) {
            throw e
        }
    }
}