package com.ronieathaydes.kontent.domain.provider

import com.ronieathaydes.kontent.domain.model.Status

interface KontentProvider {
    suspend fun getHomeTimeline(): Result<List<Status>>
}
