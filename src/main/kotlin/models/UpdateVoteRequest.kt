package ktor.koiyae.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateVoteRequest(
    val id: String,
    val newValue: Int
)