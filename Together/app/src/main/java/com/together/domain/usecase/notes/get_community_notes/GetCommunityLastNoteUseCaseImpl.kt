package com.together.domain.usecase.notes.get_community_notes

import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.NoteRepository
import com.together.ui.models.CommunityNoteVO
import javax.inject.Inject

class GetCommunityLastNoteUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository,
    private val domainToUiMapper: DomainToUiMapper
) : GetCommunityLastNoteUseCase {
    override suspend fun getLastCommunityNote(): CommunityNoteVO {
        return try {
            val communityNotes = noteRepository.getCommunityNotes()

            if (communityNotes.isNotEmpty()) {
                val latestNote = communityNotes.maxByOrNull { it.date }
                latestNote?.let { domainToUiMapper.run { it.toViewObject() } }
                    ?: throw NoSuchElementException("Нет доступных записей")
            } else {
                throw NoSuchElementException("Нет доступных записей")
            }
        } catch (e: Exception) {
            throw e
        }
    }
}