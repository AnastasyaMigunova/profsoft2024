package com.together.data.repository

import com.together.data.api.ApiService
import com.together.data.mapper.DataToDomainMapper
import com.together.data.mapper.DomainToDataMapper
import com.together.data.storage.room.dao.LocalNoteDao
import com.together.domain.models.CommunityNote
import com.together.domain.models.LocalNote
import com.together.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localNoteDao: LocalNoteDao,
    private val dataToDomainMapper: DataToDomainMapper,
    private val domainToDataMapper: DomainToDataMapper
) : NoteRepository {
    override suspend fun getCommunityNotes(): List<CommunityNote> {
        return try {
            val response = apiService.getNotes()
            dataToDomainMapper.run { response.toDomain() }
        } catch (e: Exception) {
            throw Exception("Get community notes error: ${e.message}", e)
        }
    }

    override suspend fun getLastLocalNote(): LocalNote? {
        return try {
            val response = localNoteDao.getLastLocalNote()
            dataToDomainMapper.run { response?.toDomain() }
        } catch (e: Exception) {
            throw Exception("Get last local note error: ${e.message}", e)
        }
    }

    override suspend fun getLocalNotes(): List<LocalNote> {
        return try {
            val localNotes = localNoteDao.getLocalNotes()
            dataToDomainMapper.run { localNotes.toDomain() }
        } catch (e: Exception) {
            throw Exception("Get local notes error: ${e.message}", e)
        }
    }

    override suspend fun postLocalNote(localNote: LocalNote): Boolean {
        return try {
            val localNoteEntity = domainToDataMapper.run { localNote.toData() }
            localNoteDao.insertNote(localNoteEntity.localNote)
            localNoteEntity.content.forEach { contentEntity ->
                localNoteDao.insertContent(contentEntity)
            }
            true
        } catch (e: Exception) {
            throw Exception("Post local note error: ${e.message}", e)
        }
    }

    override suspend fun getFavLocalNotes(): List<LocalNote> {
        return try {
            val localNotes = localNoteDao.getFavouriteLocalNotes()
            dataToDomainMapper.run { localNotes.toDomain() }
        } catch (e: Exception) {
            throw Exception("Get favourite local notes error: ${e.message}", e)
        }
    }
}