package com.together.data.repository

import com.together.data.api.ApiService
import com.together.data.mapper.DataToDomainMapper
import com.together.data.mapper.DomainToDataMapper
import com.together.domain.models.Course
import com.together.domain.repository.CourseRepository
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dataToDomainMapper: DataToDomainMapper,
    private val domainToDataMapper: DomainToDataMapper
) : CourseRepository {
    override suspend fun getCourses(): List<Course> {
        return try {
            val response = apiService.getCourses()
            dataToDomainMapper.run { response.toDomain() }
        } catch (e: Exception) {
            throw Exception("Get courses error: ${e.message}", e)
        }
    }

    override suspend fun postCourse(course: Course): Course {
        return try {
            val postCourseRequestDTO = domainToDataMapper.run { course.toData() }
            val response = apiService.postCourse(postCourseRequestDTO)
            dataToDomainMapper.run { response.toDomain() }
        } catch (e: Exception) {
            throw Exception("Post courses error: ${e.message}", e)
        }
    }

    override suspend fun getCourseById(courseId: String): Course {
        return try {
            val response = apiService.getCourseById(courseId)
            dataToDomainMapper.run { response.toDomain() }
        } catch (e: Exception) {
            throw Exception("Get course by id error: ${e.message}", e)
        }
    }
}