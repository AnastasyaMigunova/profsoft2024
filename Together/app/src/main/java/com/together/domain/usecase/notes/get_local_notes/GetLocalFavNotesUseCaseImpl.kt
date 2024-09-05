package com.together.domain.usecase.notes.get_local_notes

import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.NoteRepository
import com.together.ui.models.LocalNoteVO
import javax.inject.Inject

class GetLocalFavNotesUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository,
    private val domainToUiMapper: DomainToUiMapper
) : GetLocalFavNotesUseCase {
    override suspend fun getLocalFavNotes(): List<LocalNoteVO> {
        return try {
            val localFavNotes = noteRepository.getFavLocalNotes()
            val localFavNotesVO =
                localFavNotes.map { note -> domainToUiMapper.run { note.toViewObject() } }
            localFavNotesVO
        } catch (e: Exception) {
            throw e
        }
    }
}