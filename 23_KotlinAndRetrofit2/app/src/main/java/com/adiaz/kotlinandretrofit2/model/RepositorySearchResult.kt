package com.adiaz.kotlinandretrofit2.model

class RepositorySearchResult (
        val total_count: Int,
        var items: List<RepositoryItem>)

class RepositoryItem (val url: String, val name: String)
