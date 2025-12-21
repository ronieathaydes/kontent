package com.ronieathaydes.kontent.domain.provider

import com.ronieathaydes.kontent.domain.model.Config
import com.ronieathaydes.kontent.domain.model.Status

interface KontentProvider {
    suspend fun getConfigs(): List<Config>
    suspend fun getHomeTimeline(): Result<List<Status>>
}
