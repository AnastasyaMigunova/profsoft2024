package com.together.domain.usecase.courses.get_course_by_id

import com.together.ui.models.CourseVO

interface GetCourseByIdUseCase {
    suspend fun getCourseById(courseId: String) : Result<CourseVO>
}