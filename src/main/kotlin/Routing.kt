package ktor.koiyae

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import ktor.koiyae.models.Poll
import ktor.koiyae.models.PollResponse
import ktor.koiyae.repositories.PollRepository

fun Application.configureRouting() {

    val repository = PollRepository()
    val message = createMessages()
    val sum = calculateSum(message)
    val totalPoll = createTotalPoll(sum, message)
    val response = mountResponse(totalPoll, message)

    repository.save(response)

    routing {
        get("/poll") {
            call.respond(repository.poll)
        }
        post("/poll") {
            val task = call.receive<PollResponse>()
            repository.save(task)
            call.respondText("Item criado", status = HttpStatusCode.Created)
        }
        post("/poll/item") {
            val newPollItem = call.receive<Poll>()
            repository.addPollItem(newPollItem)
            call.respondText("Item adicionado à enquete", status = HttpStatusCode.Created)
        }
    }
}
