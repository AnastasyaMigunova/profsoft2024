package com.together.di

import com.together.data.repository.AuthRepositoryImpl
import com.together.data.repository.CourseRepositoryImpl
import com.together.data.repository.NoteRepositoryImpl
import com.together.data.repository.ProfileRepositoryImpl
import com.together.data.repository.RegisterRepositoryImpl
import com.together.data.storage.preferences.TokenManager
import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.mapper.UiToDomainMapper
import com.together.domain.usecase.auth.AuthUseCase
import com.together.domain.usecase.auth.AuthUseCaseImpl
import com.together.domain.usecase.auth.CheckUserLoggedInUseCase
import com.together.domain.usecase.auth.CheckUserLoggedInUseCaseImpl
import com.together.domain.usecase.courses.get_course_by_id.GetCourseByIdUseCase
import com.together.domain.usecase.courses.get_course_by_id.GetCourseByIdUseCaseImpl
import com.together.domain.usecase.courses.get_courses.GetCoursesUseCase
import com.together.domain.usecase.courses.get_courses.GetCoursesUseCaseImpl
import com.together.domain.usecase.courses.get_courses.GetLastCoursesUseCase
import com.together.domain.usecase.courses.get_courses.GetLastCoursesUseCaseImpl
import com.together.domain.usecase.courses.post_course.PostCourseUseCase
import com.together.domain.usecase.courses.post_course.PostCourseUseCaseImpl
import com.together.domain.usecase.notes.get_community_notes.GetCommunityLastNoteUseCase
import com.together.domain.usecase.notes.get_community_notes.GetCommunityLastNoteUseCaseImpl
import com.together.domain.usecase.notes.get_community_notes.GetCommunityNotesUseCase
import com.together.domain.usecase.notes.get_community_notes.GetCommunityNotesUseCaseImpl
import com.together.domain.usecase.notes.get_local_notes.GetLocalFavNotesUseCase
import com.together.domain.usecase.notes.get_local_notes.GetLocalFavNotesUseCaseImpl
import com.together.domain.usecase.notes.get_local_notes.GetLocalLastNoteUseCase
import com.together.domain.usecase.notes.get_local_notes.GetLocalLastNoteUseCaseImpl
import com.together.domain.usecase.notes.get_local_notes.GetLocalNotesUseCase
import com.together.domain.usecase.notes.get_local_notes.GetLocalNotesUseCaseImpl
import com.together.domain.usecase.notes.post_local_note.PostLocalNoteUseCase
import com.together.domain.usecase.notes.post_local_note.PostLocalNoteUseCaseImpl
import com.together.domain.usecase.profile.GetProfileUseCase
import com.together.domain.usecase.profile.GetProfileUseCaseImpl
import com.together.domain.usecase.registration.RegisterUseCase
import com.together.domain.usecase.registration.RegisterUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    fun provideAuthUseCase(authRepositoryImpl: AuthRepositoryImpl): AuthUseCase {
        return AuthUseCaseImpl(authRepositoryImpl)
    }

    @Provides
    fun provideRegisterUseCase(registerRepositoryImpl: RegisterRepositoryImpl): RegisterUseCase {
        return RegisterUseCaseImpl(registerRepositoryImpl)
    }

    @Provides
    fun provideGetCoursesUseCase(
        courseRepositoryImpl: CourseRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ): GetCoursesUseCase {
        return GetCoursesUseCaseImpl(courseRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun providePostCourseUseCase(
       courseRepositoryImpl: CourseRepositoryImpl,
       domainToUiMapper: DomainToUiMapper,
       uiToDomainMapper: UiToDomainMapper
    ) : PostCourseUseCase {
        return PostCourseUseCaseImpl(courseRepositoryImpl, uiToDomainMapper, domainToUiMapper)
    }

    @Provides
    fun provideGetCourseByIdUseCase(
        courseRepositoryImpl: CourseRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ) : GetCourseByIdUseCase {
        return GetCourseByIdUseCaseImpl(courseRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideGetLastCoursesUseCase(
        courseRepositoryImpl: CourseRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ) : GetLastCoursesUseCase {
        return GetLastCoursesUseCaseImpl(courseRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideGetCommunityNotesUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ) : GetCommunityNotesUseCase {
        return GetCommunityNotesUseCaseImpl(noteRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideGetCommunityLastNoteUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ) : GetCommunityLastNoteUseCase {
        return GetCommunityLastNoteUseCaseImpl(noteRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideGetLocalNotesUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ) : GetLocalNotesUseCase {
        return GetLocalNotesUseCaseImpl(noteRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideGetLocalLastNoteUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ) : GetLocalLastNoteUseCase {
        return GetLocalLastNoteUseCaseImpl(noteRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideGetLocalFavNotesUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ) : GetLocalFavNotesUseCase {
        return GetLocalFavNotesUseCaseImpl(noteRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun providePostLocalNoteUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        uiToDomainMapper: UiToDomainMapper
    ) : PostLocalNoteUseCase {
        return PostLocalNoteUseCaseImpl(noteRepositoryImpl, uiToDomainMapper)
    }

    @Provides
    fun provideGetProfileUseCase(
        profileRepositoryImpl: ProfileRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ): GetProfileUseCase {
        return GetProfileUseCaseImpl(profileRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideCheckUserLoggedInUseCase(tokenManager: TokenManager): CheckUserLoggedInUseCase {
        return CheckUserLoggedInUseCaseImpl(tokenManager)
    }
}