package com.together.domain.usecase.courses.post_course

import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.mapper.UiToDomainMapper
import com.together.domain.repository.CourseRepository
import com.together.ui.models.CourseVO
import javax.inject.Inject

class PostCourseUseCaseImpl @Inject constructor(
    private val courseRepository: CourseRepository,
    private val uiToDomainMapper: UiToDomainMapper,
    private val domainToUiMapper: DomainToUiMapper
) : PostCourseUseCase {
    override suspend fun postCourse(courseVO: CourseVO): CourseVO {
        return try {
            val course = uiToDomainMapper.run { courseVO.toDomain() }
            val postedCourse = courseRepository.postCourse(course)
            val courseVOResult = domainToUiMapper.run { postedCourse.toViewObject() }

            courseVOResult
        } catch (e: Exception) {
            throw e
        }
    }
}