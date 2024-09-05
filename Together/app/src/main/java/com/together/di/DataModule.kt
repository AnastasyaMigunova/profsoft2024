package com.together.di

import android.content.Context
import com.together.data.api.ApiService
import com.together.data.mapper.DataToDomainMapper
import com.together.data.mapper.DomainToDataMapper
import com.together.data.repository.AuthRepositoryImpl
import com.together.data.repository.CourseRepositoryImpl
import com.together.data.repository.NoteRepositoryImpl
import com.together.data.repository.ProfileRepositoryImpl
import com.together.data.repository.RegisterRepositoryImpl
import com.together.data.storage.preferences.PreferencesManager
import com.together.data.storage.preferences.TokenManager
import com.together.data.storage.room.dao.LocalNoteDao
import com.together.domain.repository.AuthRepository
import com.together.domain.repository.CourseRepository
import com.together.domain.repository.NoteRepository
import com.together.domain.repository.ProfileRepository
import com.together.domain.repository.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideAuthRepository(
        apiService: ApiService,
        tokenManager: TokenManager
    ): AuthRepository {
        return AuthRepositoryImpl(apiService, tokenManager)
    }

    @Provides
    fun provideRegisterRepository(
        apiService: ApiService,
        tokenManager: TokenManager
    ): RegisterRepository {
        return RegisterRepositoryImpl(apiService, tokenManager)
    }

    @Provides
    fun provideCourseRepository(
        apiService: ApiService,
        dataToDomainMapper: DataToDomainMapper,
        domainToDataMapper: DomainToDataMapper
    ): CourseRepository {
        return CourseRepositoryImpl(apiService, dataToDomainMapper, domainToDataMapper)
    }

    @Provides
    fun provideGetNotesRepository(
        apiService: ApiService,
        noteDao: LocalNoteDao,
        dataToDomainMapper: DataToDomainMapper,
        domainToDataMapper: DomainToDataMapper
    ): NoteRepository {
        return NoteRepositoryImpl(apiService, noteDao, dataToDomainMapper, domainToDataMapper)
    }

    @Provides
    fun provideGetProfileRepository(
        apiService: ApiService,
        dataToDomainMapper: DataToDomainMapper
    ): ProfileRepository {
        return ProfileRepositoryImpl(apiService, dataToDomainMapper)
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): PreferencesManager {
        return PreferencesManager(context)
    }

    @Provides
    fun provideTokenManager(preferencesManager: PreferencesManager): TokenManager {
        return TokenManager(preferencesManager)
    }
}