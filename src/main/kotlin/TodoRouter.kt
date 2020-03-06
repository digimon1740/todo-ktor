import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.put
import io.ktor.routing.route
import model.TodoRequest
import service.TodoService


fun Routing.todo(service: TodoService) {
    route("todos") {
        get {
            call.respond(service.getAll())
        }
        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get
            val todo = service.getById(id)
            if (todo == null) call.response.status(HttpStatusCode.NotFound)
            else call.respond(todo)
        }
        post {
            val body = call.receive<TodoRequest>()
            call.respond(service.new(body))
            call.response.status(HttpStatusCode.Created)
        }
        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put
            val body = call.receive<TodoRequest>()
            call.respond(service.renew(id, body))
            call.response.status(HttpStatusCode.NoContent)
        }
        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete
            service.delete(id)
            call.response.status(HttpStatusCode.NoContent)
        }
    }
}