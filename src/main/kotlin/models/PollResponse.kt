package ktor.koiyae.models

import kotlinx.serialization.Serializable

@Serializable
data class PollResponse(
    val poll: TotalPoll
)