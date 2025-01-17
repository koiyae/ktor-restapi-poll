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
import ktor.koiyae.models.UpdateVoteRequest
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
        post("/poll/update") {
            val updateRequest = call.receive<UpdateVoteRequest>()
            val updated = repository.updateVote(updateRequest.id, updateRequest.newValue)
            if(updated) {
                call.respondText("Voto atualizado com sucesso", status = HttpStatusCode.Created)
            } else {
                call.respondText("Item não encontrado", status = HttpStatusCode.NotFound)
            }
        }
    }
}
