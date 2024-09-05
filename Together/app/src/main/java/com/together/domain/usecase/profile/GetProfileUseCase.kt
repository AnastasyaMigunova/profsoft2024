package com.together.domain.usecase.profile

import com.together.ui.models.ProfileVO

interface GetProfileUseCase {
    suspend fun getProfile() : ProfileVO
}