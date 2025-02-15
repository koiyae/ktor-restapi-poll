package ktor.koiyae.models

import kotlinx.serialization.Serializable

@Serializable
data class Poll(
    val id: String,
    val itemStatistics: String,
    var itemTotalVotes: Int
)