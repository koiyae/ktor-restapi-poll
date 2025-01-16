package ktor.koiyae

import ktor.koiyae.models.Poll
import ktor.koiyae.models.PollResponse
import ktor.koiyae.models.TotalPoll

fun createMessages(): List<Poll> {
    return listOf(
        Poll("29.63%", 8),
        Poll("18,52%", 5),
        Poll("7,41%", 2),
        Poll("7,41%", 2),
        Poll("14,81%", 4),
        Poll("22.22%", 6)
    )
}

fun calculateSum(messages: List<Poll>): Int {
    return messages.sumOf { it.itemTotalVotes }
}

fun createTotalPoll(sum: Int, message: List<Poll>): TotalPoll {
    return TotalPoll(
        "bbb-teste-hoje",
        totalVotes = sum,
        "título-enquete",
        "desc-enquete",
        message
    )
}

fun mountResponse(totalPoll: TotalPoll, messages: List<Poll>): PollResponse {
    return PollResponse(
        poll = TotalPoll(
            idPoll = totalPoll.idPoll,
            totalVotes = totalPoll.totalVotes,
            title = totalPoll.title,
            description = totalPoll.description,
            enqueteItemDto = messages
        )
    )
}