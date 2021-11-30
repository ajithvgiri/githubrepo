package com.ajithvgiri.githubrepo.api


import java.io.Serializable

data class Repositories(
    var id: Int = 0,
    var name: String = "",
    var html_url: String = "",
    var description: String = "",
    var language: String = "",
    var topics: List<String> = listOf(),
    var visibility: String = "",

    var watchers: Int = 0,
    var forks: Int = 0,
    var stargazers_count: Int = 0,
    var open_issues: Int = 0
) : Serializable