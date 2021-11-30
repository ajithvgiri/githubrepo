package com.ajithvgiri.githubrepo.api

import com.ajithvgiri.githubrepo.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository(private val apiService: ApiService) {
    suspend fun getOrganizationRepositories(): Flow<Resource<List<Repositories>>> {
        return flow {
            emit(Resource.loading(null))
            try {
                val response = apiService.getOrganizationRepositories("square")
                when (response.isSuccessful) {
                    true -> {
                        emit(Resource.success(response.body()))
                    }
                    else -> {
                        emit(Resource.error(response.message()))
                    }
                }
            } catch (e: Exception) {
                emit(Resource.error(e.localizedMessage))
            }
        }.flowOn(Dispatchers.IO)
    }
}