package com.together.domain.usecase.notes.get_local_notes

import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.NoteRepository
import com.together.ui.models.LocalNoteVO
import javax.inject.Inject

class GetLocalNotesUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository,
    private val domainToUiMapper: DomainToUiMapper
) : GetLocalNotesUseCase {
    override suspend fun getLocalNotes(): List<LocalNoteVO> {
        return try {
            val localNotes = noteRepository.getLocalNotes()
            val localNotesVO =
                localNotes.map { note -> domainToUiMapper.run { note.toViewObject() } }
            localNotesVO
        } catch (e: Exception) {
            throw e
        }
    }
}