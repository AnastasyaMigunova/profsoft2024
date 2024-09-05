package com.together.domain.usecase.courses.get_courses

import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.CourseRepository
import com.together.ui.models.CourseVO
import javax.inject.Inject

class GetLastCoursesUseCaseImpl @Inject constructor(
    private val courseRepository: CourseRepository,
    private val domainToUiMapper: DomainToUiMapper
): GetLastCoursesUseCase {
    override suspend fun getLastCourses(): List<CourseVO> {
        return try {
            val courses = courseRepository.getCourses()
            val lastFiveCourses = courses.takeLast(5).reversed()
            lastFiveCourses.map { course -> domainToUiMapper.run { course.toViewObject() } }
        } catch (e: Exception) {
            throw e
        }
    }

}