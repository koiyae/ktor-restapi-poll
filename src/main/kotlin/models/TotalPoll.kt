package ktor.koiyae.models

import kotlinx.serialization.Serializable

@Serializable
data class TotalPoll(
    val idPoll: String,
    val totalVotes: Int,
    val title: String,
    val description: String,
    val enqueteItemDto: List<Poll>
)