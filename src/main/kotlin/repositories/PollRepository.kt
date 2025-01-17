package ktor.koiyae.repositories

import ktor.koiyae.models.PollResponse

class PollRepository {

    val poll get() = _poll.toList()

    fun save(task: PollResponse) {
        _poll.add(task)
    }

    fun updateVote(id: String, newValue: Int): Boolean {
        _poll.forEach { response ->
            response.poll.enqueteItemDto.find { it.id == id }?.let {
                it.itemTotalVotes = newValue
                recalculateTotalVotes(response)
                return true
            }
        }
        return false
    }

    private fun recalculateTotalVotes(response: PollResponse) {
        val total = response.poll.enqueteItemDto.sumOf { it.itemTotalVotes }
        response.poll = response.poll.copy(totalVotes = total)
    }

    companion object {
        private val _poll = mutableListOf<PollResponse>()
    }
}