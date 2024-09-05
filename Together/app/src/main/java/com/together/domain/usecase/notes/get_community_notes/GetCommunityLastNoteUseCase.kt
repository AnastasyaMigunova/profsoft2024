package com.together.domain.usecase.notes.get_community_notes

import com.together.ui.models.CommunityNoteVO

interface GetCommunityLastNoteUseCase {
    suspend fun getLastCommunityNote() : CommunityNoteVO
}