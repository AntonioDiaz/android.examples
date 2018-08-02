package com.adiaz.kotlinandretrofit2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.adiaz.kotlinandretrofit2.model.RepositorySearchResult
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity(), Callback<RepositorySearchResult> {

    val TAG = MainActivity::class.java.simpleName

    override fun onFailure(call: Call<RepositorySearchResult>?, t: Throwable?) {
        tvResults.text = "Erroraco ${t?.localizedMessage}"
    }

    override fun onResponse(call: Call<RepositorySearchResult>?, response: Response<RepositorySearchResult>?) {
        tvResults.text = "Resultados: ${response?.body()?.total_count}"
        response?.body()?.items?.forEach {
            tvResults.append("\n\t Name: ${it.name}")
            tvResults.append("\n\t Url: ${it.url}\n")
        }
    }

    private val gitHubSearchApi: GitHubSearchApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        gitHubSearchApi = retrofit.create(GitHubSearchApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener() {
            val searchRepositories = gitHubSearchApi.searchRepositories(etSearchToken.text.toString())
            searchRepositories.enqueue(this)
        }
    }
}
