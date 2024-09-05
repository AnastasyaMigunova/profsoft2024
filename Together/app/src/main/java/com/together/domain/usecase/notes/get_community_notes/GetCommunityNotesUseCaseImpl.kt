package com.together.domain.usecase.notes.get_community_notes

import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.NoteRepository
import com.together.ui.models.CommunityNoteVO
import javax.inject.Inject

class GetCommunityNotesUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository,
    private val domainToUiMapper: DomainToUiMapper
) : GetCommunityNotesUseCase {
    override suspend fun getAllCommunityNotes(): List<CommunityNoteVO> {
        return try {
            val communityNotes = noteRepository.getCommunityNotes()
            val communityNotesVO =
                communityNotes.map { note -> domainToUiMapper.run { note.toViewObject() } }
            communityNotesVO
        } catch (e: Exception) {
            throw e
        }
    }
}