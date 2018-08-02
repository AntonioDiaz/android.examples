package com.adiaz.kotlinandretrofit2

import com.adiaz.kotlinandretrofit2.model.RepositorySearchResult
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubSearchApi {
    //https://api.github.com/search/repositories?q=caranchoa
    //https://api.github.com/search/respositories?q=fff
    @GET("/search/repositories")
    fun searchRepositories(@Query("q") token: String): Call<RepositorySearchResult>
}