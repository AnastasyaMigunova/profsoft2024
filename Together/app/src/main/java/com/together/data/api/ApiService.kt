package com.together.data.api

import com.together.data.models.TokenUserResponse
import com.together.data.models.auth.AuthRequestInfo
import com.together.data.models.courses.CourseRequestDTO
import com.together.data.models.courses.CourseResponseDTO
import com.together.data.models.courses.GetCoursesDTO
import com.together.data.models.notes.GetNotesDTO
import com.together.data.models.profile.GetProfileDTO
import com.together.data.models.registration.RegisterRequestInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth")
    suspend fun authUser(@Body authRequestInfo: AuthRequestInfo): TokenUserResponse

    @POST("register")
    suspend fun registerUser(@Body registerRequestInfo: RegisterRequestInfo): TokenUserResponse

    @GET("courses")
    suspend fun getCourses() : GetCoursesDTO

    @POST("courses")
    suspend fun postCourse(@Body courseRequestDTO: CourseRequestDTO) : CourseResponseDTO

    @GET("courses/{courseId}")
    suspend fun getCourseById(@Path("courseId") courseId: String) : CourseResponseDTO

    @GET("community_notes")
    suspend fun getNotes() : GetNotesDTO

    @POST("community_notes")
    suspend fun postNotes()

    @GET("profile")
    suspend fun getProfile() : GetProfileDTO
}