package com.together.domain.usecase.courses.get_courses

import com.together.ui.models.CourseVO

interface GetLastCoursesUseCase {
    suspend fun getLastCourses(): List<CourseVO>
}