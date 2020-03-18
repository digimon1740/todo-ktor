package main.kotlin

import io.ktor.application.call
import io.ktor.features.BadRequestException
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.put
import io.ktor.routing.route
import io.ktor.util.KtorExperimentalAPI
import main.kotlin.model.TodoRequest
import main.kotlin.service.TodoService

@KtorExperimentalAPI
fun Routing.todo(service: TodoService) {

    route("todos") {
        get {
            call.respond(service.getAll())
        }
        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw BadRequestException("Parameter id is null")
            call.respond(service.getById(id))
        }
        post {
            val body = call.receive<TodoRequest>()
            service.new(body.content)
            call.response.status(HttpStatusCode.Created)
        }
        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw BadRequestException("Parameter id is null")
            val body = call.receive<TodoRequest>()
            service.renew(id, body)
            call.response.status(HttpStatusCode.NoContent)
        }
        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw BadRequestException("Parameter id is null")
            service.delete(id)
            call.response.status(HttpStatusCode.NoContent)
        }
    }
}