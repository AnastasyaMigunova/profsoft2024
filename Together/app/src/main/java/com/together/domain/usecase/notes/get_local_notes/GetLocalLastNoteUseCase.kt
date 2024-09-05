package com.together.domain.usecase.notes.get_local_notes

import com.together.ui.models.LocalNoteVO

interface GetLocalLastNoteUseCase {
    suspend fun getLastLocalNote() : LocalNoteVO?
}