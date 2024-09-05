package com.together.domain.usecase.courses.get_course_by_id

import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.CourseRepository
import com.together.ui.models.CourseVO
import javax.inject.Inject

class GetCourseByIdUseCaseImpl @Inject constructor(
    private val courseRepository: CourseRepository,
    private val domainToUiMapper: DomainToUiMapper
) : GetCourseByIdUseCase {
    override suspend fun getCourseById(courseId: String): Result<CourseVO> {
        return try {
            val course = courseRepository.getCourseById(courseId)
            val courseVO = domainToUiMapper.run { course.toViewObject() }
            Result.success(courseVO)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}