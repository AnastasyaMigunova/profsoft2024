package com.together.domain.repository

import com.together.domain.models.CommunityNote
import com.together.domain.models.LocalNote

interface NoteRepository {
    suspend fun getCommunityNotes() : List<CommunityNote>
    suspend fun getLastLocalNote(): LocalNote?
    suspend fun getLocalNotes(): List<LocalNote>
    suspend fun postLocalNote(localNote: LocalNote): Boolean
    suspend fun getFavLocalNotes(): List<LocalNote>
}