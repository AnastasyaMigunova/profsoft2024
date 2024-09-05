package com.together.domain.usecase.courses.get_courses

import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.CourseRepository
import com.together.ui.models.CourseVO
import javax.inject.Inject

class GetCoursesUseCaseImpl @Inject constructor(
    private val courseRepository: CourseRepository,
    private val domainToUiMapper: DomainToUiMapper
) : GetCoursesUseCase {
    override suspend fun getCourses(): List<CourseVO> {
        return try {
            val courses = courseRepository.getCourses()
            val coursesVO = courses.map { course -> domainToUiMapper.run { course.toViewObject() } }
            coursesVO
        } catch (e: Exception) {
            throw e
        }
    }
}