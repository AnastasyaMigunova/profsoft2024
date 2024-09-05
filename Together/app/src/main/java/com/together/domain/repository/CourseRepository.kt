package com.together.domain.repository

import com.together.domain.models.Course

interface CourseRepository {
    suspend fun getCourses() : List<Course>

    suspend fun postCourse(course: Course) : Course

    suspend fun getCourseById(courseId: String) : Course
}