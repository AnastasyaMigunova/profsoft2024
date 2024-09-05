package com.together.ui.main_screen

import androidx.lifecycle.ViewModel
import com.together.domain.usecase.courses.get_courses.GetLastCoursesUseCase
import com.together.domain.usecase.notes.get_community_notes.GetCommunityLastNoteUseCase
import com.together.domain.usecase.notes.get_local_notes.GetLocalLastNoteUseCase
import com.together.domain.usecase.notes.post_local_note.PostLocalNoteUseCase
import com.together.ui.models.CommunityNoteVO
import com.together.ui.models.ContentVO
import com.together.ui.models.CourseVO
import com.together.ui.models.LocalNoteVO
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

data class MainState(
    val isLoading: Boolean = false,
    val lastCourses: List<CourseVO> = emptyList(),
    val lastCommunityNote: CommunityNoteVO? = null,
    val lastLocalNote: LocalNoteVO? = null,
    val errorMessage: String? = null
)

sealed class MainSideEffect {
    data object ShowError : MainSideEffect()
    data class NavigateTo(val type: String) : MainSideEffect()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLastCoursesUseCase: GetLastCoursesUseCase,
    private val getCommunityLastNoteUseCase: GetCommunityLastNoteUseCase,
    private val getLocalLastNoteUseCase: GetLocalLastNoteUseCase,
    private val postLocalNoteUseCase: PostLocalNoteUseCase
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {

    override val container = container<MainState, MainSideEffect>(MainState())

    fun loadData() = intent {
        reduce { state.copy(isLoading = true) }

        try {
            val courses = getLastCoursesUseCase.getLastCourses()
            val lastCommunityNote = getCommunityLastNoteUseCase.getLastCommunityNote()
            val lastLocalNote = getLocalLastNoteUseCase.getLastLocalNote()

            reduce {
                state.copy(
                    isLoading = false,
                    lastCourses = courses,
                    lastCommunityNote = lastCommunityNote,
                    lastLocalNote = lastLocalNote
                )
            }

        } catch (exception: Exception) {
            reduce { state.copy(isLoading = false, errorMessage = exception.message) }
            postSideEffect(MainSideEffect.ShowError)
        }
    }

    fun navigate(type: String) = intent {
        postSideEffect(MainSideEffect.NavigateTo(type))
    }

    fun insertNote() = intent {
        val localNote =
            LocalNoteVO(
                "3",
                "Тестовая заметка 3",
                listOf(ContentVO("", "Тест 3")),
                "5 сентября",
                false
            )
            postLocalNoteUseCase.postLocalNote(localNote)
    }
}
