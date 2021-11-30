package com.ajithvgiri.githubrepo.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("orgs/{organization}/repos")
    suspend fun getOrganizationRepositories(@Path("organization") organization: String): Response<List<Repositories>>
}