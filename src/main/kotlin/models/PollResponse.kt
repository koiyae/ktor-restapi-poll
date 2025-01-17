package ktor.koiyae.models

import kotlinx.serialization.Serializable

@Serializable
data class PollResponse(
    var poll: TotalPoll
)