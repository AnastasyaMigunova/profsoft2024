package com.together.domain.usecase.notes.get_local_notes

import com.together.ui.models.LocalNoteVO

interface GetLocalFavNotesUseCase {
    suspend fun getLocalFavNotes() : List<LocalNoteVO>
}