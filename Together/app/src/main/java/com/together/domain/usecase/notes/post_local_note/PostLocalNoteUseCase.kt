package com.together.domain.usecase.notes.post_local_note

import com.together.ui.models.LocalNoteVO

interface PostLocalNoteUseCase {
    suspend fun postLocalNote(localNoteVO: LocalNoteVO) : Boolean
}