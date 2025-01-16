package ktor.koiyae.models

import kotlinx.serialization.Serializable

@Serializable
data class Poll(
    val itemStatistics: String,
    val itemTotalVotes: Int
)