package ktor.koiyae.repositories

import ktor.koiyae.models.Poll
import ktor.koiyae.models.PollResponse

class PollRepository {

    val poll get() = _poll.toList()

    fun save(task: PollResponse) {
        _poll.add(task)
    }

    fun addPollItem(newPoll: Poll) {
        if(_poll.isNotEmpty()) {
            val currentPoll = _poll[0]
            val updatedItems = currentPoll.poll.enqueteItemDto + newPoll
            val updatedTotalVotes = currentPoll.poll.totalVotes + newPoll.itemTotalVotes
            val updatedPoll = currentPoll.copy(
                poll = currentPoll.poll.copy(
                    totalVotes = updatedTotalVotes,
                    enqueteItemDto = updatedItems
                )
            )

            _poll[0] = updatedPoll
        }
    }

    companion object {
        private val _poll = mutableListOf<PollResponse>()
    }
}