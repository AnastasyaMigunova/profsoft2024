package com.together.domain.usecase.courses.get_courses

import com.together.ui.models.CourseVO

interface GetCoursesUseCase {
    suspend fun getCourses() : List<CourseVO>
}