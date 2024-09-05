package com.together.domain.usecase.courses.post_course

import com.together.ui.models.CourseVO

interface PostCourseUseCase {
    suspend fun postCourse(courseVO : CourseVO) : CourseVO
}