package com.together.domain.usecase.notes.get_local_notes

import com.together.ui.models.CommunityNoteVO
import com.together.ui.models.LocalNoteVO

interface GetLocalNotesUseCase {
    suspend fun getLocalNotes() : List<LocalNoteVO>
}